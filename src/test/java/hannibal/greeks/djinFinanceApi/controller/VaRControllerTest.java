package hannibal.greeks.djinFinanceApi.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(VaRController.class)
public class VaRControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCalculateVaR() throws Exception {
        String requestBody = """
        {
            "positionValue": 10000,
            "volatility": 0.02,
            "confidenceLevel": 0.95
        }
        """;

        mockMvc.perform(post("/api/var")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valueAtRisk").value(329.0))
                .andExpect(jsonPath("$.zScore").value(1.645));
    }
}
