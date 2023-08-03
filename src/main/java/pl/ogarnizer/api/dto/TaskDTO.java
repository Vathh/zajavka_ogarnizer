package pl.ogarnizer.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {

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
