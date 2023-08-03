package pl.ogarnizer.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

@Value
@Builder
@EqualsAndHashCode(of = "nip")
@ToString(of = {"clientId", "name", "address", "nip", "phoneNumber"})
public class Client {

    Integer clientId;
    String name;
    String address;
    String nip;
    String phoneNumber;
}
