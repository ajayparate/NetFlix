package com.netflix.clone.recommendation.dto;

import lombok.Data;

@Data
public class HistoryDTO {

    private Long userId;

    private Long videoId;

    private String genre;
}
