package hannibal.greeks.djinFinanceApi.controller;


import hannibal.greeks.djinFinanceApi.model.PortfolioResult;
import hannibal.greeks.djinFinanceApi.model.PositionRequest;
import hannibal.greeks.djinFinanceApi.model.PositionResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pnl")
public class PnLController {

    @PostMapping
    public PortfolioResult computePnL(@RequestBody List<PositionRequest> positions) {
        List<PositionResult> results = positions.stream().map(pos -> {
            double initial = pos.prices.get(0);
            double last = pos.prices.get(pos.prices.size() - 1);
            double pnl = (last - initial) * pos.quantity;

            PositionResult r = new PositionResult();
            r.symbol = pos.symbol;
            r.finalPnl = pnl;
            return r;
        }).collect(Collectors.toList());

        double total = results.stream().mapToDouble(r -> r.finalPnl).sum();

        PortfolioResult portfolio = new PortfolioResult();
        portfolio.positions = results;
        portfolio.totalPnl = total;
        return portfolio;
    }
}
