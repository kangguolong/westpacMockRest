package com.westpac.mockweb.restservice.services;

import com.westpac.mockweb.restservice.api.domain.Post;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PostApiServiceImpl implements PostApiService{

    private final String API_URL;
    private final static String POST_ENDPOINT = "posts";

    public PostApiServiceImpl(@Value("${api.url}") String apiUrl) {
        this.API_URL = apiUrl;
    }

    @Override
    public Flux<Post> getPostsByUserId(Mono<Integer> userId) {
        return WebClient.create(API_URL + POST_ENDPOINT)
                .get()
                .uri(uriBuilder -> uriBuilder.queryParam("userId", userId.block()).build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Post.class);
    }

    @Override
    public Flux<Post> getPosts() {
        return WebClient.create(API_URL + POST_ENDPOINT)
                .get()
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Post.class);
    }
}
