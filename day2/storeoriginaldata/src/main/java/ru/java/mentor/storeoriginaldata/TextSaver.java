package ru.java.mentor.storeoriginaldata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.java.mentor.storeoriginaldata.dao.SequenceDao;
import ru.java.mentor.storeoriginaldata.entity.Text;
import ru.java.mentor.storeoriginaldata.dao.TextRepository;

@Component
public class TextSaver {

    @Autowired
    private TextRepository textRepository;

    @Autowired
    private SequenceDao sequenceDao;

    private static final String DOC_ID_SEQ_KEY = "docId";

    @Value("${spring.data.mongodb.database}")
    private String mongoDB;

    public SaveResponse saveTextToDb(String content) {
        Integer newLastid = sequenceDao.getNextSequenceId(DOC_ID_SEQ_KEY); //getLastId() + 1;
        Text text = new Text(content, newLastid);
        textRepository.insert(text);
        return new SaveResponse(newLastid);
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

//    private Integer getLastId(){
//        Document lastId = mongoConfig
//                .mongoClient()
//                .getDatabase(mongoDB)
//                .getCollection("texts")
//                .find()
//                .sort(new BasicDBObject("_id", OrderBy.DESC.getIntRepresentation()))
//                .limit(1)
//                .first();
//        if (lastId == null){
//            return 0;
//        } else{
//            return lastId.getInteger("_id");
//        }
//    }
}
