package com.westpac.mockweb.restservice.controllers;

import com.westpac.mockweb.restservice.api.domain.Comment;
import com.westpac.mockweb.restservice.services.CommentApiService;
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
@WebFluxTest(controllers = CommentController.class)
class CommentControllerTest {

    @Autowired
    WebTestClient webTestClient;
    @MockBean
    CommentApiService commentApiService;

    @Test
    void getCommentsByPostId() {
        Flux<Comment> commentFlux = Flux.empty();
        doReturn(commentFlux).when(commentApiService).getCommentsByPostId(Mono.just(anyInt()));

        webTestClient.get()
                .uri("/comments/byPostId/1")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Comment.class)
                .hasSize(0);

        verify(commentApiService, times(1)).getCommentsByPostId(any());
    }
}
