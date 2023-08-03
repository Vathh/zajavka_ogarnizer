package pl.ogarnizer.domain;

import lombok.*;

import java.time.LocalDateTime;

@Value
@Builder
@With
@EqualsAndHashCode(of = "awayWorkId")
@ToString(of = {"awayWorkId", "createdDate", "description", "place", "device"})
public class AwayWork {

    Integer awayWorkId;
    User creatingUser;
    LocalDateTime createdDate;
    Priority priority;
    Client client;
    String description;
    String place;
    String device;
    String additionalInfo;
    String updateInfo;
    Stage stage;
}
