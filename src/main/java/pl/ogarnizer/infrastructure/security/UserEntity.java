package pl.ogarnizer.infrastructure.security;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.ogarnizer.infrastructure.database.entity.*;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ogarnizer_user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "active")
    private Boolean active;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "ogarnizer_user_role",
            joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleEntity> roles;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "creatingUser")
    private Set<AwayWorkEntity> awayWorks;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "creatingUser")
    private Set<OrderEntity> orders;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "creatingUser")
    private Set<ServiceEntity> services;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "creatingUser")
    private Set<ClosedAwayWorkEntity> closedAwayWorksCreated;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "creatingUser")
    private Set<ClosedOrderEntity> closedOrdersCreated;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "creatingUser")
    private Set<ClosedServiceEntity> closedServicesCreated;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "closingUser")
    private Set<ClosedAwayWorkEntity> closedAwayWorksClosed;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "closingUser")
    private Set<ClosedOrderEntity> closedOrdersClosed;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "closingUser")
    private Set<ClosedServiceEntity> closedServicesClosed;
}
