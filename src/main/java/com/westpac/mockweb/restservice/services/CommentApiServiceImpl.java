package com.westpac.mockweb.restservice.services;

import com.westpac.mockweb.restservice.api.domain.Comment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CommentApiServiceImpl implements CommentApiService{

    private final String API_URL;
    private final static String COMMENT_ENDPOINT = "comments";

    public CommentApiServiceImpl(@Value("${api.url}") String apiUrl) {
        this.API_URL = apiUrl;
    }

    @Override
    public Flux<Comment> getCommentsByPostId(Mono<Integer> postId) {
        return WebClient.create(API_URL + COMMENT_ENDPOINT)
                .get()
                .uri(uriBuilder -> uriBuilder.queryParam("postId", postId.block()).build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Comment.class);
    }
}
