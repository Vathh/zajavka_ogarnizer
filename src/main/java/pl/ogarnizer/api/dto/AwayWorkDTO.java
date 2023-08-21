package pl.ogarnizer.api.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Data
@With
@EqualsAndHashCode(of = {"creatingUserName", "priorityName", "clientName",
        "description", "place", "device", "additionalInfo", "updateInfo", "stageName"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AwayWorkDTO {

    private Integer awayWorkId;
    private String creatingUserName;
    private LocalDateTime createdDate;
    private String priorityName;
    private String clientName;
    @Length(max = 100)
    private String description;
    @Length(max = 100)
    private String place;
    @Length(max = 50)
    private String device;
    @Length(max = 100)
    private String additionalInfo;
    @Length(max = 100)
    private String updateInfo;
    private String stageName;
}
