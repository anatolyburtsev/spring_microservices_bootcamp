package com.mylnikov.HibernatePerfTest.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "user")
public class SimpleUser {

    @Id
    @GeneratedValue
    public Long user_id;

    public String name;

    public String email;

    @OneToMany
    @Cascade({CascadeType.ALL})
    @JoinColumn(name="user_id", nullable = false, updatable = false)
    public List<Account> accounts;

}
