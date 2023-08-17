package pl.ogarnizer.api.dto;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTaskDTO {

    private Integer updateTaskId;
    @Pattern(regexp = "low|medium|high", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String priorityName;
    @Length(max = 100)
    private String updateInfo;
    @Pattern(regexp = "just_added|in_progress|waiting_for_parts|to_invoice", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String stageName;
}
