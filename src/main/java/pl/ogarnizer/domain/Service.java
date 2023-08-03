package pl.ogarnizer.domain;

import lombok.*;

import java.time.LocalDateTime;

@Value
@Builder
@With
@EqualsAndHashCode(of = "serviceId")
@ToString(of = {"serviceId", "createdDate", "description", "device"})
public class Service {

    Integer serviceId;
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
