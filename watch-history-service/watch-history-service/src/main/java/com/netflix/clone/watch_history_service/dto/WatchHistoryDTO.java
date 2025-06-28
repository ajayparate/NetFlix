package com.netflix.clone.watch_history_service.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WatchHistoryDTO {

    private Long id;
    private Long userId;
    private Long videoId;
    private Integer progressSeconds;
    private Boolean completed;
    private LocalDateTime lastWatched; // ISO 8601 format for date-time
}
