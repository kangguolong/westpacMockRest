package com.westpac.mockweb.restservice.services;

import com.westpac.mockweb.restservice.api.domain.Post;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PostApiService {
    Flux<Post> getPostsByUserId(Mono<Integer> userId);
    Flux<Post> getPosts();
}
