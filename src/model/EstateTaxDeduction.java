package model;

/**
 * <strong>Класс рассчитывает налоговый вычет на строительство</strong>
 * @author Aliunina P.A.
 * @version 1.0
 */
public class EstateTaxDeduction implements TaxDeduction {

    /**
     * Рассчитывает налоговый вычет на строительство
     * @param individual - физическое лицо
     */
    public double calculate(Individual individual) {
        double deduction = 0.0;
        deduction += individual.getConstructionExpenses();
        return deduction;
    }
}
