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

    @GetMapping("/{id}")
    public ResponseEntity<Novel> getNovel(@PathVariable UUID id) {
        return ResponseEntity.ok(novelService.getNovelById(id));
    }

    @PostMapping
    public ResponseEntity<Novel> createNovel(@RequestBody Novel novel) {
        Novel createdNovel = novelService.createNovel(novel);
        URI location = URI.create("/novels/" + createdNovel.getId());
        return ResponseEntity.created(location).body(novel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Novel> updateNovel(@PathVariable UUID id, @RequestBody Novel novel) {
        return ResponseEntity.ok(novelService.updateNovel(id, novel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNovel(@PathVariable UUID id) {
        novelService.deleteNovel(id);
        return ResponseEntity.noContent().build();
    }
}
