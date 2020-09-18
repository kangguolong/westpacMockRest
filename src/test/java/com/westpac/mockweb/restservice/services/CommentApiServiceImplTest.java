package com.westpac.mockweb.restservice.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.westpac.mockweb.restservice.api.domain.Comment;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;

class CommentApiServiceImplTest {

    static MockWebServer mockWebServer;
    CommentApiService commentApiService;
    ObjectMapper MAPPER = new ObjectMapper();

    @BeforeAll
    static void init() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @BeforeEach
    void setUp() {
        String baseUrl = String.format("http://localhost:%s", mockWebServer.getPort());
        commentApiService = new CommentApiServiceImpl(baseUrl);
    }

    @Test
    void getCommentsByPostId() throws Exception {
        //given
        List<Comment> comments = Arrays.asList(Comment.builder().build(),
                Comment.builder().build());
        MockResponse mockResponse = new MockResponse()
                .addHeader("Content-Type", "application/json")
                .setResponseCode(HttpStatus.OK.value())
                .setBody(MAPPER.writeValueAsString(comments));
        mockWebServer.enqueue(mockResponse);

        //when
        Flux<Comment> commentFlux = commentApiService.getCommentsByPostId(Mono.just(1));

        //then
        StepVerifier.create(commentFlux)
                .expectNextCount(2)
                .verifyComplete();

        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertEquals("GET", recordedRequest.getMethod());
    }
}
