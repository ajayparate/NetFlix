package com.netflix.clone.search.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.netflix.clone.search.model.Video;
import com.netflix.clone.search.repository.VideoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SearchService {
    
    private VideoRepository videoRepository;

    public List<String> search(String keyWord){
        List<Video> byTitle = videoRepository.findByTitleContainingIgnoreCase(keyWord);
        List<Video> byGenre = videoRepository.findByGenreContainingIgnoreCase(keyWord);
        List<Video> byActors = videoRepository.findByActorsContainingIgnoreCase(keyWord);
        
        return Stream.<List<Video>>of(byTitle, byGenre, byActors)
                .flatMap(Collection::stream)
                .map(Video::getTitle)
                .distinct()
                .collect(Collectors.toList());
    }


}
