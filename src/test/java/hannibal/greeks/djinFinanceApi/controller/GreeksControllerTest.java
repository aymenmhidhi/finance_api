package hannibal.greeks.djinFinanceApi.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GreekController.class)
public class GreeksControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BlackScholesService blackScholesService;

    @Test
    void testCalculateGreeks() throws Exception {
        String requestBody = """
        [
            {
                "underlyingPrice": 100,
                "strikePrice": 90,
                "timeToMaturity": 0.5,
                "volatility": 0.2,
                "riskFreeRate": 0.01,
                "optionType": "call"
            }
        ]
        """;

        mockMvc.perform(post("/api/greeks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].delta").exists());
    }
}