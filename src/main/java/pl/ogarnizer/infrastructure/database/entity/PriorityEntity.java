package pl.ogarnizer.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "priorityId")
@ToString(of = {"priorityId", "name"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ogarnizer_priority")
public class PriorityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "priority_id")
    private Integer priorityId;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "priority")
    private Set<OrderEntity> orders;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "priority")
    private Set<ServiceEntity> services;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "priority")
    private Set<AwayWorkEntity> awayWorks;
}
