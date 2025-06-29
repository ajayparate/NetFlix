package com.netflix.clone.recommendation.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.clone.recommendation.dto.HistoryDTO;
import com.netflix.clone.recommendation.dto.VideoDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final RestTemplate restTemplate;

    public List<VideoDTO> getRecommendations(Long userId){
        //Step 1: Get user watch history
        HistoryDTO[] history = restTemplate.getForObject("http://watch-history-service/api/history/user/"+userId,
         HistoryDTO[].class);

         if (history == null || history.length == 0) 
         {
            //fallback - trending videos
            return getTrendingVideos();
            
         }

         //Step 2: Count genre frequency
         Map<String, Long> genreFrequency = Arrays.stream(history)
                    .collect(Collectors.groupingBy(HistoryDTO::getGenre
                    , Collectors.counting()));

        //Step 3: Find top genre
        List<String> topGenre = genreFrequency
                    .entrySet().stream()
                    .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                    .limit(2)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());
                    
        List<VideoDTO> recommend = new ArrayList<>();
        for(String genre : topGenre){
            VideoDTO[] genreVideos = restTemplate.getForObject("http://video-service/api/videos.genre/"+ genre, VideoDTO[].class);

            if (genreVideos != null) {
                recommend.addAll(Arrays.asList(genreVideos));
            }
        }
        return recommend;
    }

    private List<VideoDTO> getTrendingVideos(){
        VideoDTO[] trending = restTemplate.getForObject(
            "http://video-service/api/videos/trending", VideoDTO[].class);

        return trending != null ? Arrays.asList(trending) : new ArrayList<>();
    }
}
