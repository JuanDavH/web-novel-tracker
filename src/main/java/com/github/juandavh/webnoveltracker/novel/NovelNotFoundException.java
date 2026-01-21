package com.github.juandavh.webnoveltracker.novel;

public class NovelNotFoundException extends RuntimeException {
    public NovelNotFoundException(String message) {
        super(message);
    }
}
