package com.github.juandavh.webnoveltracker.novelfolder.dto;

import com.github.juandavh.webnoveltracker.novelfolder.NovelFolder;

import java.time.LocalDateTime;
import java.util.UUID;

public record NovelFolderResponse(UUID id, String folderName, LocalDateTime createdAt) {
    public static NovelFolderResponse fromEntity(NovelFolder novelFolder) {
        return new NovelFolderResponse(novelFolder.getId(), novelFolder.getFolderName(), novelFolder.getCreatedAt());
    }
}
