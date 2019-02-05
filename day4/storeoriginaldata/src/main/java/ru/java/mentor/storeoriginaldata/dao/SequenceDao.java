package ru.java.mentor.storeoriginaldata.dao;

public interface SequenceDao {

    int getNextSequenceId(String key) throws SequenceException;
}
