package pl.ogarnizer.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "stageId")
@ToString(of = {"stageId", "name"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ogarnizer_stage")
public class StageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stage_id")
    private Integer stageId;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "stage")
    private Set<OrderEntity> orders;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "stage")
    private Set<ServiceEntity> services;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "stage")
    private Set<AwayWorkEntity> awayWorks;
}
