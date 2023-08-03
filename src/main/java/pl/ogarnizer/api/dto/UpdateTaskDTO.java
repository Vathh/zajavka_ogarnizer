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
public class UpdateTaskDTO {

    private Integer updateTaskId;
    private String priorityName;
    private String updateInfo;
    private String stageName;
}
