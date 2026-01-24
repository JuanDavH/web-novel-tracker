package com.github.juandavh.webnoveltracker.novelfolder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NovelFolderRepository extends JpaRepository<NovelFolder, UUID> {

}
