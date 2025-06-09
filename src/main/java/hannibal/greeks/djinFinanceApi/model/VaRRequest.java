package hannibal.greeks.djinFinanceApi.model;

public class VaRRequest {
    public double positionValue; // Valeur de la position (€)
    public double volatility;    // Volatilité (ex: 0.02 pour 2%)
    public double confidenceLevel; // Niveau de confiance (ex: 0.95 ou 0.99)
}
