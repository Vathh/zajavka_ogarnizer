package pl.ogarnizer.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = {"clientId"})
@ToString(of = {"clientId", "name", "address", "nip", "phoneNumber"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ogarnizer_client")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Integer clientId;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "nip")
    private String nip;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    private Set<AwayWorkEntity> awayWorks;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    private Set<ClosedAwayWorkEntity> closedAwayWorks;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    private Set<ClosedOrderEntity> closedOrders;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    private Set<ClosedServiceEntity> closedServices;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    private Set<OrderEntity> orders;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    private Set<ServiceEntity> services;
}
