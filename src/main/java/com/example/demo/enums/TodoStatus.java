package com.example.demo.enums;

public enum TodoStatus {
    TODO("Todo"), IN_PROGRESS("In progress"), DONE("Done");

    public final String status;

    private TodoStatus(String status) {
        this.status = status;
    }
}