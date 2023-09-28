package design.ratelimiter.bucket4j;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.junit.Assert;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Example {
    public static void main(String[] args) {
        Bandwidth limit = Bandwidth.simple(10, Duration.ofSeconds(10000));
        Bucket bucket = Bucket.builder().addLimit(limit).build();
        if (bucket.tryConsume(1)) {
            System.out.println("do something");
        } else {
            System.out.println("do nothing");
        }

        /*
        Basic Usage
         */
        Bandwidth limit1 = Bandwidth.classic(10, Refill.intervally(10, Duration.ofMinutes(1)));
        Bucket bucket1 = Bucket.builder().addLimit(limit1).build();
        for (int i = 1; i <= 10; i++) {
            assertTrue(bucket1.tryConsume(1));
        }
        assertFalse(bucket1.tryConsume(1));


        Bandwidth limit2 = Bandwidth.classic(1, Refill.intervally(1, Duration.ofSeconds(2)));
        Bucket bucket3 = Bucket.builder().addLimit(limit2).build();
        Assert.assertTrue(bucket3.tryConsume(1));     // first request
        Executors.newScheduledThreadPool(1)   // schedule another request for 2 seconds later
                .schedule(() -> assertTrue(bucket.tryConsume(1)), 2, TimeUnit.SECONDS);


        /*
        we may wish to avoid spikes that would exhaust all the tokens in the first 5 seconds
         */
        Bucket bucket4 = Bucket.builder()
                .addLimit(Bandwidth.classic(10, Refill.intervally(10, Duration.ofMinutes(1))))
                .addLimit(Bandwidth.classic(5, Refill.intervally(5, Duration.ofSeconds(20))))
                .build();
        for (int i = 1; i <= 5; i++) {
            assertTrue(bucket4.tryConsume(1));
        }
        assertFalse(bucket4.tryConsume(1));
    }
}
