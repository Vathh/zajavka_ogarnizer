package pl.ogarnizer.api.rest.controller.unit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.ogarnizer.api.dto.ServiceDTO;
import pl.ogarnizer.api.dto.mapper.ServiceMapper;
import pl.ogarnizer.api.rest.controller.ServiceRestController;
import pl.ogarnizer.api.rest.dto.ServicesDTO;
import pl.ogarnizer.business.ServiceService;
import pl.ogarnizer.domain.Service;
import pl.ogarnizer.util.DomainFixtures;
import pl.ogarnizer.util.DtoFixtures;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServiceRestControllerUnitTests {
    @Mock
    private ServiceService serviceService;
    @Mock
    private ServiceMapper serviceMapper;

    @InjectMocks
    private ServiceRestController serviceRestController;

    @Test
    void thatGetServicesWorksCorrectly(){
        //given
        List<Service> services = List.of(DomainFixtures.someService1(), DomainFixtures.someService1(), DomainFixtures.someService1());
        when(serviceService.findServices()).thenReturn(services);
        when(serviceMapper.map(any())).thenReturn(DtoFixtures.someServiceDTO1());

        //when
        ServicesDTO result = serviceRestController.getServices();

        //then
        assertThat(result).isEqualTo(DtoFixtures.someServicesDTO());
    }

    @Test
    void thatServiceDetailsWorksCorrectly(){
        //given
        Integer serviceId = 10;
        Service service = DomainFixtures.someService1();
        when(serviceService.findServiceById(serviceId)).thenReturn(service);
        when(serviceMapper.map(service)).thenReturn(DtoFixtures.someServiceDTO1());

        //when
        ServiceDTO result = serviceRestController.serviceDetails(serviceId);

        //then
        assertThat(result).isEqualTo(DtoFixtures.someServiceDTO1());

    }
}
