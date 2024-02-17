package design.lld.instagram.controllers;

import design.lld.instagram.dtos.LikeDto;
import design.lld.instagram.services.LikeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/{postId}/likes")
    public ResponseEntity<Void> likePost(@PathVariable Long postId, @RequestBody LikeDto likeDto) {
        likeService.likePost(postId, likeDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{postId}/likes/{userId}")
    public ResponseEntity<Void> unlikePost(@PathVariable Long postId, @PathVariable Long userId) {
        likeService.unlikePost(postId, userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{postId}/likes")
    public ResponseEntity<List<LikeDto>> getLikesByPost(@PathVariable Long postId) {
        List<LikeDto> likes = likeService.getLikesByPostId(postId);
        return ResponseEntity.ok(likes);
    }
}

