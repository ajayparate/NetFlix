package com.netflix.clone.watch_history_service.model;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WatchHistory {

    private Long id;

    private Long userId;

    private Long videoId;

    private Boolean completed;

    private Integer progressSeconds;

    private LocalDateTime lastWatched;
}
