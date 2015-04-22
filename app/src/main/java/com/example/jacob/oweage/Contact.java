package com.example.jacob.oweage;

import java.util.ArrayList;

/**
 * Created by jacob on 4/21/15.
 */
public class Contact {
    private String name = "";
    private String relation = "";
    private ArrayList<TransactionEntry> history = new ArrayList<TransactionEntry>();

    public void addTransactionEntry(TransactionEntry entry) {
        history.add(entry);
    }

    public void removeTransactionEntry(TransactionEntry entry) {
        history.remove(entry);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public ArrayList<TransactionEntry> getHistory() {
        return history;
    }

    public void setHistory(ArrayList<TransactionEntry> entry) {
        this.history = entry;
    }
}
