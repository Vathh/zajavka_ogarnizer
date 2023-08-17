package pl.ogarnizer.infrastructure.ogarnizerAPI;

import org.springframework.stereotype.Component;
import pl.ogarnizer.domain.Priority;
import pl.ogarnizer.infrastructure.ogarnizerAPI.model.PriorityDTO;

@Component
public class PriorityAPIMapper {

    public Priority map(PriorityDTO priorityDTO){
        return Priority.builder()
                .priorityId(priorityDTO.getPriorityId())
                .name(priorityDTO.getName())
                .build();
    }
}
