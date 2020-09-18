package com.westpac.mockweb.restservice.controllers;

import com.westpac.mockweb.restservice.api.domain.Post;
import com.westpac.mockweb.restservice.services.PostApiService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostApiService postApiService;

    public PostController(PostApiService postApiService) {
        this.postApiService = postApiService;
    }

    @GetMapping({"", "/"})
    @ResponseStatus(HttpStatus.OK)
    public Flux<Post> getPosts() {
        return postApiService.getPosts();
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Post> getPostsByUserId(@PathVariable Integer userId) {
        return postApiService.getPostsByUserId(Mono.just(userId));
    }
}
