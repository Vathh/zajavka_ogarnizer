package pl.ogarnizer.api.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@With
@NoArgsConstructor
@AllArgsConstructor
public class ClosedServiceDTO {

    private Integer closedServiceId;
    private String creatingUserName;
    private LocalDateTime createdDate;
    private String clientName;
    private String description;
    private String device;
    private String additionalInfo;
    private String updateInfo;
    private String closingUserName;
    private LocalDateTime closedDate;
    private String success;
}
