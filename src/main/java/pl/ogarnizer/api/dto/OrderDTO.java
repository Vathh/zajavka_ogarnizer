package pl.ogarnizer.api.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Data
@With
@Builder
@EqualsAndHashCode(of = {"creatingUserName", "priorityName", "clientName",
        "description", "device", "additionalInfo", "updateInfo", "stageName"})
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private Integer orderId;
    private String creatingUserName;
    private LocalDateTime createdDate;
    private String priorityName;
    private String clientName;
    @Length(max = 100)
    private String description;
    @Length(max = 50)
    private String device;
    @Length(max = 100)
    private String additionalInfo;
    @Length(max = 100)
    private String updateInfo;
    private String stageName;
}
