package com.westpac.mockweb.restservice.controllers;

import com.westpac.mockweb.restservice.api.domain.Post;
import com.westpac.mockweb.restservice.services.PostApiService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = PostController.class)
class PostControllerTest {

    @Autowired
    WebTestClient webTestClient;
    @MockBean
    PostApiService postApiService;

    @Test
    void getPosts() {
        Flux<Post> mockPosts = Flux.just(Post.builder().build(), Post.builder().build());
        doReturn(mockPosts).when(postApiService).getPosts();

        webTestClient.get()
                .uri("/api/v1/posts")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Post.class)
                .hasSize(2);
        verify(postApiService, times(1)).getPosts();
    }

    @Test
    void getPostsByUserId() {
        Flux<Post> mockPosts = Flux.empty();
        doReturn(mockPosts).when(postApiService).getPostsByUserId(Mono.just(anyInt()));

        webTestClient.get()
                .uri("/api/v1/posts/byUserId/1")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Post.class)
                .hasSize(0);

        verify(postApiService, times(1)).getPostsByUserId(any());
    }
}
