package com.netflix.clone.video_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VideoDTO {
    private Long id;

    private String title;
    private String description;
    private String genre;
    private String director;
    private String cast;
    private String language;
    private String thumbnailUrl;
    private String videoUrl;
    private String duration;
    private String rating;
    private String contentType; // e.g., Movie, TV Show, Documentary
    private String status; // e.g., Available, Coming Soon, Unavailable
    private Long uploadedBy;

}
