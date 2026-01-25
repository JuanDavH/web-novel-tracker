package com.github.juandavh.webnoveltracker.novelfolder;

import com.github.juandavh.webnoveltracker.exception.NotFoundException;

import java.util.UUID;

public class FolderItemNotFoundException extends NotFoundException {
    public FolderItemNotFoundException(UUID id) {
        super("No folder item found with id: " + id);
    }
}
