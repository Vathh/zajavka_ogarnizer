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
public class ServiceDTO {

    private Integer serviceId;
    private String creatingUserName;
    private LocalDateTime createdDate;
    private String priorityName;
    private String clientName;
    private String description;
    private String device;
    private String additionalInfo;
    private String updateInfo;
    private String stageName;
}
