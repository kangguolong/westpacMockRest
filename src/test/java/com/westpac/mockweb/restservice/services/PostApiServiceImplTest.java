package com.westpac.mockweb.restservice.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.westpac.mockweb.restservice.api.domain.Post;
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

class PostApiServiceImplTest {

    static MockWebServer mockWebServer;
    PostApiService postApiService;
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
        postApiService = new PostApiServiceImpl(baseUrl);
    }

    @Test
    void getPostsByUserId() throws Exception {
        //given
        List<Post> posts = Arrays.asList(Post.builder().build(), Post.builder().build());
        MockResponse mockResponse = new MockResponse()
                .addHeader("Content-Type", "application/json")
                .setResponseCode(HttpStatus.OK.value())
                .setBody(MAPPER.writeValueAsString(posts));
        mockWebServer.enqueue(mockResponse);

        //when
        Flux<Post> postFlux = postApiService.getPostsByUserId(Mono.just(anyInt()));

        //then
        StepVerifier.create(postFlux)
                .expectNextCount(2)
                .verifyComplete();

        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertEquals("GET", recordedRequest.getMethod());
    }

    @Test
    void getPosts() throws Exception{
        //given
        List<Post> posts = Arrays.asList(Post.builder().build(), Post.builder().build());
        MockResponse mockResponse = new MockResponse()
                .addHeader("Content-Type", "application/json")
                .setResponseCode(HttpStatus.OK.value())
                .setBody(MAPPER.writeValueAsString(posts));
        mockWebServer.enqueue(mockResponse);

        //when
        Flux<Post> postFlux = postApiService.getPosts();

        //then
        StepVerifier.create(postFlux)
                .expectNextCount(2)
                .verifyComplete();

        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertEquals("GET", recordedRequest.getMethod());
        assertEquals("/posts", recordedRequest.getPath());
    }
}
