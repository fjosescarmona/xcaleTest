package com.xcale.test.backend.bussiness.impl;

import com.xcale.test.backend.api.models.GetMessageResponse;
import com.xcale.test.backend.api.models.SendMessageRequest;
import com.xcale.test.backend.bussiness.TestBackendBussiness;
import com.xcale.test.backend.model.Messages;
import com.xcale.test.backend.repository.MessageRepository;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import jdk.internal.org.jline.utils.Log;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j
@AllArgsConstructor
public class TestBackendBussinessImpl implements TestBackendBussiness {

    private MessageRepository messageRepository;
    @Override
    public Completable sendMessage(SendMessageRequest sendMessageRequest) {
        return messageRepository.save(sendMessageRequestToMessage(sendMessageRequest))
                .subscribeOn(Schedulers.io())
                .flatMapCompletable(messages -> sendNotification(sendMessageRequest.getGroupId()))
                .doOnSubscribe(disposable -> Log.info("Starting send message"))
                .doOnComplete(() -> Log.info("send message success"));
    }

    @Override
    public Observable<List<GetMessageResponse>> getMessages(String groupId) {
        return messageRepository.findAllByGroupId(groupId)
                .subscribeOn(Schedulers.io())
                .map(this::messagesToGetMessagesResponse)
                .toList()
                .toObservable()
                .doOnSubscribe(disposable -> Log.info("Starting get messages"));
    }

    private GetMessageResponse messagesToGetMessagesResponse(Messages messages) {
        GetMessageResponse getMessageResponse = new GetMessageResponse();
        getMessageResponse.setMensaje(messages.getMessage());
        return getMessageResponse;
    }

    private Messages sendMessageRequestToMessage(SendMessageRequest sendMessageRequest) {
        Messages messages = new Messages();
        messages.setMessage(sendMessageRequest.getMensaje());
        messages.setGroupId(sendMessageRequest.getGroupId());
        return messages;
    }

    private Completable sendNotification(String groupId) {
        //aqui la logica de envio de notificaciones a usuarios del grupo indicado en groupId
        return Completable.complete();
    }
}
