package com.github.juandavh.webnoveltracker.novel;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name="novels")
public class Novel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String title;

    private String author;
    private String description;
    private int totalChapters;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    public Novel() {
    }

    public Novel(UUID id, String title, String author, String description, int totalChapters) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.totalChapters = totalChapters;
        this.createdAt = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTotalChapters() {
        return totalChapters;
    }

    public void setTotalChapters(int totalChapters) {
        this.totalChapters = totalChapters;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Novel novel = (Novel) o;
        return totalChapters == novel.totalChapters && Objects.equals(id, novel.id) && Objects.equals(title, novel.title) && Objects.equals(author, novel.author) && Objects.equals(description, novel.description) && Objects.equals(createdAt, novel.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, description, totalChapters, createdAt);
    }
}
