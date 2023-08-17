package pl.ogarnizer.infrastructure.ogarnizerAPI;

import org.springframework.stereotype.Component;
import pl.ogarnizer.domain.Stage;
import pl.ogarnizer.infrastructure.ogarnizerAPI.model.StageDTO;

@Component
public class StageAPIMapper {

    public Stage map(StageDTO stageDTO){
        return Stage.builder()
                .stageId(stageDTO.getStageId())
                .name(stageDTO.getName())
                .build();
    }
}
