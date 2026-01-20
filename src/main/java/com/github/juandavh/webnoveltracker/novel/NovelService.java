package com.github.juandavh.webnoveltracker.novel;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NovelService {

    private final NovelRepository novelRepository;

    public NovelService(NovelRepository novelRepository) {
        this.novelRepository = novelRepository;
    }

    public Novel getNovelById(UUID id) {
        return novelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No novel found with id: " + id));
    }

    public List<Novel> getAllNovels() {
        return novelRepository.findAll();
    }

    public Novel createNovel(Novel novel) {
        return novelRepository.save(novel);
    }

    public Novel updateNovel(UUID id, Novel updatedNovel) {
        Novel existingNovel = novelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No novel found with id: " + id));
        existingNovel.setTitle(updatedNovel.getTitle());
        existingNovel.setAuthor(updatedNovel.getAuthor());
        existingNovel.setDescription(updatedNovel.getDescription());
        existingNovel.setTotalChapters(updatedNovel.getTotalChapters());
        return novelRepository.save(existingNovel);
    }

    public void deleteNovel(UUID id) {
        Novel novel = novelRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("No novel found with id: " + id));
        novelRepository.delete(novel);
    }
}
