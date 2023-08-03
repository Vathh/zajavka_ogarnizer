package pl.ogarnizer.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
@EqualsAndHashCode(of = {"createdByUserName", "createdDate"})
@ToString(of = {"createdByUserName", "createdDate", "clientName", "description", "place", "device"})
public class Task {

    Integer taskId;
    String createdByUserName;
    LocalDateTime createdDate;
    String priorityName;
    String clientName;
    String description;
    String place;
    String device;
    String additionalInfo;
    String updateInfo;
    String stageName;
    String closingUserName;
    LocalDateTime closedDate;
    String success;
}
