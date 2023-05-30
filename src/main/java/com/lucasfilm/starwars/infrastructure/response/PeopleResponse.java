package com.lucasfilm.starwars.infrastructure.response;

import java.util.List;

public class PeopleResponse {

    private String next;

    private List<PersonResponse> results;

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public List<PersonResponse> getResults() {
        return results;
    }

    public void setResults(List<PersonResponse> results) {
        this.results = results;
    }
}
