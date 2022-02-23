package com.xcale.test.backend.repository;

import com.xcale.test.backend.model.Messages;
import io.reactivex.Observable;
import org.springframework.data.repository.reactive.RxJava2CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends RxJava2CrudRepository<Messages, String> {

    Observable<Messages> findAllByGroupId(String groupId);
}
