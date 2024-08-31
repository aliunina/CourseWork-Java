package model;

import controller.*;
import javax.swing.*;

/**
 * <strong>Класс "Физическое лицо"</strong>
 * Содержит поле основной ставки подоходного налога и методы для ее изменения
 * Может рассчитывать подоходный налог с учетом налоговых вычетов
 * @author Aliunina P.A.
 * @version 1.0
 */

public class Individual {
    /**
     * Основная ставка подоходного налога
     */
    private static double basicTaxRate = 0.13;

    /**
     * Основной доход
     */
    private double income = 0.0;

    /**
     * Другие доходы (без НДС)
     */
    private double otherIncome = 0.0;

    /**
     * Имеет ли льготы (инвалид, участник ВОВ и т. д.)
     */
    private boolean hasBenefits = false;

    /**
     * Является ли вдовой (вдовцом), опекуном и т. д.
     */
    private boolean isSociallyVulnerable = false;

    /**
     * Количество детей до 18 лет
     */
    private byte childrenCount = 0;

    /**
     * Количество детей-инвалидов
     */
    private byte disabledChildrenCount = 0;

    /**
     * Количество иждивенцев
     */
    private byte dependentsCount = 0;

    /**
     * Расходы на страхование
     */
    private double insuranceExpenses = 0.0;

    /**
     * Расходы на образование
     */
    private double educationExpenses = 0.0;

    /**
     * Расходы на строительство
     */
    private double constructionExpenses = 0.0;

    /**
     * Расходы на предпринимательскую деятельность
     */
    private double businessExpenses = 0.0;

    /**
     * Рассчитывает налог к уплате с учетом налоговых вычетов
     * @param period - Период, за который производится расчет
     * @throws ArithmeticException
     * @return tax - налог к уплате
     */
    public double calculateTax(int period) throws Exception{
        double tax = getIncome() + getOtherIncome();
        if (tax <= 0) {
            throw new ArithmeticException("Ошибка ввода данных: Доход должен быть больше нуля.");
        }
        tax -= new StandardTaxDeduction().calculate(this);
        tax -= new SocialTaxDeduction().calculate(this);
        tax -= new EstateTaxDeduction().calculate(this);
        tax *= basicTaxRate;
        return tax * period;
    }

    /**
     * Конструктор
     * Создаёт объект класса, заполняя поля значениями из полей ввода
     * @throws NumberFormatException
     */
    public Individual() {
        try {
            setIncome(Double.parseDouble(Main.renderer.txtIncome.getText().isEmpty()? "0" :
                    Main.renderer.txtIncome.getText()));
            setOtherIncome(Double.parseDouble(Main.renderer.txtOtherIncome.getText().isEmpty()? "0" :
                    Main.renderer.txtOtherIncome.getText()));
            setHasBenefits(Main.renderer.pnlHasBenefits.getBtnYes().isSelected());
            setIsSociallyVulnerable(Main.renderer.pnlSocVulnerable.getBtnYes().isSelected());
            setChildrenCount(Byte.parseByte(Main.renderer.txtChildCount.getText().isEmpty()? "0" :
                    Main.renderer.txtChildCount.getText()));
            setDisabledChildrenCount(Byte.parseByte(Main.renderer.txtDisChildCount.getText().isEmpty()? "0" :
                    Main.renderer.txtDisChildCount.getText()));
            setDependentsCount(Byte.parseByte(Main.renderer.txtDepCount.getText().isEmpty()? "0" :
                    Main.renderer.txtDepCount.getText()));
            setInsuranceExpenses(Double.parseDouble(Main.renderer.txtInsExpenses.getText().isEmpty()? "0" :
                    Main.renderer.txtInsExpenses.getText()));
            setEducationExpenses(Double.parseDouble(Main.renderer.txtEduExpenses.getText().isEmpty()? "0" :
                    Main.renderer.txtEduExpenses.getText()));
            setConstructionExpenses(Double.parseDouble(Main.renderer.txtConsExpenses.getText().isEmpty()? "0" :
                    Main.renderer.txtConsExpenses.getText()));
            setBusinessExpenses(Double.parseDouble(Main.renderer.txtBsnsExpenses.getText().isEmpty() ? "0" :
                    Main.renderer.txtBsnsExpenses.getText()));
        } catch (NumberFormatException exception){
            JOptionPane.showMessageDialog(null, "Проверьте правильность ввода.");
        }
    }

    /**
     * Возвращает значение basicTaxRate
     */
    public static double getBasicIncomeTaxRate() {
        return basicTaxRate;
    }

    /**
     * Возвращает значение income
     * @return income
     */
    public double getIncome() {
        return this.income;
    }

    /**
     * Возвращает значение otherIncome
     * @return otherIncome
     */
    public double getOtherIncome() {
        return this.otherIncome;
    }

    /**
     * Возвращает значение hasBenefits
     * @return hasBenefits
     */
    public boolean getHasBenefits() {
        return this.hasBenefits;
    }

    /**
     * Возвращает значение isSociallyVulnerable
     * @return isSociallyVulnerable
     */
    public boolean getIsSociallyVulnerable() {
        return this.isSociallyVulnerable;
    }

    /**
     * Возвращает значение childrenCount
     * @return childrenCount
     */
    public byte getChildrenCount() {
        return this.childrenCount;
    }

    /**
     * Возвращает значение disabledChildrenCount
     * @return disabledChildrenCount
     */
    public byte getDisabledChildrenCount() {
        return this.disabledChildrenCount;
    }

    /**
     * Возвращает значение dependentsCount
     * @return dependentsCount
     */
    public byte getDependentsCount() {
        return this.dependentsCount;
    }

    /**
     * Возвращает значение insuranceExpenses
     * @return insuranceExpenses
     */
    public double getInsuranceExpenses() {
        return this.insuranceExpenses;
    }

    /**
     * Возвращает значение educationExpenses
     * @return educationExpenses
     */
    public double getEducationExpenses() {
        return this.educationExpenses;
    }

    /**
     * Возвращает значение constructionExpenses
     * @return constructionExpenses
     */
    public double getConstructionExpenses() {
        return this.constructionExpenses;
    }

    /**
     * Возвращает значение businessExpenses
     * @return businessExpenses
     */
    public double getBusinessExpenses() {
        return this.businessExpenses;
    }

    /**
     * Устанавливает значение basicTaxRate
     * @param tax - новое значение основной ставки подоходного налога
     */
    public static void setBasicIncomeTaxRate(double tax) {
        basicTaxRate = tax;
    }

    /**
     * Устанавливает значение income
     * @param income - новое значение основного дохода
     */
    public void setIncome(double income) {
        this.income = income;
    }

    /**
     * Устанавливает значение otherIncome
     * @param otherIncome - новое значение других доходов
     */
    public void setOtherIncome(double otherIncome) {
        this.otherIncome = otherIncome;
    }

    /**
     * Устанавливает значение hasBenefits
     * @param hasBenefits - смена значения "Имеет ли льготы"
     */
    public void setHasBenefits(boolean hasBenefits) {
        this.hasBenefits = hasBenefits;
    }

    /**
     * Устанавливает значение isSociallyVulnerable
     * @param isSociallyVulnerable - смена значения "Является ли вдовой(вдовцом) и т. д."
     */
    public void setIsSociallyVulnerable(boolean isSociallyVulnerable) {
        this.isSociallyVulnerable = isSociallyVulnerable;
    }

    /**
     * Устанавливает значение childrenCount
     * @param childrenCount - новое значение количества детей
     */
    public void setChildrenCount(byte childrenCount) {
        this.childrenCount = childrenCount;
    }

    /**
     * Устанавливает значение disabledChildrenCount
     * @param disabledChildrenCount - новое значение количества летей инвалидов
     */
    public void setDisabledChildrenCount(byte disabledChildrenCount) {
        this.disabledChildrenCount = disabledChildrenCount;
    }

    /**
     * Устанавливает значение dependentsCount
     * @param dependentsCount - количество иждивенцев
     */
    public void setDependentsCount(byte dependentsCount) {
        this.dependentsCount = dependentsCount;
    }

    /**
     * Устанавливает значение insuranceExpenses
     * @param insuranceExpenses - новое значение расходов на страхование
     */
    public void setInsuranceExpenses(double insuranceExpenses) {
        this.insuranceExpenses = insuranceExpenses;
    }

    /**
     * Устанавливает значение educationExpenses
     * @param educationExpenses - новое значение расходов на образование
     */
    public void setEducationExpenses(double educationExpenses) {
        this.educationExpenses = educationExpenses;
    }

    /**
     * Устанавливает значение constructionExpenses
     * @param constructionExpenses - новое значение расходов на строительство
     */
    public void setConstructionExpenses(double constructionExpenses) {
        this.constructionExpenses = constructionExpenses;
    }

    /**
     * Устанавливает значение businessExpenses
     * @param businessExpenses - новое значение расходов на предпринимательство
     */
    public void setBusinessExpenses(double businessExpenses) {
        this.businessExpenses = businessExpenses;
    }
}