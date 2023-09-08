package com.myapp.myclient.user.controller;

import com.myapp.myclient.user.service.UserDetailsGrpcBlockingClient;
import com.myapp.myclient.user.service.UserDetailsGrpcStreamClient;
import com.myapp.myclient.user.service.UserDetailsRestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class UserDetailsController {

    private final UserDetailsRestService userDetailsRestService;
    private final UserDetailsGrpcBlockingClient userDetailsGrpcBlockingClient;
    private final UserDetailsGrpcStreamClient userDetailsGrpcStreamClient;

    public UserDetailsController(UserDetailsRestService userDetailsRestService,
                                 UserDetailsGrpcBlockingClient userDetailsGrpcBlockingClient,
                                 UserDetailsGrpcStreamClient userDetailsGrpcStreamClient) {
        this.userDetailsRestService = userDetailsRestService;
        this.userDetailsGrpcBlockingClient = userDetailsGrpcBlockingClient;
        this.userDetailsGrpcStreamClient = userDetailsGrpcStreamClient;
    }

    @GetMapping("/rest/unary/user/{range}")
    public Flux<Object> getRestUsers(@PathVariable Integer range) {
        return this.userDetailsRestService.generateUsers(range);
    }


    @GetMapping("/grpc/unary/user/{range}")
    public Flux<Object> getGRPCBlockingUsers(@PathVariable Integer range) {
        return this.userDetailsGrpcBlockingClient.getUserDetailsResponse(range);
    }

    @GetMapping("/grpc/stream/user/{range}")
    public Flux<Object> getGRPCStreamUsers(@PathVariable Integer range) {
        return this.userDetailsGrpcStreamClient.generateUserStreamResponse(range);
    }
}