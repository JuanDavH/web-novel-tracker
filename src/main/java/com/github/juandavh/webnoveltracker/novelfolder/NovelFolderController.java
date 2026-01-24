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

    public NovelFolderController(NovelFolderService novelFolderService) {
        this.novelFolderService = novelFolderService;
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
}
