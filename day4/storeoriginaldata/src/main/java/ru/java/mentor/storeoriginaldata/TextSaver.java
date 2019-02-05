package ru.java.mentor.storeoriginaldata;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import ru.java.mentor.storeoriginaldata.dao.SequenceDao;
import ru.java.mentor.storeoriginaldata.entity.Text;
import ru.java.mentor.storeoriginaldata.dao.TextRepository;

@Slf4j
@Component
public class TextSaver {

    @Autowired
    private TextRepository textRepository;

    @Autowired
    private SequenceDao sequenceDao;

    private static final String DOC_ID_SEQ_KEY = "docId";

    @Value("${spring.data.mongodb.database}")
    private String mongoDB;

    public Mono<Integer> saveTextToDbReactive(String content) {
        return Mono.fromCallable(() -> saveTextToDb(content))
                .subscribeOn(Schedulers.elastic());
    }

    public Integer saveTextToDb(String content) {
        log.info("launch saveTextToDb with text: " + content);
        Integer newLastid = sequenceDao.getNextSequenceId(DOC_ID_SEQ_KEY);
        Text text = new Text(content, newLastid);
        textRepository.insert(text).block();
        return newLastid;
    }

    public class SaveResponse {
        public SaveResponse(Integer id) {
            this.id = id;
        }

        public Integer getId() {
            return id;
        }

        private Integer id;
    }
}
