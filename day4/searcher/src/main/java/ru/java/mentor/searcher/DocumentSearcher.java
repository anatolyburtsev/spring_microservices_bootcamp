package ru.java.mentor.searcher;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.java.mentor.searcher.entity.PairRepository;
import ru.java.mentor.searcher.entity.ReverseIndexPair;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Component
public class DocumentSearcher {

    @Autowired
    private PairRepository pairRepository;

    public Mono<List<Integer>> getDocsBySentence(String sentence) {
        Stream<String> words = Stream.of(sentence
                .replaceAll("[^a-zA-Zа-яА-Я ]", "")
                .toLowerCase().split(" "));


        Flux<ReverseIndexPair> documentIdsFlux = Flux.merge(
                words.map(p -> pairRepository.findByWord(p))
                        .collect(Collectors.toList())
        );

        Mono<List<Integer>> documentIds = documentIdsFlux
                .map(ReverseIndexPair::getDocumentIds)
                .reduce(this::intersectSortedList);
        return documentIds;

    }

    public Flux<List<Integer>> getManyDocs(List<String> query) {
        return Flux.merge(
                query.stream().map(this::getDocsBySentence).collect(Collectors.toList())
        );
    }

    private List<Integer> intersectSortedList(List<Integer> l1, List<Integer> l2) {
        int i1 = 0;
        int i2 = 0;
        List<Integer> resultList = new ArrayList<>();
        while (i1 < l1.size() && i2 < l2.size()) {
            int obj1 = l1.get(i1);
            int obj2 = l2.get(i2);
            if (obj1 == obj2) {
                resultList.add(obj1);
                i1++;
                i2++;
            } else if (obj1 > obj2) {
                i2++;
            } else {
                i1++;
            }
        }
        return resultList;
    }

    private <T> List<T> intersect(List<T> l1, List<T> l2) {
        l2.retainAll(l1);
        return l2;
    }
}
