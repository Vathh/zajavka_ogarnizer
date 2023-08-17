package pl.ogarnizer.util;

import pl.ogarnizer.infrastructure.database.entity.*;
import pl.ogarnizer.infrastructure.security.RoleEntity;
import pl.ogarnizer.infrastructure.security.UserEntity;

import java.time.LocalDateTime;
import java.util.Set;


public class EntityFixtures {

    public static AwayWorkEntity someAwayWorkEntity1(){
        return AwayWorkEntity.builder()
                .creatingUser(someUserEntity1())
                .createdDate(LocalDateTime.of(2020,10,20,10,20,20))
                .priority(lowPriorityEntity())
                .client(someClientEntity1())
                .description("opis")
                .place("miejsce")
                .device("urzadzenie")
                .additionalInfo("dodatkowe informacje")
                .updateInfo("informacje o aktualizacji")
                .stage(someStageEntity1())
                .build();
    }

    public static AwayWorkEntity someAwayWorkEntity2(){
        return AwayWorkEntity.builder()
                .creatingUser(someUserEntity2())
                .createdDate(LocalDateTime.of(2020,10,20,10,20,20))
                .priority(lowPriorityEntity())
                .client(someClientEntity2())
                .description("opis")
                .place("miejsce")
                .device("urzadzenie")
                .additionalInfo("dodatkowe informacje")
                .updateInfo("informacje o aktualizacji")
                .stage(someStageEntity2())
                .build();
    }

    public static AwayWorkEntity someAwayWorkEntity3(){
        return AwayWorkEntity.builder()
                .creatingUser(someUserEntity3())
                .createdDate(LocalDateTime.of(2020,10,20,10,20,20))
                .priority(lowPriorityEntity())
                .client(someClientEntity3())
                .description("opis")
                .place("miejsce")
                .device("urzadzenie")
                .additionalInfo("dodatkowe informacje")
                .updateInfo("informacje o aktualizacji")
                .stage(someStageEntity3())
                .build();
    }

    public static OrderEntity someOrderEntity1(){
        return OrderEntity.builder()
                .creatingUser(someUserEntity1())
                .createdDate(LocalDateTime.of(2020,10,20,10,20,20))
                .priority(lowPriorityEntity())
                .client(someClientEntity1())
                .description("opis")
                .device("urzadzenie")
                .additionalInfo("dodatkowe informacje")
                .updateInfo("informacje o aktualizacji")
                .stage(someStageEntity1())
                .build();
    }

    public static OrderEntity someOrderEntity2(){
        return OrderEntity.builder()
                .creatingUser(someUserEntity2())
                .createdDate(LocalDateTime.of(2020,10,20,10,20,20))
                .priority(mediumPriorityEntity())
                .client(someClientEntity2())
                .description("opis")
                .device("urzadzenie")
                .additionalInfo("dodatkowe informacje")
                .updateInfo("informacje o aktualizacji")
                .stage(someStageEntity2())
                .build();
    }

    public static OrderEntity someOrderEntity3(){
        return OrderEntity.builder()
                .creatingUser(someUserEntity3())
                .createdDate(LocalDateTime.of(2020,10,20,10,20,20))
                .priority(highPriorityEntity())
                .client(someClientEntity3())
                .description("opis")
                .device("urzadzenie")
                .additionalInfo("dodatkowe informacje")
                .updateInfo("informacje o aktualizacji")
                .stage(someStageEntity3())
                .build();
    }

    public static ServiceEntity someServiceEntity1(){
        return ServiceEntity.builder()
                .creatingUser(someUserEntity1())
                .createdDate(LocalDateTime.of(2020,10,20,10,20,20))
                .priority(lowPriorityEntity())
                .client(someClientEntity1())
                .description("opis")
                .device("urzadzenie")
                .additionalInfo("dodatkowe informacje")
                .updateInfo("informacje o aktualizacji")
                .stage(someStageEntity1())
                .build();
    }

    public static ServiceEntity someServiceEntity2(){
        return ServiceEntity.builder()
                .creatingUser(someUserEntity2())
                .createdDate(LocalDateTime.of(2020,10,20,10,20,20))
                .priority(mediumPriorityEntity())
                .client(someClientEntity2())
                .description("opis")
                .device("urzadzenie")
                .additionalInfo("dodatkowe informacje")
                .updateInfo("informacje o aktualizacji")
                .stage(someStageEntity2())
                .build();
    }

    public static ServiceEntity someServiceEntity3(){
        return ServiceEntity.builder()
                .creatingUser(someUserEntity3())
                .createdDate(LocalDateTime.of(2020,10,20,10,20,20))
                .priority(highPriorityEntity())
                .client(someClientEntity3())
                .description("opis")
                .device("urzadzenie")
                .additionalInfo("dodatkowe informacje")
                .updateInfo("informacje o aktualizacji")
                .stage(someStageEntity3())
                .build();
    }

    public static UserEntity someUserEntity1(){
        return UserEntity.builder()
                .userName("Waldek")
                .password("waldek123")
                .active(true)
                .roles(Set.of(servicemanRoleEntity()))
                .build();
    }

    public static UserEntity someUserEntity2(){
        return UserEntity.builder()
                .userName("Darek")
                .password("darek123")
                .active(true)
                .roles(Set.of(servicemanRoleEntity()))
                .build();
    }

    public static UserEntity someUserEntity3(){
        return UserEntity.builder()
                .userName("Marek")
                .password("marek123")
                .active(true)
                .roles(Set.of(servicemanRoleEntity()))
                .build();
    }

    public static RoleEntity servicemanRoleEntity(){
        return RoleEntity.builder()
                .roleId(1)
                .role("serviceman")
                .build();
    }

    public static RoleEntity adminRoleEntity(){
        return RoleEntity.builder()
                .roleId(2)
                .role("admin")
                .build();
    }

    public static PriorityEntity lowPriorityEntity(){
        return PriorityEntity.builder()
                .priorityId(1)
                .name("low")
                .build();
    }

    public static PriorityEntity mediumPriorityEntity(){
        return PriorityEntity.builder()
                .priorityId(2)
                .name("medium")
                .build();
    }

    public static PriorityEntity highPriorityEntity(){
        return PriorityEntity.builder()
                .priorityId(3)
                .name("high")
                .build();
    }

    public static StageEntity someStageEntity1(){
        return StageEntity.builder()
                .stageId(1)
                .name("just_added")
                .build();
    }

    public static StageEntity someStageEntity2(){
        return StageEntity.builder()
                .stageId(2)
                .name("in_progress")
                .build();
    }

    public static StageEntity someStageEntity3(){
        return StageEntity.builder()
                .stageId(3)
                .name("waiting_for_parts")
                .build();
    }

    public static StageEntity someStageEntity4(){
        return StageEntity.builder()
                .stageId(4)
                .name("to_invoice")
                .build();
    }

    public static ClientEntity someClientEntity1(){
        return ClientEntity.builder()
                .name("Magda")
                .address("Poznanska 15 Poznan")
                .nip("543 345 34 32")
                .phoneNumber("123 456 789")
                .build();
    }

    public static ClientEntity someClientEntity2(){
        return ClientEntity.builder()
                .name("Natalia")
                .address("Warszawska 15 Warszawa")
                .nip("654 345 23 12")
                .phoneNumber("456 798 123")
                .build();
    }

    public static ClientEntity someClientEntity3(){
        return ClientEntity.builder()
                .name("Magda")
                .address("Gdanska 15 Gdansk")
                .nip("654 321 23 45")
                .phoneNumber("789 123 456")
                .build();
    }

    public static ClosedAwayWorkEntity someClosedAwayWorkEntity1(){
        UserEntity userEntity = someUserEntity1();
        return ClosedAwayWorkEntity.builder()
                .creatingUser(userEntity)
                .createdDate(LocalDateTime.of(2020,10,20,10,20,20))
                .client(someClientEntity1())
                .description("opis")
                .place("miejsce")
                .device("urzadzenie")
                .additionalInfo("dodatkowe informacje")
                .updateInfo("informacje o aktualizacji")
                .closingUser(userEntity)
                .closedDate(LocalDateTime.of(2020,10,20,10,20,20))
                .build();
    }

    public static ClosedAwayWorkEntity someClosedAwayWorkEntity2(){
        UserEntity userEntity = someUserEntity2();
        return ClosedAwayWorkEntity.builder()
                .creatingUser(userEntity)
                .createdDate(LocalDateTime.of(2020,10,20,10,20,20))
                .client(someClientEntity2())
                .description("opis")
                .place("miejsce")
                .device("urzadzenie")
                .additionalInfo("dodatkowe informacje")
                .updateInfo("informacje o aktualizacji")
                .closingUser(userEntity)
                .closedDate(LocalDateTime.of(2020,10,20,10,20,20))
                .build();
    }

    public static ClosedAwayWorkEntity someClosedAwayWorkEntity3(){
        UserEntity userEntity = someUserEntity3();
        return ClosedAwayWorkEntity.builder()
                .creatingUser(userEntity)
                .createdDate(LocalDateTime.of(2020,10,20,10,20,20))
                .client(someClientEntity3())
                .description("opis")
                .place("miejsce")
                .device("urzadzenie")
                .additionalInfo("dodatkowe informacje")
                .updateInfo("informacje o aktualizacji")
                .closingUser(userEntity)
                .closedDate(LocalDateTime.of(2020,10,20,10,20,20))
                .build();
    }

    public static ClosedOrderEntity someClosedOrderEntity1(){
        UserEntity userEntity = someUserEntity1();
        return ClosedOrderEntity.builder()
                .creatingUser(userEntity)
                .createdDate(LocalDateTime.of(2020,10,20,10,20,20))
                .client(someClientEntity1())
                .description("opis")
                .device("urzadzenie")
                .additionalInfo("dodatkowe informacje")
                .updateInfo("informacje o aktualizacji")
                .closingUser(userEntity)
                .closedDate(LocalDateTime.of(2020,10,20,10,20,20))
                .build();
    }

    public static ClosedOrderEntity someClosedOrderEntity2(){
        UserEntity userEntity = someUserEntity2();
        return ClosedOrderEntity.builder()
                .creatingUser(userEntity)
                .createdDate(LocalDateTime.of(2020,10,20,10,20,20))
                .client(someClientEntity2())
                .description("opis")
                .device("urzadzenie")
                .additionalInfo("dodatkowe informacje")
                .updateInfo("informacje o aktualizacji")
                .closingUser(userEntity)
                .closedDate(LocalDateTime.of(2020,10,20,10,20,20))
                .build();
    }

    public static ClosedOrderEntity someClosedOrderEntity3(){
        UserEntity userEntity = someUserEntity3();
        return ClosedOrderEntity.builder()
                .creatingUser(userEntity)
                .createdDate(LocalDateTime.of(2020,10,20,10,20,20))
                .client(someClientEntity3())
                .description("opis")
                .device("urzadzenie")
                .additionalInfo("dodatkowe informacje")
                .updateInfo("informacje o aktualizacji")
                .closingUser(userEntity)
                .closedDate(LocalDateTime.of(2020,10,20,10,20,20))
                .build();
    }

    public static ClosedServiceEntity someClosedServiceEntity1(){
        UserEntity userEntity = someUserEntity1();
        return ClosedServiceEntity.builder()
                .creatingUser(userEntity)
                .createdDate(LocalDateTime.of(2020,10,20,10,20,20))
                .client(someClientEntity1())
                .description("opis")
                .device("urzadzenie")
                .additionalInfo("dodatkowe informacje")
                .updateInfo("informacje o aktualizacji")
                .closingUser(userEntity)
                .closedDate(LocalDateTime.of(2020,10,20,10,20,20))
                .build();
    }

    public static ClosedServiceEntity someClosedServiceEntity2(){
        UserEntity userEntity = someUserEntity2();
        return ClosedServiceEntity.builder()
                .creatingUser(userEntity)
                .createdDate(LocalDateTime.of(2020,10,20,10,20,20))
                .client(someClientEntity2())
                .description("opis")
                .device("urzadzenie")
                .additionalInfo("dodatkowe informacje")
                .updateInfo("informacje o aktualizacji")
                .closingUser(userEntity)
                .closedDate(LocalDateTime.of(2020,10,20,10,20,20))
                .build();
    }

    public static ClosedServiceEntity someClosedServiceEntity3(){
        UserEntity userEntity = someUserEntity3();
        return ClosedServiceEntity.builder()
                .creatingUser(userEntity)
                .createdDate(LocalDateTime.of(2020,10,20,10,20,20))
                .client(someClientEntity3())
                .description("opis")
                .device("urzadzenie")
                .additionalInfo("dodatkowe informacje")
                .updateInfo("informacje o aktualizacji")
                .closingUser(userEntity)
                .closedDate(LocalDateTime.of(2020,10,20,10,20,20))
                .build();
    }
}
