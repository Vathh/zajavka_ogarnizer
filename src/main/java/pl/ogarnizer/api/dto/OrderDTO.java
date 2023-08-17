package pl.ogarnizer.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Data
@Builder
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
