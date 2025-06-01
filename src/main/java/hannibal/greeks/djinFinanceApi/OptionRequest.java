package hannibal.greeks.djinFinanceApi;

public class OptionRequest {
    public double spot;
    public double strike;
    public double maturity; // en années
    public double rate;
    public double volatility;
}