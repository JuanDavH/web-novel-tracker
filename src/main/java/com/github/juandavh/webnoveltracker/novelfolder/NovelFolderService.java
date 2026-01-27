package com.github.juandavh.webnoveltracker.novelfolder;

import com.github.juandavh.webnoveltracker.novelfolder.dto.NovelFolderResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NovelFolderService {

    private final NovelFolderRepository novelFolderRepository;

    public NovelFolderService(final NovelFolderRepository novelFolderRepository) {
        this.novelFolderRepository = novelFolderRepository;
    }

    public List<NovelFolderResponse> getAllNovelFolders() {
        return novelFolderRepository.findAll()
                .stream().map(NovelFolderResponse::fromEntity).toList();
    }

    public NovelFolderResponse getNovelFolderById(UUID id) {
        return NovelFolderResponse.fromEntity(novelFolderRepository.findById(id)
                .orElseThrow(() -> new NovelFolderNotFoundException(id)));
    }

    public NovelFolderResponse createNovelFolder(NovelFolder novelFolder) {
        return NovelFolderResponse.fromEntity(novelFolderRepository.save(novelFolder));
    }

    public NovelFolderResponse updateNovelFolder(UUID id, NovelFolder updatedNovelFolder) {
        NovelFolder existingFolder = novelFolderRepository.findById(id)
                .orElseThrow(() -> new NovelFolderNotFoundException(id));

        existingFolder.setFolderName(updatedNovelFolder.getFolderName());
        return NovelFolderResponse.fromEntity(novelFolderRepository.save(existingFolder));
    }

    public void deleteNovelFolder(UUID id) {
        NovelFolder novelFolder = novelFolderRepository.findById(id)
                .orElseThrow(() -> new NovelFolderNotFoundException(id));

        novelFolderRepository.delete(novelFolder);
    }

}
