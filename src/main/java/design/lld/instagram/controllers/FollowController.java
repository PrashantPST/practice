package design.lld.instagram.controllers;

import design.lld.instagram.dtos.FollowDto;
import design.lld.instagram.dtos.UserDto;
import design.lld.instagram.services.FollowService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class FollowController {

    private final FollowService followService;

    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    @PostMapping("/follow")
    public ResponseEntity<Void> follow(@RequestBody FollowDto followDto) {
        followService.follow(followDto.getFollowingId(), followDto.getFollowerId());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/unfollow")
    public ResponseEntity<Void> unfollow(@RequestBody FollowDto followDto) {
        followService.unfollow(followDto.getFollowingId(), followDto.getFollowerId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{followingId}/followers")
    public ResponseEntity<List<UserDto>> getFollowers(@PathVariable String followingId) {
        List<UserDto> followers = followService.getFollowers(followingId);
        return ResponseEntity.ok(followers);
    }

    @GetMapping("/{followerId}/following")
    public ResponseEntity<List<UserDto>> getFollowings(@PathVariable String followerId) {
        List<UserDto> followings = followService.getFollowings(followerId);
        return ResponseEntity.ok(followings);
    }
}

