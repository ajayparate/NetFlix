package com.netflix.clone.search.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.netflix.clone.search.model.Video;
import com.netflix.clone.search.service.SearchService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping
    public ResponseEntity<List<String>> search(@RequestParam String keyword) {
        return ResponseEntity.ok(searchService.search(keyword));
    }
    
    // @GetMapping
    // public ResponseEntity<List<Video>> search(@RequestParam String keyword) {
    //     return ResponseEntity.ok(searchService.search(keyword));
    // }


}
