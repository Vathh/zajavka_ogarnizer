package pl.ogarnizer.api.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@With
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClosedAwayWorkDTO {

    private Integer closedAwayWorkId;
    private String creatingUserName;
    private LocalDateTime createdDate;
    private String clientName;
    private String description;
    private String place;
    private String device;
    private String additionalInfo;
    private String updateInfo;
    private String closingUserName;
    private LocalDateTime closedDate;
    private String success;
}
