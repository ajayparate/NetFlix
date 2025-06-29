package com.netflix.clone.search.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.netflix.clone.search.model.Video;

public interface VideoRepository extends JpaRepository<Video, String >{
    List<Video> findByTitleContainingIgnoreCase(String title);
    List<Video> findByGenreContainingIgnoreCase(String genre);
    List<Video> findByActorsContainingIgnoreCase(String actors);


}
