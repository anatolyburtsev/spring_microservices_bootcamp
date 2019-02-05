package ru.java.mentor.storeoriginaldata.dao;

import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.java.mentor.storeoriginaldata.entity.Text;

import java.util.List;
import java.util.Optional;

@Repository
public interface TextRepository extends MongoRepository<Text, Integer> {
    Optional<Text> findById(final Integer id);
    List<Text> findAll();
    Text insert(final Text text);

}
