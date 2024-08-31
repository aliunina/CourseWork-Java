package model;

/**
 * <strong>Класс рассчитывает социальный налоговый вычет</strong>
 * @author Aliunina P.A.
 * @version 1.0
 */

public class SocialTaxDeduction implements TaxDeduction {

    /**
     * Рассчитывает социальный налоговый вычет
     * @param individual - физическое лицо
     */
    public double calculate(Individual individual) {
        double deduction = 0.0;
        deduction += individual.getInsuranceExpenses() + individual.getEducationExpenses();
        return deduction;
    }
}
