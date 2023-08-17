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
public class ClientDTO {

    Integer clientId;
    @Length(max = 100)
    String name;
    @Length(max = 100)
    String address;
    @Pattern(regexp = "^\\d{3}\\s\\d{3}\\s\\d{2}\\s\\d{2}$")
    String nip;
    @Pattern(regexp = "^\\d{3}\\s\\d{3}\\s\\d{3}$")
    String phoneNumber;
}
