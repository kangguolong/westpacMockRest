package com.westpac.mockweb.restservice.services;

import com.westpac.mockweb.restservice.api.domain.Comment;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CommentApiService {
    Flux<Comment> getCommentsByPostId(Mono<Integer> postId);
}
