package com.github.juandavh.webnoveltracker.novelfolder;

import com.github.juandavh.webnoveltracker.novel.Novel;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "folderitems", uniqueConstraints = {@UniqueConstraint(columnNames= {"novelID", "folderID"})})
public class FolderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID id;

    // Many-to-one relationship to allow multiple folders to contain the same novel
    @ManyToOne
    @JoinColumn(name="novelID", nullable = false)
    private Novel novel;

    // Many-to-one relationship to allow folders to contain multiple folder items
    @ManyToOne
    @JoinColumn(name="folderID", nullable = false)
    private NovelFolder folder;

    private int position;
    private boolean pinned = false;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    public FolderItem() {

    }

    public FolderItem(Novel novel, NovelFolder folder, int position, boolean pinned) {
        this.novel = novel;
        this.folder = folder;
        this.position = position;
        this.pinned = pinned;
        this.createdAt = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public Novel getNovel() {
        return novel;
    }

    public void setNovel(Novel novel) {
        this.novel = novel;
    }

    public NovelFolder getFolder() {
        return folder;
    }

    public void setFolder(NovelFolder folder) {
        this.folder = folder;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isPinned() {
        return pinned;
    }

    public void setPinned(boolean pinned) {
        this.pinned = pinned;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

}
