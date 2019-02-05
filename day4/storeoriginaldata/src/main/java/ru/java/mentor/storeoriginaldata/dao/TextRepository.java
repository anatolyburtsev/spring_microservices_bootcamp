package ru.java.mentor.storeoriginaldata.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.java.mentor.storeoriginaldata.entity.Text;

@Repository
public interface TextRepository extends ReactiveMongoRepository<Text, Integer> {
    Mono<Text> findById(final Integer id);
    Mono insert(final Text text);

}
