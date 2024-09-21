package com.example.server.entity;

import static com.example.server.common.constant.Constant.BOARD_CYCLE;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column(name = "member_position")
    private Integer memberPosition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tile> tiles = new ArrayList<>();

    public Board(Long id, Integer memberPosition, Member member, List<Tile> tiles) {
        this.id = id;
        this.memberPosition = memberPosition;
        this.member = member;
        this.tiles = tiles;
    }

    public Board() {
    }

    public Long getId() {
        return id;
    }

    public Integer getMemberPosition() {
        return memberPosition;
    }

    public Member getMember() {
        return member;
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public void moveMemberPosition(Integer dice) {
        this.memberPosition = (this.memberPosition + dice) % BOARD_CYCLE;
    }

}