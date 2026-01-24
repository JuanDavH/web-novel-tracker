package com.github.juandavh.webnoveltracker.novelfolder;

import com.github.juandavh.webnoveltracker.exception.NotFoundException;

import java.util.UUID;

public class NovelFolderNotFoundException extends NotFoundException {
    public NovelFolderNotFoundException(UUID id) {
        super("No folder found with id " + id);
    }
}
