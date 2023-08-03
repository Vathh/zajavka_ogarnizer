package pl.ogarnizer.domain;

import lombok.*;

import java.time.LocalDateTime;

@Value
@With
@Builder
@EqualsAndHashCode(of = "orderId")
@ToString(of = {"orderId", "createdDate", "description", "device"})
public class Order {

    Integer orderId;
    User creatingUser;
    LocalDateTime createdDate;
    Priority priority;
    Client client;
    String description;
    String device;
    String additionalInfo;
    String updateInfo;
    Stage stage;
}
