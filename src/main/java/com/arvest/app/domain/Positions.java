package com.arvest.app.domain;

public class Positions {
    private Position[] previous;
    private Position[] results;
    private Position[] next;

    public Position[] getPrevious() {
        return previous;
    }

    public void setPrevious(Position[] previous) {
        this.previous = previous;
    }

    public Position[] getResults() {
        return results;
    }

    public void setResults(Position[] results) {
        this.results = results;
    }

    public Position[] getNext() {
        return next;
    }

    public void setNext(Position[] next) {
        this.next = next;
    }
}
