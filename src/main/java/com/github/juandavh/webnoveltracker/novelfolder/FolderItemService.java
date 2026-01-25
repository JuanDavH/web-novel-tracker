package com.github.juandavh.webnoveltracker.novelfolder;

import com.github.juandavh.webnoveltracker.novel.Novel;
import com.github.juandavh.webnoveltracker.novel.NovelNotFoundException;
import com.github.juandavh.webnoveltracker.novel.NovelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FolderItemService {

    private final FolderItemRepository folderItemRepository;
    private final NovelFolderRepository novelFolderRepository;
    private final NovelRepository novelRepository;

    public FolderItemService(FolderItemRepository folderItemRepository, NovelFolderRepository novelFolderRepository,
                             NovelRepository novelRepository) {
        this.folderItemRepository = folderItemRepository;
        this.novelFolderRepository = novelFolderRepository;
        this.novelRepository = novelRepository;
    }

    public List<FolderItem> getFolderItems(UUID folderId) {
        return folderItemRepository.findByFolderId(folderId);
    }

    public FolderItem createFolderItem(UUID folderId, UUID novelId) {
        // Ensure given folder and novel exists
        NovelFolder novelFolder = novelFolderRepository.findById(folderId)
                .orElseThrow(() -> new NovelFolderNotFoundException(folderId));

        Novel novel = novelRepository.findById(novelId)
                .orElseThrow(() -> new NovelNotFoundException(novelId));

        // Ensure novel has not already been added to the folder
        Optional<FolderItem> existingFolderItem = folderItemRepository.findByFolderIdAndNovelId(folderId, novelId);
        if (existingFolderItem.isPresent()) {
            throw new IllegalArgumentException("Novel already exists in folder");
        }

        int defaultPosition = folderItemRepository.countByFolderId(folderId);

        FolderItem folderItem = new FolderItem(novel, novelFolder, defaultPosition, false);
        return folderItemRepository.save(folderItem);
    }

    public void updateFolderItemPosition(UUID id, int newPosition) {
        FolderItem folderItem = folderItemRepository.findById(id)
                .orElseThrow(() -> new FolderItemNotFoundException(id));
        NovelFolder novelFolder = folderItem.getFolder();

        int oldPosition = folderItem.getPosition();
        if (newPosition == oldPosition) return;

        int maxPosition = folderItemRepository.countByFolderId(novelFolder.getId());
        if (newPosition < 0 || newPosition > maxPosition) {
            throw new IllegalArgumentException("Position out of bounds");
        }

        // Adjust bounds to not include updated folder item
        if (newPosition > oldPosition) {
            folderItemRepository.decrementPositionsBetweenRange(id, oldPosition + 1, newPosition);
        }
        else {
            folderItemRepository.incrementPositionsBetweenRange(id, oldPosition, newPosition - 1);
        }

        folderItem.setPosition(newPosition);
    }

    public void deleteFolderItem(UUID id) {
        FolderItem folderItem = folderItemRepository.findById(id)
                .orElseThrow(() -> new FolderItemNotFoundException(id));

        int oldPosition = folderItem.getPosition();
        // folderItemRepository.decrementPositionsAfterDeletion(oldPosition)

        folderItemRepository.delete(folderItem);
    }

}
