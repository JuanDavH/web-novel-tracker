package com.github.juandavh.webnoveltracker.novelfolder;

import com.github.juandavh.webnoveltracker.novelfolder.dto.CreateFolderItemRequest;
import com.github.juandavh.webnoveltracker.novelfolder.dto.FolderItemResponse;
import com.github.juandavh.webnoveltracker.novelfolder.dto.NovelFolderResponse;
import com.github.juandavh.webnoveltracker.novelfolder.dto.UpdateFolderItemPositionRequest;
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
    public ResponseEntity<List<NovelFolderResponse>> getAllNovelFolders() {
        return ResponseEntity.ok(novelFolderService.getAllNovelFolders());
    }

    @GetMapping("/{folderId}")
    public ResponseEntity<NovelFolderResponse> getNovelFolder(@PathVariable UUID folderId) {
        return ResponseEntity.ok(novelFolderService.getNovelFolderById(folderId));
    }

    @PostMapping
    public ResponseEntity<NovelFolderResponse> createNovelFolder(@RequestBody NovelFolder novelFolder) {
        NovelFolderResponse createdNovelFolder = novelFolderService.createNovelFolder(novelFolder.getFolderName());
        URI location = URI.create("/folders/" + createdNovelFolder.id());
        return ResponseEntity.created(location).body(createdNovelFolder);
    }

    @PatchMapping("/{folderId}")
    public ResponseEntity<NovelFolderResponse> updateNovelFolder(@PathVariable UUID folderId, @RequestBody NovelFolder novelFolder) {
        return ResponseEntity.ok(novelFolderService.updateNovelFolder(folderId, novelFolder.getFolderName()));
    }

    @DeleteMapping("/{folderId}")
    public ResponseEntity<Void> deleteNovelFolder(@PathVariable UUID folderId) {
        novelFolderService.deleteNovelFolder(folderId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{folderId}/items")
    public ResponseEntity<List<FolderItemResponse>> getFolderItems(@PathVariable UUID folderId) {
        return ResponseEntity.ok(folderItemService.getFolderItems(folderId));
    }

    @PostMapping("/{folderId}/items")
    public ResponseEntity<FolderItemResponse> createFolderItem(@PathVariable UUID folderId,
                                                               @RequestBody CreateFolderItemRequest request) {
        FolderItemResponse createdFolderItem = folderItemService.createFolderItem(folderId, request.novelId());
        URI location = URI.create("/folders/" + folderId + "/items" + createdFolderItem.id());
        return ResponseEntity.created(location).body(createdFolderItem);
    }

    @PatchMapping("/{folderId}/items/{itemId}")
    public ResponseEntity<Void> updateFolderItem(@PathVariable UUID folderId, @PathVariable UUID itemId
    , @RequestBody UpdateFolderItemPositionRequest request) {
        folderItemService.updateFolderItemPosition(folderId, itemId, request.newPosition());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{folderId}/items/{itemId}")
    public ResponseEntity<Void> deleteFolderItem(@PathVariable UUID folderId, @PathVariable UUID itemId) {
        folderItemService.deleteFolderItem(folderId, itemId);
        return ResponseEntity.noContent().build();
    }

}
