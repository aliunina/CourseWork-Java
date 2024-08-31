package model;

/**
 * <strong>Класс рассчитывает стандартный налоговый вычет</strong>
 * @author Aliunina P.A.
 * @version 1.0
 */

public class StandardTaxDeduction implements TaxDeduction {

    /**
     * Минимальный доход
     */
    private static final double LOW_INCOME = 944;

    /**
     * Вычет при условии дохода меньше минимального
     */
    private static final double LOW_INCOME_DEDUCTION = 156;

    /**
     * Вычет при наличии льгот
     */
    private static final double BENEFITS_DEDUCTION = 220;

    /**
     * Вычет на ребёнка
     */
    private static final double CHILDREN_DEDUCTION = 46;

    /**
     * Вычет на ребёнка/иждивенца
     */
    private static final double CHILD_DEP_DEDUCTION = 87;

    /**
     * Рассчитывает стандартный налоговый вычет
     * @param individual - физическое лицо
     * @throws ArithmeticException
     * @return deduction - стандартный налоговый вычет
     */
    public double calculate(Individual individual) throws Exception{
        double deduction = 0.0, allIncome = individual.getIncome() + individual.getOtherIncome();
        if (allIncome < individual.getBusinessExpenses()) {
            throw new ArithmeticException("Ошибка ввода данных: Расход не может превышать доходы.");
        }
        if (individual.getBusinessExpenses() > 0) {
            deduction += (allIncome - individual.getBusinessExpenses()) * 0.16;
        }
        if (allIncome <= LOW_INCOME) {
            deduction += LOW_INCOME_DEDUCTION;
        }
        if (individual.getHasBenefits()) {
            deduction += BENEFITS_DEDUCTION;
        }
        if (individual.getChildrenCount() == 1) {
            deduction += CHILDREN_DEDUCTION;
        }
        if (individual.getDependentsCount() > 0)
            deduction += individual.getDependentsCount() * CHILDREN_DEDUCTION;
        if (individual.getChildrenCount() >= 2 || individual.getDisabledChildrenCount() > 0) {
            deduction += CHILD_DEP_DEDUCTION;
        }
        if (individual.getIsSociallyVulnerable()) {
            deduction += individual.getChildrenCount() * CHILD_DEP_DEDUCTION
                    + individual.getDependentsCount() * CHILD_DEP_DEDUCTION;
        }
        return deduction;
    }
}
