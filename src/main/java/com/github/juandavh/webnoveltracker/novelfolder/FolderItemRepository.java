package com.github.juandavh.webnoveltracker.novelfolder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FolderItemRepository extends JpaRepository<FolderItem, UUID> {

    List<FolderItem> findByFolderId(UUID folderId);

    Optional<FolderItem> findByFolderIdAndNovelId(UUID folderId, UUID novelId);

    int getFolderItemCountByFolderId(UUID folderId);
}
