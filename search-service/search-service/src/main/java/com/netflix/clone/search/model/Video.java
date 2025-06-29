package com.netflix.clone.search.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Video {

    @Id
    private String id;
    private String title;
    private String genre;
    private String actors;
}
