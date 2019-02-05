package ru.java.mentor.storeoriginaldata.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "lastid")
public class LastId {
    private Integer lastid;
}
