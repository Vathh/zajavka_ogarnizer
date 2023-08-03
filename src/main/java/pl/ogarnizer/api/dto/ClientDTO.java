package pl.ogarnizer.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {

    Integer clientId;
    String name;
    String address;
    String nip;
    String phoneNumber;
}
