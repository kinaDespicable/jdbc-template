package dev.spring.simpleCrud.model.enums;

import lombok.Getter;

@Getter
public enum Deleted {
    DELETED("Successfully deleted."),
    NOT_DELETED("Record hasn't been deleted.");

    private String phrase;
    Deleted(String phrase){
        this.phrase = phrase;
    }
}
