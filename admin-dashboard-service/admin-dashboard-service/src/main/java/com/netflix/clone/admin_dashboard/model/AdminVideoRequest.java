package com.netflix.clone.admin_dashboard.model;

// import jakarta.annotation.sql.DataSourceDefinition;
// import lombok.Builder;
// import lombok.Data;

// @Data
// @Builder
// @AllArgsConstructor
// @NoArgsConstructor
public class AdminVideoRequest {

    private String videoId;
    private String title;
    private String genre;
    private String actors;
    private String description;

    public AdminVideoRequest() {
    }
    public AdminVideoRequest(String videoId, String title, String genre, String actors, String description) {
        this.videoId = videoId;
        this.title = title;
        this.genre = genre;
        this.actors = actors;
        this.description = description;
    }
    public String getVideoId() {
        return videoId;
    }
    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public String getActors() {
        return actors;
    }
    public void setActors(String actors) {
        this.actors = actors;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

}
