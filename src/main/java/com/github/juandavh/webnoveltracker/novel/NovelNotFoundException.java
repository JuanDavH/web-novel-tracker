package com.github.juandavh.webnoveltracker.novel;

import com.github.juandavh.webnoveltracker.exception.NotFoundException;

import java.util.UUID;

public class NovelNotFoundException extends NotFoundException {
    public NovelNotFoundException(UUID id) {
        super("No novel found with id " + id);
    }
}
