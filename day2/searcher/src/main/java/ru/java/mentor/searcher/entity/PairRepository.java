package ru.java.mentor.searcher.entity;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PairRepository extends MongoRepository<ReverseIndexPair, String> {
    ReverseIndexPair findByWord(final String word);
}
