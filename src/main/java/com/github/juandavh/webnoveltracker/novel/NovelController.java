package com.github.juandavh.webnoveltracker.novel;

import org.springframework.web.bind.annotation.*;

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
    public List<Novel> getAllNovels() {
        return novelService.getAllNovels();
    }

    @GetMapping("/{id}")
    public Novel getNovel(@PathVariable UUID id) {
        return novelService.getNovelById(id);
    }

    @PostMapping
    public Novel createNovel(@RequestBody Novel novel) {
        return novelService.createNovel(novel);
    }

    @PutMapping("/{id}")
    public Novel updateNovel(@PathVariable UUID id, @RequestBody Novel novel) {
        return novelService.updateNovel(id, novel);
    }

    @DeleteMapping("/{id}")
    public void deleteNovel(@PathVariable UUID id) {
        novelService.deleteNovel(id);
    }
}
