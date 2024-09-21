package com.example.server.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tile_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    @Column(name = "order")
    private Integer order;

    @Column(name = "state")
    private Boolean state;

    public void changeState(Boolean state){
        this.state = state;
    }

    public void changeMission(Mission mission){
        this.mission = mission;
    }
}