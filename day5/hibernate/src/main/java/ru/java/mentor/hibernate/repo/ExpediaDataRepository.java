package ru.java.mentor.hibernate.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.java.mentor.hibernate.entity.ExpediaData;

import javax.transaction.Transactional;
import java.util.List;

public interface ExpediaDataRepository extends JpaRepository<ExpediaData, Long> {
    ExpediaData getById(Long id);
    ExpediaData getByChannel(int channel);
    long count();
}
