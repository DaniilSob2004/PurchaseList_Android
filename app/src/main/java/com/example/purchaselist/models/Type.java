package com.example.purchaselist.models;

public class Type {
    private int id;
    private String label;
    private String rule;

    public Type(int id, String label, String rule) {
        this.id = id;
        this.label = label;
        this.rule = rule;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public String getRule() {
        return rule;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    @Override
    public String toString() {
        return "Type { id=" + id + ", label=" + label + ", rule=" + rule + " }";
    }
}
