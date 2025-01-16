package dev.tolana.exambackend.drone;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DroneControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private DroneService droneService;

    @Test
    void getDrones() throws Exception {
        mockMvc.perform(get("/api/v1/drones"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(3));
    }

    @Test
    void enableDrone() throws Exception {
        mockMvc.perform(get("/api/v1/drones/enable?id=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status")
                        .value(OperationStatus.IN_SERVICE.toString()));

    }

    @Test
    void disableDrone() throws Exception {
        mockMvc.perform(get("/api/v1/drones/disable?id=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status")
                        .value(OperationStatus.OUT_OF_SERVICE.toString()));

    }

    @Test
    void retireDrone() throws Exception {
        mockMvc.perform(get("/api/v1/drones/retire?id=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status")
                        .value(OperationStatus.DECOMMISSIONED.toString()));

    }

    @Test
    void addDrone() throws Exception {
        mockMvc.perform(get("/api/v1/drones/add"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/api/v1/drones"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(4));

    }
}