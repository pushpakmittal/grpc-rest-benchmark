package com.myapp.myclient.user.service;

import com.myapp.UserDetailsRequest;
import com.myapp.UserDetailsResponse;
import com.myapp.UserDetailsServiceGrpc;
import com.myapp.myclient.user.model.UserDetails;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.Map;


@Service
public class UserDetailsGrpcBlockingClient {

    @Autowired
    DataFactory dataFactory;

    @GrpcClient("UserDetailsService")
    UserDetailsServiceGrpc.UserDetailsServiceBlockingStub userDetailsServiceBlockingStub;

    public Flux<Object> getUserDetailsResponse(Integer range) {
        return
                Flux.range(1, range)
                        .map(i -> UserDetailsRequest.newBuilder()
                                .setCity(dataFactory.getCity())
                                .setLastName(dataFactory.getLastName())
                                .setFirstName(dataFactory.getFirstName())
                                .build())
                        .map(i -> {
                            UserDetailsResponse response = this.userDetailsServiceBlockingStub.generateRandomUser(i);
                            return (Object) Map.of(response.getId(), new UserDetails(response));
                        })
                        .subscribeOn(Schedulers.boundedElastic());
    }
}