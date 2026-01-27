package com.github.juandavh.webnoveltracker.novelfolder.exception;

import java.util.UUID;

public class NovelAlreadyInFolderException extends RuntimeException{
    public NovelAlreadyInFolderException(UUID novelId, UUID folderId){
        super("Novel " + novelId + " already exists in folder " + folderId);
    }
}
