package ru.java.mentor.searcher.entity;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public interface PairRepository extends ReactiveMongoRepository<ReverseIndexPair, String> {
    Mono<ReverseIndexPair> findByWord(final String word);
}
