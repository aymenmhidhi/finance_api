package hannibal.greeks.djinFinanceApi.model;

import java.util.List;

public class PositionRequest {
    public String symbol;
    public List<Double> prices;
    public int quantity;
}
