package hannibal.greeks.djinFinanceApi.controller;

import hannibal.greeks.djinFinanceApi.model.VaRRequest;
import hannibal.greeks.djinFinanceApi.model.VaRResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/var")
public class VaRController {

    @PostMapping
    public VaRResult calculateVaR(@RequestBody VaRRequest req) {
        double z = getZScore(req.confidenceLevel);
        double var = req.positionValue * req.volatility * z;

        VaRResult result = new VaRResult();
        result.valueAtRisk = var;
        result.zScore = z;
        return result;
    }


    private double getZScore(double confidenceLevel) {
        if (confidenceLevel == 0.90) return 1.28;
        if (confidenceLevel == 0.95) return 1.645;
        if (confidenceLevel == 0.99) return 2.33;
        throw new IllegalArgumentException("Unsupported confidence level: " + confidenceLevel);
    }
}
