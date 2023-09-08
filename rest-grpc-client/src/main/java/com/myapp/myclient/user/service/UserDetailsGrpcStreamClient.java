package com.myapp.myclient.user.service;

import com.myapp.UserDetailsRequest;
import com.myapp.UserDetailsResponse;
import com.myapp.UserDetailsServiceGrpc;
import com.myapp.myclient.user.model.UserDetails;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Schedulers;

import java.util.Map;

@Service
public class UserDetailsGrpcStreamClient {

    @Autowired
    DataFactory dataFactory;

    @GrpcClient("UserDetailsService")
    private UserDetailsServiceGrpc.UserDetailsServiceStub stub;

    public Flux<Object> generateUserStreamResponse(Integer range) {
        DirectProcessor<Object> processor = DirectProcessor.create();
        StreamObserver<UserDetailsResponse> observer = new StreamObserverImpl(processor.sink());
        StreamObserver<UserDetailsRequest> inputStreamObserver = this.stub.generateRandomUserStream(observer);
        return Flux.range(1, range)
                .map(i -> UserDetailsRequest.newBuilder()
                        .setCity(dataFactory.getCity())
                        .setLastName(dataFactory.getLastName())
                        .setFirstName(dataFactory.getFirstName())
                        .build())
                .doOnNext(inputStreamObserver::onNext)
                .zipWith(processor, (a, b) -> b)
                .doOnComplete(inputStreamObserver::onCompleted)
                .subscribeOn(Schedulers.boundedElastic());
    }

    private class StreamObserverImpl implements StreamObserver<UserDetailsResponse> {

        final FluxSink<Object> sink;

        public StreamObserverImpl(FluxSink<Object> sink) {
            this.sink = sink;
        }

        @Override
        public void onNext(UserDetailsResponse output) {
            this.sink.next(Map.of(output.getId(), new UserDetails(output)));
        }

        @Override
        public void onError(Throwable throwable) {
            this.sink.error(throwable);
        }

        @Override
        public void onCompleted() {
            this.sink.complete();
        }
    }
}