package pl.ogarnizer.domain;

import lombok.*;

import java.time.LocalDateTime;

@Value
@Builder
@With
@EqualsAndHashCode(of = "closedAwayWorkId")
@ToString(of = {"closedAwayWorkId", "createdDate", "description", "place", "device", "closedDate", "success"})
public class ClosedAwayWork {

    Integer closedAwayWorkId;
    User creatingUser;
    LocalDateTime createdDate;
    Client client;
    String description;
    String place;
    String device;
    String additionalInfo;
    String updateInfo;
    User closingUser;
    LocalDateTime closedDate;
    boolean success;
}
