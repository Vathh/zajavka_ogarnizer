package pl.ogarnizer.api.dto;

import jakarta.validation.constraints.Pattern;
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
public class TaskDTO {

    private Integer taskId;
    private String createdByUserName;
    private LocalDateTime createdDate;
    @Pattern(regexp = "low|medium|high", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String priorityName;
    private String clientName;
    @Length(max = 100)
    private String description;
    @Length(max = 100)
    private String place;
    @Length(max = 100)
    private String device;
    @Length(max = 100)
    private String additionalInfo;
    @Length(max = 100)
    private String updateInfo;
    @Pattern(regexp = "just_added|in_progress|waiting_for_parts|to_invoice", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String stageName;
    private String closingUserName;
    private LocalDateTime closedDate;
    private Boolean success;
}
