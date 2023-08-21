package pl.ogarnizer.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;
import pl.ogarnizer.infrastructure.security.UserEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(of = "serviceId")
@ToString(of = {"serviceId", "createdDate", "description", "device"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ogarnizer_service")
public class ServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private Integer serviceId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "created_by_user_id")
    private UserEntity creatingUser;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "priority_id")
    private PriorityEntity priority;

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
    @JoinColumn(name = "stage_id")
    private StageEntity stage;
}
