package com.mylnikov.HibernatePerfTest.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Account {

    @Id
    @GeneratedValue
    public Long account_id;

    @ManyToOne
    public SimpleUser user;

}
