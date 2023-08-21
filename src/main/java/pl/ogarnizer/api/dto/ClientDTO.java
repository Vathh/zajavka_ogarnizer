package pl.ogarnizer.api.dto;

import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Getter
@Setter
@EqualsAndHashCode(of = "nip")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {

    Integer clientId;
    @Length(max = 100)
    String name;
    @Length(max = 100)
    String address;
    @Pattern(regexp = "^\\d{3}\\s\\d{3}\\s\\d{2}\\s\\d{2}$", message = "Invalid nip")
    String nip;
    @Pattern(regexp = "^\\d{3}\\s\\d{3}\\s\\d{3}$", message = "Invalid phone number")
    String phoneNumber;

    public Map<String, String> asMap(){
        Map<String, String> result = new HashMap<>();
        Optional.ofNullable(name).ifPresent(value -> result.put("name", value));
        Optional.ofNullable(address).ifPresent(value -> result.put("address", value));
        Optional.ofNullable(nip).ifPresent(value -> result.put("nip", value));
        Optional.ofNullable(phoneNumber).ifPresent(value -> result.put("phoneNumber", value));
        return result;
    }
}
