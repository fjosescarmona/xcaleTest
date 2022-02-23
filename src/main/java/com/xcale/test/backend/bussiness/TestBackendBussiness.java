package com.xcale.test.backend.bussiness;

import com.xcale.test.backend.api.models.GetMessageResponse;
import com.xcale.test.backend.api.models.SendMessageRequest;
import io.reactivex.Completable;
import io.reactivex.Observable;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface TestBackendBussiness {

    Completable sendMessage(SendMessageRequest sendMessageRequest);

    Observable<List<GetMessageResponse>> getMessages(String groupId);
}
