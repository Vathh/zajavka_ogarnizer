package pl.ogarnizer.domain;

import lombok.*;

import java.time.LocalDateTime;

@Value
@Builder
@With
@EqualsAndHashCode(of = "closedServiceId")
@ToString(of = {"closedServiceId", "createdDate", "description", "device", "closedDate", "success"})
public class ClosedService {

    Integer closedServiceId;
    User creatingUser;
    LocalDateTime createdDate;
    Client client;
    String description;
    String device;
    String additionalInfo;
    String updateInfo;
    User closingUser;
    LocalDateTime closedDate;
    boolean success;
}
