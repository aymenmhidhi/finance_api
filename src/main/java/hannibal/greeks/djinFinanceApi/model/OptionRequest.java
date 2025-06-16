package hannibal.greeks.djinFinanceApi.model;

public class OptionRequest {
    public double spot;
    public double strike;
    public double maturity; // en années
    public double rate;
    public double volatility;
}