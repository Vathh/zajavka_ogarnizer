package pl.ogarnizer.infrastructure.ogarnizerAPI;

import org.springframework.stereotype.Component;
import pl.ogarnizer.domain.Client;
import pl.ogarnizer.infrastructure.ogarnizerAPI.model.ClientDTO;

@Component
public class ClientAPIMapper {

    public Client map(ClientDTO clientDTO){
        return Client.builder()
                .clientId(clientDTO.getClientId())
                .name(clientDTO.getName())
                .address(clientDTO.getAddress())
                .nip(clientDTO.getNip())
                .phoneNumber(clientDTO.getPhoneNumber())
                .build();
    }
}
