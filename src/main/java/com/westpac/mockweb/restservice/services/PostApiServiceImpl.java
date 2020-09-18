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

    private final WebClient webClient;
    private final static String POST_ENDPOINT = "posts";

    public PostApiServiceImpl(@Value("${api.url}") String apiUrl) {
        this.webClient = WebClient.create(apiUrl);
    }

    @Override
    public Flux<Post> getPostsByUserId(Mono<Integer> userId) {
        return webClient
                .get()
                .uri(POST_ENDPOINT, uriBuilder ->
                        uriBuilder.queryParam("userId",
                                userId.block()).build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Post.class);
    }

    @Override
    public Flux<Post> getPosts() {
        return webClient
                .get()
                .uri(POST_ENDPOINT)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Post.class);
    }
}
