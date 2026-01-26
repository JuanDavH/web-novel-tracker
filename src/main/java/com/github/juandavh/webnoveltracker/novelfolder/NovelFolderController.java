package com.github.juandavh.webnoveltracker.novelfolder;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/folders")
public class NovelFolderController {
    private final NovelFolderService novelFolderService;
    private final FolderItemService folderItemService;

    public NovelFolderController(NovelFolderService novelFolderService, FolderItemService folderItemService) {
        this.novelFolderService = novelFolderService;
        this.folderItemService = folderItemService;
    }

    @GetMapping
    public ResponseEntity<List<NovelFolder>> getAllNovelFolders() {
        return ResponseEntity.ok(novelFolderService.getAllNovelFolders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NovelFolder> getNovelFolder(@PathVariable UUID id) {
        return ResponseEntity.ok(novelFolderService.getNovelFolderById(id));
    }

    @PostMapping
    public ResponseEntity<NovelFolder> createNovelFolder(@RequestBody NovelFolder novelFolder) {
        NovelFolder createdNovelFolder = novelFolderService.createNovelFolder(novelFolder);
        URI location = URI.create("/folders/" + createdNovelFolder.getId());
        return ResponseEntity.created(location).body(createdNovelFolder);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<NovelFolder> updateNovelFolder(@PathVariable UUID id, @RequestBody NovelFolder novelFolder) {
        return ResponseEntity.ok(novelFolderService.updateNovelFolder(id, novelFolder));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNovelFolder(@PathVariable UUID id) {
        novelFolderService.deleteNovelFolder(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/items")
    public ResponseEntity<List<FolderItem>> getFolderItems(@PathVariable UUID id) {
        return ResponseEntity.ok(folderItemService.getFolderItems(id));
    }

    @PostMapping("/{id}/items")
    public ResponseEntity<FolderItem> createFolderItem(@PathVariable UUID id, @RequestBody UUID novelId) {
        FolderItem createdFolderItem = folderItemService.createFolderItem(id, novelId);
        URI location = URI.create("/folders/" + id + "/items" + createdFolderItem.getId());
        return ResponseEntity.created(location).body(createdFolderItem);
    }

    @PatchMapping("/{folderId}/items/{itemId}")
    public ResponseEntity<Void> updateFolderItem(@PathVariable UUID folderId, @PathVariable UUID itemId
    , @RequestBody int newPosition) {
        folderItemService.updateFolderItemPosition(folderId, itemId, newPosition);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{folderId}/items/{itemId}")
    public ResponseEntity<Void> deleteFolderItem(@PathVariable UUID folderId, @PathVariable UUID itemId) {
        folderItemService.deleteFolderItem(folderId, itemId);
        return ResponseEntity.noContent().build();
    }

}
