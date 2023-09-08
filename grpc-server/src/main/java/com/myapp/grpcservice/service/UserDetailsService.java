package com.myapp.grpcservice.service;

import com.myapp.UserDetailsRequest;
import com.myapp.UserDetailsResponse;
import com.myapp.UserDetailsServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@GrpcService
public class UserDetailsService extends UserDetailsServiceGrpc.UserDetailsServiceImplBase {

    @Autowired
    DataFactory dataFactory;
    private final AtomicInteger ID_GENERATOR = new AtomicInteger();

    @Override
    public void generateRandomUser(UserDetailsRequest request, StreamObserver<UserDetailsResponse> responseObserver) {
        var city = request.getCity();
        UserDetailsResponse output = UserDetailsResponse.newBuilder()
                .setCity(city)
                .setId(UUID.randomUUID().toString())
                .setNumericId(ID_GENERATOR.incrementAndGet())
                .setFirstName(request.getFirstName())
                .setLastName(request.getLastName())
                .build();
        responseObserver.onNext(output);
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<UserDetailsRequest> generateRandomUserStream(StreamObserver<UserDetailsResponse> responseObserver) {
        return new StreamObserver<UserDetailsRequest>() {
            @Override
            public void onNext(UserDetailsRequest input) {
                UserDetailsResponse output = UserDetailsResponse.newBuilder()
                        .setCity(dataFactory.getCity())
                        .setId(UUID.randomUUID().toString())
                        .setNumericId(ID_GENERATOR.incrementAndGet())
                        .setFirstName(dataFactory.getFirstName())
                        .setLastName(dataFactory.getLastName())
                        .build();
                responseObserver.onNext(output);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }

}