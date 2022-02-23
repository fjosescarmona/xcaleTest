package com.xcale.test.backend.controller;

import com.xcale.test.backend.api.controller.MessagesApi;
import com.xcale.test.backend.api.models.GetMessageResponse;
import com.xcale.test.backend.api.models.SendMessageRequest;
import com.xcale.test.backend.bussiness.TestBackendBussiness;
import com.xcale.test.backend.model.Messages;
import io.reactivex.Completable;
import io.reactivex.Observable;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@EnableScheduling
@AllArgsConstructor
public class TestBackendController implements MessagesApi {

    private TestBackendBussiness testBackendBussiness;

    @Override
    public Observable<List<GetMessageResponse>> getMessages(String groupId) {
        return testBackendBussiness.getMessages(groupId);
    }

    @Override
    public Completable sendMessage(SendMessageRequest sendMessageRequest) {
        return testBackendBussiness.sendMessage(sendMessageRequest);
    }

}
