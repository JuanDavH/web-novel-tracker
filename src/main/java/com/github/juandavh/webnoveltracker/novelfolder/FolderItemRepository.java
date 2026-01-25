package com.github.juandavh.webnoveltracker.novelfolder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FolderItemRepository extends JpaRepository<FolderItem, UUID> {

    List<FolderItem> findByFolderId(UUID folderId);

    Optional<FolderItem> findByFolderIdAndNovelId(UUID folderId, UUID novelId);

    int countByFolderId(UUID folderId);

    @Modifying
    @Query("UPDATE FolderItem folderItem SET folderItem.position = folderItem.position + 1 " +
            "WHERE folderItem.folder.id = :folderId AND folderItem.position BETWEEN :start AND :end")
    void incrementPositionsBetweenRange(@Param("folderId") UUID folderId, @Param("start") int start,
                                       @Param("end") int end);
    @Modifying
    @Query("UPDATE FolderItem folderItem SET folderItem.position = folderItem.position - 1 " +
            "WHERE folderItem.folder.id = :folderId AND folderItem.position BETWEEN :start AND :end")
    void decrementPositionsBetweenRange(@Param("folderId") UUID folderId, @Param("start") int start,
                                       @Param("end") int end);
}
