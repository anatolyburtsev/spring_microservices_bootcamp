package ru.java.mentor.indexator;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reactor.core.publisher.Mono;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pair {
    private String word;
    private Integer documentId;
}
