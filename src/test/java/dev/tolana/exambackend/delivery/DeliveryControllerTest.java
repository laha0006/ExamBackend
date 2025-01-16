package dev.tolana.exambackend.delivery;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.tolana.exambackend.delivery.dto.DeliveryDto;
import dev.tolana.exambackend.delivery.dto.DeliveryRequest;
import dev.tolana.exambackend.delivery.dto.ScheduleRequest;
import dev.tolana.exambackend.drone.Drone;
import dev.tolana.exambackend.drone.OperationStatus;
import dev.tolana.exambackend.drone.dto.DroneDtoWithoutStation;
import dev.tolana.exambackend.pizza.Pizza;
import dev.tolana.exambackend.station.Station;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class DeliveryControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Mock
    private DeliveryService deliveryService;


    @Test
    void testGetDeliveries() throws Exception {
        mockMvc.perform(get("/api/v1/deliveries"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));

    }

    @Test
    void testAddDelivery() throws Exception {
        DeliveryRequest deliveryRequest = new DeliveryRequest(
                Pizza.builder().id(1).build(),
                "My Home Address"
        );

        // Use ObjectMapper to convert the object to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(deliveryRequest);

        mockMvc.perform(post("/api/v1/deliveries/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated());
    }

    @Test
    void testGetDeliveriesQueue() {
    }

    @Test
    void testScheduleDelivery() {
    }

    @Test
    void testFinishDelivery() {
    }
}

//Generated with love by TestMe :) Please raise issues & feature requests at: https://weirddev.com/forum#!/testme