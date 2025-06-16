package hannibal.greeks.djinFinanceApi.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PnLController.class)
public class PnLControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testComputePnL() throws Exception {
        String requestBody = """
        [
            {
                "symbol": "AAPL",
                "prices": [170.0, 175.0],
                "quantity": 10
            }
        ]
        """;

        mockMvc.perform(post("/api/pnl")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.positions[0].symbol").value("AAPL"))
                .andExpect(jsonPath("$.positions[0].finalPnl").value(50.0));
    }
}
