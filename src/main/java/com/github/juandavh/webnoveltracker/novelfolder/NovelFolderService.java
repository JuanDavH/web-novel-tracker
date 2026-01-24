package com.github.juandavh.webnoveltracker.novelfolder;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NovelFolderService {

    private final NovelFolderRepository novelFolderRepository;

    public NovelFolderService(final NovelFolderRepository novelFolderRepository) {
        this.novelFolderRepository = novelFolderRepository;
    }

    public List<NovelFolder> getAllNovelFolders() {
        return novelFolderRepository.findAll();
    }

    public NovelFolder getNovelFolderById(UUID id) {
        return novelFolderRepository.findById(id)
                .orElseThrow(() -> new NovelFolderNotFoundException(id));
    }

    public NovelFolder createNovelFolder(NovelFolder novelFolder) {
        return novelFolderRepository.save(novelFolder);
    }

    public NovelFolder updateNovelFolder(UUID id, NovelFolder updatedNovelFolder) {
        NovelFolder existingFolder = novelFolderRepository.findById(id)
                .orElseThrow(() -> new NovelFolderNotFoundException(id));

        existingFolder.setFolderName(updatedNovelFolder.getFolderName());
        return novelFolderRepository.save(existingFolder);
    }

    public void deleteNovelFolder(UUID id) {
        NovelFolder novelFolder = novelFolderRepository.findById(id)
                .orElseThrow(() -> new NovelFolderNotFoundException(id));

        novelFolderRepository.delete(novelFolder);
    }

}
