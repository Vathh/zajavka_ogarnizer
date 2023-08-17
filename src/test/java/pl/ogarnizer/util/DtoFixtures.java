package pl.ogarnizer.util;


import pl.ogarnizer.api.dto.*;
import pl.ogarnizer.api.rest.dto.*;

import java.time.LocalDateTime;
import java.util.List;

public class DtoFixtures {
    public static AwayWorkDTO someAwayWorkDTO1(){
        return AwayWorkDTO.builder()
                .creatingUserName(someUserDTO1().getUserName())
                .createdDate(LocalDateTime.of(2020,10,20,10,20,20))
                .priorityName(lowPriority().getName())
                .clientName(someClientDTO1().getName())
                .description("opis")
                .place("miejsce")
                .device("urzadzenie")
                .additionalInfo("dodatkowe informacje")
                .updateInfo("informacje o aktualizacji")
                .stageName(someStage1().getName())
                .build();
    }

    public static AwayWorkDTO someAwayWorkDTO2(){
        return AwayWorkDTO.builder()
                .creatingUserName(someUserDTO2().getUserName())
                .createdDate(LocalDateTime.of(2020,10,20,10,20,20))
                .priorityName(lowPriority().getName())
                .clientName(someClientDTO2().getName())
                .description("opis")
                .place("miejsce")
                .device("urzadzenie")
                .additionalInfo("dodatkowe informacje")
                .updateInfo("informacje o aktualizacji")
                .stageName(someStage2().getName())
                .build();
    }

    public static AwayWorkDTO someAwayWorkDTO3(){
        return AwayWorkDTO.builder()
                .creatingUserName(someUserDTO3().getUserName())
                .createdDate(LocalDateTime.of(2020,10,20,10,20,20))
                .priorityName(lowPriority().getName())
                .clientName(someClientDTO3().getName())
                .description("opis")
                .place("miejsce")
                .device("urzadzenie")
                .additionalInfo("dodatkowe informacje")
                .updateInfo("informacje o aktualizacji")
                .stageName(someStage3().getName())
                .build();
    }

    public static AwayWorksDTO someAwayWorksDTO(){
        return AwayWorksDTO.builder()
                .awayWorks(List.of(someAwayWorkDTO1(), someAwayWorkDTO1(), someAwayWorkDTO1()))
                .build();
    }

    public static OrderDTO someOrderDTO1(){
        return OrderDTO.builder()
                .creatingUserName(someUserDTO3().getUserName())
                .createdDate(LocalDateTime.of(2020,10,20,10,20,20))
                .priorityName(lowPriority().getName())
                .clientName(someClientDTO3().getName())
                .description("opis")
                .device("urzadzenie")
                .additionalInfo("dodatkowe informacje")
                .updateInfo("informacje o aktualizacji")
                .stageName(someStage3().getName())
                .build();
    }

    public static OrdersDTO someOrdersDTO(){
        return OrdersDTO.builder()
                .orders(List.of(someOrderDTO1(), someOrderDTO1(), someOrderDTO1()))
                .build();
    }

    public static ServiceDTO someServiceDTO1(){
        return ServiceDTO.builder()
                .creatingUserName(someUserDTO3().getUserName())
                .createdDate(LocalDateTime.of(2020,10,20,10,20,20))
                .priorityName(lowPriority().getName())
                .clientName(someClientDTO3().getName())
                .description("opis")
                .device("urzadzenie")
                .additionalInfo("dodatkowe informacje")
                .updateInfo("informacje o aktualizacji")
                .stageName(someStage3().getName())
                .build();
    }

    public static ServicesDTO someServicesDTO(){
        return ServicesDTO.builder()
                .services(List.of(someServiceDTO1(), someServiceDTO1(), someServiceDTO1()))
                .build();
    }

    public static UserDTO someUserDTO1(){
        return UserDTO.builder()
                .userName("Marian")
                .password("marian123")
                .roles(List.of(servicemanRole().getRole()))
                .build();
    }

    public static UserDTO someUserDTO2(){
        return UserDTO.builder()
                .userName("Darek")
                .password("darek123")
                .roles(List.of(servicemanRole().getRole()))
                .build();
    }

    public static UserDTO someUserDTO3(){
        return UserDTO.builder()
                .userName("Marek")
                .password("marek123")
                .roles(List.of(servicemanRole().getRole()))
                .build();
    }

    public static UsersDTO someUsersDTO(){
        return UsersDTO.builder()
                .users(List.of(someUserDTO1(), someUserDTO1(), someUserDTO1()))
                .build();
    }

    public static RoleDTO servicemanRole(){
        return RoleDTO.builder()
                .role("serviceman")
                .build();
    }

    public static RoleDTO adminRole(){
        return RoleDTO.builder()
                .role("admin")
                .build();
    }

    public static PriorityDTO lowPriority(){
        return PriorityDTO.builder()
                .name("low")
                .build();
    }

    public static PriorityDTO mediumPriority(){
        return PriorityDTO.builder()
                .name("medium")
                .build();
    }

    public static PriorityDTO highPriority(){
        return PriorityDTO.builder()
                .name("high")
                .build();
    }

    public static StageDTO someStage1(){
        return StageDTO.builder()
                .name("just_added")
                .build();
    }

    public static StageDTO someStage2(){
        return StageDTO.builder()
                .name("in_progress")
                .build();
    }

    public static StageDTO someStage3(){
        return StageDTO.builder()
                .name("waiting_for_parts")
                .build();
    }

    public static StageDTO someStage4(){
        return StageDTO.builder()
                .name("to_invoice")
                .build();
    }

    public static ClientDTO someClientDTO1(){
        return ClientDTO.builder()
                .name("Magda")
                .address("Poznanska 15 Warszawa")
                .nip("543 345 34 32")
                .phoneNumber("123 456 789")
                .build();
    }

    public static ClientDTO someClientDTO2(){
        return ClientDTO.builder()
                .name("Natalia")
                .address("Warszawska 15 Warszawa")
                .nip("654 345 23 12")
                .phoneNumber("456 798 123")
                .build();
    }

    public static ClientDTO someClientDTO3(){
        return ClientDTO.builder()
                .name("Magda")
                .address("Gdanska 15 Gdansk")
                .nip("654 321 23 45")
                .phoneNumber("789 123 456")
                .build();
    }

    public static ClientsDTO someClientsDTO(){
        return ClientsDTO.builder()
                .clients(List.of(someClientDTO1(), someClientDTO1(), someClientDTO1()))
                .build();
    }

    public static ClosedAwayWorkDTO someClosedAwayWorkDTO1(){
        return ClosedAwayWorkDTO.builder()
                .creatingUserName(someUserDTO1().getUserName())
                .createdDate(LocalDateTime.of(2020,10,20,10,20,20))
                .clientName(someClientDTO1().getName())
                .description("opis")
                .place("miejsce")
                .device("urzadzenie")
                .additionalInfo("dodatkowe informacje")
                .updateInfo("informacje o aktualizacji")
                .closingUserName(someUserDTO1().getUserName())
                .closedDate(LocalDateTime.of(2020,10,20,10,20,20))
                .build();
    }

    public static ClosedAwayWorksDTO someClosedAwayWorksDTO(){
        return ClosedAwayWorksDTO.builder()
                .closedAwayWorks(List.of(someClosedAwayWorkDTO1(), someClosedAwayWorkDTO1(), someClosedAwayWorkDTO1()))
                .build();
    }

    public static ClosedOrderDTO someClosedOrderDTO1(){
        return ClosedOrderDTO.builder()
                .creatingUserName(someUserDTO1().getUserName())
                .createdDate(LocalDateTime.of(2020,10,20,10,20,20))
                .clientName(someClientDTO1().getName())
                .description("opis")
                .device("urzadzenie")
                .additionalInfo("dodatkowe informacje")
                .updateInfo("informacje o aktualizacji")
                .closingUserName(someUserDTO1().getUserName())
                .closedDate(LocalDateTime.of(2020,10,20,10,20,20))
                .build();
    }

    public static ClosedOrdersDTO someClosedOrdersDTO(){
        return ClosedOrdersDTO.builder()
                .closedOrders(List.of(someClosedOrderDTO1(), someClosedOrderDTO1(), someClosedOrderDTO1()))
                .build();
    }

    public static ClosedServiceDTO someClosedServiceDTO1(){
        return ClosedServiceDTO.builder()
                .creatingUserName(someUserDTO1().getUserName())
                .createdDate(LocalDateTime.of(2020,10,20,10,20,20))
                .clientName(someClientDTO1().getName())
                .description("opis")
                .device("urzadzenie")
                .additionalInfo("dodatkowe informacje")
                .updateInfo("informacje o aktualizacji")
                .closingUserName(someUserDTO1().getUserName())
                .closedDate(LocalDateTime.of(2020,10,20,10,20,20))
                .build();
    }

    public static ClosedServicesDTO someClosedServicesDTO(){
        return ClosedServicesDTO.builder()
                .closedServices(List.of(someClosedServiceDTO1(), someClosedServiceDTO1(), someClosedServiceDTO1()))
                .build();
    }
}
