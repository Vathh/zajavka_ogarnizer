package pl.ogarnizer.domain;

import lombok.*;

import java.time.LocalDateTime;

@Value
@Builder
@With
@EqualsAndHashCode(of = "closedOrderId")
@ToString(of = {"closedOrderId", "createdDate", "description", "device", "closedDate", "success"})
public class ClosedOrder {

    Integer closedOrderId;
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
