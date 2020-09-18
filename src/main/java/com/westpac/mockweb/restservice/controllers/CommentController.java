package com.westpac.mockweb.restservice.controllers;

import com.westpac.mockweb.restservice.api.domain.Comment;
import com.westpac.mockweb.restservice.services.CommentApiService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentApiService commentApiService;

    public CommentController(CommentApiService commentApiService) {
        this.commentApiService = commentApiService;
    }

    @GetMapping("/byPostId/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Comment> getCommentsByPostId(@PathVariable Integer postId) {
        return commentApiService.getCommentsByPostId(Mono.just(postId));
    }
}
