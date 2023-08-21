package pl.ogarnizer.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;
import pl.ogarnizer.infrastructure.security.UserEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(of = "closedServiceId")
@ToString(of = {"closedServiceId", "createdDate", "description", "device", "closedDate", "success"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ogarnizer_closed_service")
public class ClosedServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "closed_service_id")
    private Integer closedServiceId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "created_by_user_id")
    private UserEntity creatingUser;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private ClientEntity client;

    @Column(name = "description")
    private String description;

    @Column(name = "device")
    private String device;

    @Column(name = "additional_info")
    private String additionalInfo;

    @Column(name = "update_info")
    private String updateInfo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "closed_by_user_id")
    private UserEntity closingUser;

    @Column(name = "closed_date")
    private LocalDateTime closedDate;

    @Column(name = "success")
    private boolean success;
}
