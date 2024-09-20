package com.example.server.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Mission extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long missionId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private MissionCategory missionCategory;

    public Mission(Long missionId, String title, String content, String imageUrl,
        MissionCategory missionCategory) {
        this.missionId = missionId;
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.missionCategory = missionCategory;
    }

    public Mission() {
    }

    public Long getMissionId() {
        return missionId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public MissionCategory getMissionCategory() {
        return missionCategory;
    }
}
