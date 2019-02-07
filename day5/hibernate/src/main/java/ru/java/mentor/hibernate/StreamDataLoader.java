package ru.java.mentor.hibernate;

import com.opencsv.CSVReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.java.mentor.hibernate.entity.ExpediaData;
import ru.java.mentor.hibernate.repo.ExpediaDataRepository;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Slf4j
@Component
public class StreamDataLoader {
    private static String csvFile;
    private static CSVReader csvReader;

    private AtomicInteger atomicInteger = new AtomicInteger();

    @Autowired
    private ExpediaDataRepository expediaDataRepository;

    @PostConstruct
    public void init() {
        csvFile = "/Users/aburtsev/IdeaProjects/bootcamp/day5/hibernate/train.csv";
        try {
            csvReader = new CSVReader(new FileReader(csvFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Stream<ExpediaData> getCsvStream() {
        Iterable<String[]> iterable = () -> csvReader.iterator();
        return StreamSupport
                .stream(iterable.spliterator(), false)
                .skip(1)
                .map(ExpediaData::new);
    }

    @Transactional
    public void saveToDb() {
        getCsvStream().forEach(this::saveAndFlush);
    }

    void saveAndFlush(ExpediaData expediaData) {
        int batch_size = 50001;
        int notifyEachSize = 100000;
        expediaDataRepository.save(expediaData);
        int iteration = atomicInteger.incrementAndGet();
        if (iteration % notifyEachSize == 0) {
            getCount(iteration);
        }
        if (iteration % batch_size == 0) {
            expediaDataRepository.flush();
        }
    }

    private long startTime = 0;

    private static final int totalCount = 37670294;

    public void getCount(long realCount) {
        if (startTime == 0 & realCount > 0){
            startTime = System.currentTimeMillis();
        }
        double percentsProcessed =  ((double)(realCount * 100)) / totalCount;
        if (percentsProcessed > 99.9999) {
            log.info("finished");
        }
        if (startTime > 0) {
            long currentTime = System.currentTimeMillis();

            long secondsFromStart = (currentTime - startTime) / 1000;
            int secondsToFinish = (int) ((100. - percentsProcessed) / percentsProcessed * secondsFromStart );
            log.info(String.format("progress: %.2f%% time from start: %d sec, time to finish: %d sec",
                    percentsProcessed,
                    secondsFromStart,
                    secondsToFinish));
        } else {
            log.info("process hasn't started yet");
        }
    }

}
