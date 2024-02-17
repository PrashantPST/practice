package design.lld.ratelimiter.bucket4j.interceptor;

import design.lld.ratelimiter.bucket4j.service.PricingPlanService;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.ConsumptionProbe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RateLimitInterceptor implements HandlerInterceptor {

  private static final String HEADER_API_KEY = "X-api-key";
  private static final String HEADER_LIMIT_REMAINING = "X-Rate-Limit-Remaining";
  private static final String HEADER_RETRY_AFTER = "X-Rate-Limit-Retry-After-Seconds";
  private static final long NANOS_IN_SECOND = 1_000_000_000L;
  private static final String RATE_LIMIT_EXCEEDED_MESSAGE = "You have exhausted your API Request Quota";

  private final PricingPlanService pricingPlanService;

  @Autowired
  public RateLimitInterceptor(PricingPlanService pricingPlanService) {
    this.pricingPlanService = pricingPlanService;
  }

  public boolean preHandle(HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    String apiKey = request.getHeader(HEADER_API_KEY);
    if (isApiKeyInvalid(apiKey)) {
      sendErrorResponse(response, HttpStatus.BAD_REQUEST, "Missing Header: " + HEADER_API_KEY);
      return false;
    }

    Bucket tokenBucket = pricingPlanService.resolveBucket(apiKey);
    ConsumptionProbe probe = tokenBucket.tryConsumeAndReturnRemaining(1);

    if (probe.isConsumed()) {
      addRateLimitHeaders(response, probe.getRemainingTokens());

      return true;
    } else {
      long waitForRefill = probe.getNanosToWaitForRefill() / NANOS_IN_SECOND;
      sendRateLimitExceededResponse(response, waitForRefill);
      return false;
    }
  }

  private boolean isApiKeyInvalid(String apiKey) {
    return apiKey == null || apiKey.trim().isEmpty();
  }

  private void addRateLimitHeaders(HttpServletResponse response, long remainingTokens) {
    response.addHeader(HEADER_LIMIT_REMAINING, String.valueOf(remainingTokens));
  }

  private void sendRateLimitExceededResponse(HttpServletResponse response, long waitForRefill)
      throws Exception {
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.addHeader(HEADER_RETRY_AFTER, String.valueOf(waitForRefill));
    sendErrorResponse(response, HttpStatus.TOO_MANY_REQUESTS, RATE_LIMIT_EXCEEDED_MESSAGE);
  }

  private void sendErrorResponse(HttpServletResponse response, HttpStatus status, String message)
      throws Exception {
    response.sendError(status.value(), message);
  }
}
