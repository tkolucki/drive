package com.eduonix.votingsystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="candidates")
@Entity
public class Candidate {

    @Id
    @Column(name="id")
    private Long id;

    @Column(name="candidate_name")
    private String name;

    @Column(name = "numbersOfVotes")
    private Integer numberOfVotes;

    public Integer getNumberOfVotes() {
        return numberOfVotes;
    }

    public void setNumberOfVotes(Integer numberOfVotes) {
        this.numberOfVotes = numberOfVotes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Candidate() {
        super();
    }

    public Candidate(Long id, String name, Integer numberOfVotes) {
        this.id = id;
        this.name = name;
        this.numberOfVotes = numberOfVotes;
    }




}
