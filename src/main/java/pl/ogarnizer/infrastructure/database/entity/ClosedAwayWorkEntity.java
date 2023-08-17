package pl.ogarnizer.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pl.ogarnizer.infrastructure.security.UserEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(of = "closedAwayWorkId")
@ToString(of = {"closedAwayWorkId", "createdDate", "description", "place", "device", "closedDate", "success"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ogarnizer_closed_away_work")
public class ClosedAwayWorkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "closed_away_work_id")
    private Integer closedAwayWorkId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_user_id")
    private UserEntity creatingUser;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private ClientEntity client;

    @Column(name = "description")
    private String description;

    @Column(name = "place")
    private String place;

    @Column(name = "device")
    private String device;

    @Column(name = "additional_info")
    private String additionalInfo;

    @Column(name = "update_info")
    private String updateInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "closed_by_user_id")
    private UserEntity closingUser;

    @Column(name = "closed_date")
    private LocalDateTime closedDate;

    @Column(name = "success")
    private boolean success;
}
