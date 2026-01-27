package com.github.juandavh.webnoveltracker.novelfolder.dto;

import com.github.juandavh.webnoveltracker.novel.Novel;
import com.github.juandavh.webnoveltracker.novelfolder.FolderItem;

import java.util.UUID;

public record FolderItemResponse(UUID id, int position, boolean pinned, Novel novel) {

    public static FolderItemResponse fromEntity(FolderItem folderItem) {
        return new FolderItemResponse(folderItem.getId(), folderItem.getPosition(),
                folderItem.isPinned(), folderItem.getNovel());
    }
}
