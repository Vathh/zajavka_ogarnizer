package pl.ogarnizer.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StageDTO {

    String name;

    public static StageDTO buildDefault(){
        return StageDTO.builder().name("just_added").build();
    }
}
