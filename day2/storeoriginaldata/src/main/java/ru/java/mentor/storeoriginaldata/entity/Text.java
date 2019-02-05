package ru.java.mentor.storeoriginaldata.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.Generated;

@Document(collection = "texts")
public class Text {

    private String content;

    public Text() {
    }

    private Integer id;

    public String getContent() {
        return content;
    }

    public Integer getId() {
        return id;
    }

    public Text(String content, Integer id) {
        this.content = content;
        this.id = id;
    }

    public Text(String content) {
        this.content = content;
    }
}
