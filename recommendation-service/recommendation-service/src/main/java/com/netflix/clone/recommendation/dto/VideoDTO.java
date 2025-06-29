package com.netflix.clone.recommendation.dto;

import lombok.Data;

@Data
public class VideoDTO {

    private Long id;

    private String title;

    private String genre;

    private String description;

    private String thumbnailUrl;
}
