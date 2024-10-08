package com.example.server.entity;

import com.example.server.dto.MemberDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column
    private Long point;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<CompletedMission> completedMissions;

    public MemberDTO toDTO() {
        return new MemberDTO(memberId, email, password, name, point, role);
    }

    public void addCompletedMission(CompletedMission mission) {
        this.completedMissions.add(mission);
    }

    public Boolean isCompletedMission(Mission mission) {
        return this.completedMissions.stream().anyMatch(o -> o.getMission().equals(mission));
    }
}
