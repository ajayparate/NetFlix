package com.netflix.clone.video_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Video {

    private Long id;

    private String title;
    private String description;
    private String genre;
    private String director;
    private String cast;
    private String releaseDate;
    private String thumbnailUrl;
    private String videoUrl;
    private String duration;
    private String rating;
    private String language;
    private String contentType; // e.g., Movie, TV Show, Documentary
    private String status; // e.g., Available, Coming Soon, Unavailable
    private String createdBy;
    private String updatedBy;
    // private String createdAt;
    // @ManyToOne()
    private Long uploadedBy;


}
