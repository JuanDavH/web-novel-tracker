package com.github.juandavh.webnoveltracker.novelfolder;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="novelfolders")
public class NovelFolder {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String folderName;

    @OneToMany(mappedBy = "folder", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<FolderItem> items;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    public NovelFolder() {

    }

    public NovelFolder(String folderName, List<FolderItem> items) {
        this.folderName = folderName;
        this.items = items;
    }

    public UUID getId() {
        return id;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public List<FolderItem> getItems() {
        return items;
    }

    public void setItems(List<FolderItem> items) {
        this.items = items;
    }

    public void addItem(FolderItem item) {
        items.add(item);
        item.setFolder(this);
    }

    public void removeItem(FolderItem item) {
        items.remove(item);
        item.setFolder(null);
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @PrePersist
    void setCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }
}
