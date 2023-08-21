package pl.ogarnizer.util;

import pl.ogarnizer.domain.*;

import java.time.LocalDateTime;
import java.util.List;

public class DomainFixtures {

    public static AwayWork someAwayWork1(){
        return AwayWork.builder()
                .creatingUser(someUser1())
                .createdDate(LocalDateTime.of(2020,10,20,10,20,20))
                .priority(lowPriority())
                .client(someClient1())
                .description("opis")
                .place("miejsce")
                .device("urzadzenie")
                .additionalInfo("dodatkowe informacje")
                .updateInfo("informacje o aktualizacji")
                .stage(someStage1())
                .build();
    }

    public static AwayWork someAwayWork2(){
        return AwayWork.builder()
                .creatingUser(someUser2())
                .createdDate(LocalDateTime.of(2020,10,20,10,20,20))
                .priority(lowPriority())
                .client(someClient2())
                .description("opis")
                .place("miejsce")
                .device("urzadzenie")
                .additionalInfo("dodatkowe informacje")
                .updateInfo("informacje o aktualizacji")
                .stage(someStage2())
                .build();
    }

    public static AwayWork someAwayWork3(){
        return AwayWork.builder()
                .creatingUser(someUser3())
                .createdDate(LocalDateTime.of(2020,10,20,10,20,20))
                .priority(lowPriority())
                .client(someClient3())
                .description("opis")
                .place("miejsce")
                .device("urzadzenie")
                .additionalInfo("dodatkowe informacje")
                .updateInfo("informacje o aktualizacji")
                .stage(someStage3())
                .build();
    }

    public static List<AwayWork> someAwayWorksList(){
        return List.of(someAwayWork1(), someAwayWork2(), someAwayWork3());
    }

    public static Order someOrder1(){
        return Order.builder()
                .creatingUser(someUser1())
                .createdDate(LocalDateTime.of(2020,10,20,10,20,20))
                .priority(lowPriority())
                .client(someClient1())
                .description("opis")
                .device("urzadzenie")
                .additionalInfo("dodatkowe informacje")
                .updateInfo("informacje o aktualizacji")
                .stage(someStage1())
                .build();
    }

    public static Order someOrder2(){
        return Order.builder()
                .creatingUser(someUser2())
                .createdDate(LocalDateTime.of(2020,10,20,10,20,20))
                .priority(mediumPriority())
                .client(someClient2())
                .description("opis")
                .device("urzadzenie")
                .additionalInfo("dodatkowe informacje")
                .updateInfo("informacje o aktualizacji")
                .stage(someStage2())
                .build();
    }

    public static Order someOrder3(){
        return Order.builder()
                .creatingUser(someUser3())
                .createdDate(LocalDateTime.of(2020,10,20,10,20,20))
                .priority(highPriority())
                .client(someClient3())
                .description("opis")
                .device("urzadzenie")
                .additionalInfo("dodatkowe informacje")
                .updateInfo("informacje o aktualizacji")
                .stage(someStage3())
                .build();
    }

    public static List<Order> someOrdersList(){
        return List.of(someOrder1(), someOrder2(), someOrder3());
    }

    public static Service someService1(){
        return Service.builder()
                .creatingUser(someUser1())
                .createdDate(LocalDateTime.of(2020,10,20,10,20,20))
                .priority(lowPriority())
                .client(someClient1())
                .description("opis")
                .device("urzadzenie")
                .additionalInfo("dodatkowe informacje")
                .updateInfo("informacje o aktualizacji")
                .stage(someStage1())
                .build();
    }

    public static Service someService2(){
        return Service.builder()
                .creatingUser(someUser2())
                .createdDate(LocalDateTime.of(2020,10,20,10,20,20))
                .priority(mediumPriority())
                .client(someClient2())
                .description("opis")
                .device("urzadzenie")
                .additionalInfo("dodatkowe informacje")
                .updateInfo("informacje o aktualizacji")
                .stage(someStage2())
                .build();
    }

    public static Service someService3(){
        return Service.builder()
                .creatingUser(someUser3())
                .createdDate(LocalDateTime.of(2020,10,20,10,20,20))
                .priority(highPriority())
                .client(someClient3())
                .description("opis")
                .device("urzadzenie")
                .additionalInfo("dodatkowe informacje")
                .updateInfo("informacje o aktualizacji")
                .stage(someStage3())
                .build();
    }

    public static List<Service> someServicesList(){
        return List.of(someService1(), someService2(), someService3());
    }

    public static User someUser1(){
        return User.builder()
                .userName("Marian")
                .password("marian123")
                .active(true)
                .roles(List.of(servicemanRole()))
                .build();
    }

    public static User someUser2(){
        return User.builder()
                .userName("Darek")
                .password("darek123")
                .active(true)
                .roles(List.of(servicemanRole()))
                .build();
    }

    public static User someUser3(){
        return User.builder()
                .userName("Marek")
                .password("marek123")
                .active(true)
                .roles(List.of(servicemanRole()))
                .build();
    }

    public static Role servicemanRole(){
        return Role.builder()
                .role("serviceman")
                .build();
    }

    public static Role adminRole(){
        return Role.builder()
                .role("admin")
                .build();
    }

    public static Priority lowPriority(){
        return Priority.builder()
                .name("low")
                .build();
    }

    public static Priority mediumPriority(){
        return Priority.builder()
                .name("medium")
                .build();
    }

    public static Priority highPriority(){
        return Priority.builder()
                .name("high")
                .build();
    }

    public static List<Priority> allPriorities(){
        return List.of(lowPriority(), mediumPriority(), highPriority());
    }

    public static Stage someStage1(){
        return Stage.builder()
                .name("just_added")
                .build();
    }

    public static Stage someStage2(){
        return Stage.builder()
                .name("in_progress")
                .build();
    }

    public static Stage someStage3(){
        return Stage.builder()
                .name("waiting_for_parts")
                .build();
    }

    public static Stage someStage4(){
        return Stage.builder()
                .name("to_invoice")
                .build();
    }

    public static List<Stage> allStages(){
        return List.of(Stage.builder().name("just_added").build(),
                Stage.builder().name("in_progress").build(),
                Stage.builder().name("waiting_for_parts").build(),
                Stage.builder().name("to_invoice").build());
    }

    public static Client someClient1(){
        return Client.builder()
                .name("Magda")
                .address("Poznanska 15 Poznan")
                .nip("543 345 34 32")
                .phoneNumber("123 456 789")
                .build();
    }

    public static Client someClient2(){
        return Client.builder()
                .name("Natalia")
                .address("Warszawska 15 Warszawa")
                .nip("654 345 23 12")
                .phoneNumber("456 798 123")
                .build();
    }

    public static Client someClient3(){
        return Client.builder()
                .name("Magda")
                .address("Gdanska 15 Gdansk")
                .nip("654 321 23 45")
                .phoneNumber("789 123 456")
                .build();
    }

    public static List<Client> someClientsList(){
        return List.of(someClient1(), someClient2(), someClient3());
    }

    public static Task someTask1(){
        return Task.builder()
                .createdByUserName(someUser1().getUserName())
                .createdDate(LocalDateTime.of(2020,10,20,10,20,20))
                .priorityName(lowPriority().getName())
                .clientName(someClient1().getName())
                .description("opis")
                .place("miejsce")
                .device("urzadzenie")
                .additionalInfo("dodatkowe informacje")
                .updateInfo("informacje o aktualizacji")
                .stageName(someStage1().getName())
                .build();
    }

    public static ClosedAwayWork someClosedAwayWork1(){
        return  ClosedAwayWork.builder()
                .creatingUser(someUser1())
                .createdDate(LocalDateTime.of(2020,10,20,10,20,20))
                .client(someClient1())
                .description("opis")
                .place("miejsce")
                .device("urzadzenie")
                .additionalInfo("dodatkowe informacje")
                .updateInfo("informacje o aktualizacji")
                .closingUser(someUser1())
                .closedDate(LocalDateTime.of(2020,10,20,10,20,20))
                .build();
    }

    public static List<ClosedAwayWork> someClosedAwayWorksList(){
        return List.of(someClosedAwayWork1(), someClosedAwayWork1(), someClosedAwayWork1());
    }

    public static ClosedOrder someClosedOrder1(){
        return  ClosedOrder.builder()
                .creatingUser(someUser1())
                .createdDate(LocalDateTime.of(2020,10,20,10,20,20))
                .client(someClient1())
                .description("opis")
                .device("urzadzenie")
                .additionalInfo("dodatkowe informacje")
                .updateInfo("informacje o aktualizacji")
                .closingUser(someUser1())
                .closedDate(LocalDateTime.of(2020,10,20,10,20,20))
                .build();
    }

    public static List<ClosedOrder> someClosedOrdersList(){
        return List.of(someClosedOrder1(), someClosedOrder1(), someClosedOrder1());
    }

    public static ClosedService someClosedService1(){
        return  ClosedService.builder()
                .creatingUser(someUser1())
                .createdDate(LocalDateTime.of(2020,10,20,10,20,20))
                .client(someClient1())
                .description("opis")
                .device("urzadzenie")
                .additionalInfo("dodatkowe informacje")
                .updateInfo("informacje o aktualizacji")
                .closingUser(someUser1())
                .closedDate(LocalDateTime.of(2020,10,20,10,20,20))
                .build();
    }

    public static List<ClosedService> someClosedServicesList(){
        return List.of(someClosedService1(), someClosedService1(), someClosedService1());
    }

}
