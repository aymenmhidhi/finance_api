package hannibal.greeks.djinFinanceApi;

import hannibal.greeks.djinFinanceApi.model.GreekResult;
import hannibal.greeks.djinFinanceApi.model.OptionRequest;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.springframework.stereotype.Service;

@Service
public class BlackScholesService {
    private static final NormalDistribution N = new NormalDistribution();

    public GreekResult calculate(OptionRequest o) {
        double S = o.spot, K = o.strike, T = o.maturity, r = o.rate, sigma = o.volatility;
        double d1 = (Math.log(S / K) + (r + 0.5 * sigma * sigma) * T) / (sigma * Math.sqrt(T));
        double d2 = d1 - sigma * Math.sqrt(T);
        GreekResult result = new GreekResult();
        //return the greeks result in greekresult
        result.price = S * N.cumulativeProbability(d1) - K * Math.exp(-r * T) * N.cumulativeProbability(d2);
        result.delta = N.cumulativeProbability(d1);
        result.gamma = N.density(d1) / (S * sigma * Math.sqrt(T));
        result.vega = S * N.density(d1) * Math.sqrt(T);
        result.theta = -S * N.density(d1) * sigma / (2 * Math.sqrt(T)) - r * K * Math.exp(-r * T) * N.cumulativeProbability(d2);
        result.rho = K * T * Math.exp(-r * T) * N.cumulativeProbability(d2);
        return result;
    }
}