package com.github.juandavh.webnoveltracker.novel;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/novels")
public class NovelController {
    private final NovelService novelService;

    public NovelController(NovelService novelService) {
        this.novelService = novelService;
    }

    @GetMapping
    public ResponseEntity<List<Novel>> getAllNovels() {
        return ResponseEntity.ok(novelService.getAllNovels());
    }

    @GetMapping("/{novelId}")
    public ResponseEntity<Novel> getNovel(@PathVariable UUID novelId) {
        return ResponseEntity.ok(novelService.getNovelById(novelId));
    }

    @PostMapping
    public ResponseEntity<Novel> createNovel(@RequestBody Novel novel) {
        Novel createdNovel = novelService.createNovel(novel);
        URI location = URI.create("/novels/" + createdNovel.getId());
        return ResponseEntity.created(location).body(novel);
    }

    @PutMapping("/{novelId}")
    public ResponseEntity<Novel> updateNovel(@PathVariable UUID novelId, @RequestBody Novel novel) {
        return ResponseEntity.ok(novelService.updateNovel(novelId, novel));
    }

    @DeleteMapping("/{novelId}")
    public ResponseEntity<Void> deleteNovel(@PathVariable UUID novelId) {
        novelService.deleteNovel(novelId);
        return ResponseEntity.noContent().build();
    }
}
