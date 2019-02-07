package ru.java.mentor.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.java.mentor.hibernate.entity.ExpediaData;
import ru.java.mentor.hibernate.repo.ExpediaDataRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ExpediaController {

    @Autowired
    private ExpediaDataRepository expediaDataRepository;

    @Autowired
    private StreamDataLoader dataLoader;

    @RequestMapping("/add/{id}")
    public void add(@PathVariable int id) {
        ExpediaData expediaData = new ExpediaData();
        expediaData.setChannel(id);
        expediaDataRepository.save(expediaData);
    }

    @RequestMapping("/hello")
    public String hello(){
        return "HelloWorld";
    }

    @RequestMapping("/getbychannel/{id}")
    public ExpediaData get(@PathVariable int id) {
        return expediaDataRepository.getByChannel(id);
    }

    @GetMapping("/getbyid/{id}")
    public ExpediaData getById(@PathVariable long id) {
        return expediaDataRepository.getById(id);
    }

    @GetMapping("/csv2db")
    public void saveToDb() {
        dataLoader.saveToDb();
    }

    @GetMapping("/count")
    public long count() {
        return expediaDataRepository.count();
    }

    @GetMapping("/saveall")
    public int saveAll() {
        List<ExpediaData> expediaDataList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ExpediaData ed = new ExpediaData();
            ed.setChannel(i);
            expediaDataList.add(ed);
        }
        return expediaDataRepository.saveAll(expediaDataList).size();
    }
}
