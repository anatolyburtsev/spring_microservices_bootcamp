package ru.java.mentor.storeinvertedindex.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ReverseIndexPair {
    private String word;
    private List<Mono<Integer>> documentIds;

    public ReverseIndexPair(String word, Mono<Integer> docId) {
        this.word = word;
        this.documentIds = Collections.singletonList(docId);
    }

    public ReverseIndexPair(SimplePair simplePair) {
        this(simplePair.getWord(), simplePair.getDocumentId());
    }
}
