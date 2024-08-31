package model;

/**
 * Интерфейс для налоговых вычетов
 * @author Aliunina P.A.
 * @version 1.0
 */

public interface TaxDeduction {

    /**
     * Метод для расчета налогового вычета
     * @param individual - физическое лицо
     * @throws Exception
     */
    public double calculate(Individual individual) throws Exception;
}
