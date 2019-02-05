package ru.java.mentor.storeinvertedindex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.java.mentor.storeinvertedindex.entity.SimplePair;

import java.util.List;

@RestController
public class RevertIndexController {
    @PostMapping("/reverseindex")
    public Integer saveReverseIndex(@RequestBody List<SimplePair> reverseIndex) {
        return mongoInteract.addReverseIndexMany(reverseIndex);
    }

    @Autowired
    private MongoInteract mongoInteract;

    @RequestMapping("/addone/{word}/{docId}")
    public Integer addOne(@PathVariable String word, @PathVariable Integer docId) {
        return mongoInteract.addReverseIndexElement(new SimplePair(word, docId));
    }

    @RequestMapping("/doc/{word}")
    public SimplePair getOne(@PathVariable String word) {
        return mongoInteract.findIndexElement(word);
    }

}
