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

    Integer taskId;
    String createdByUserName;
    LocalDateTime createdDate;
    @Pattern(regexp = "low|medium|high", flags = Pattern.Flag.CASE_INSENSITIVE)
    String priorityName;
    String clientName;
    @Length(max = 100)
    String description;
    @Length(max = 100)
    String place;
    @Length(max = 100)
    String device;
    @Length(max = 100)
    String additionalInfo;
    @Length(max = 100)
    String updateInfo;
    @Pattern(regexp = "just_added|in_progress|waiting_for_parts|to_invoice", flags = Pattern.Flag.CASE_INSENSITIVE)
    String stageName;
    String closingUserName;
    LocalDateTime closedDate;
    Boolean success;
}
