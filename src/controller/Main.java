package controller;

import model.*;
import util.Util;
import view.*;
import view.Renderer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <strong>Класс реализует основную логику программы</strong>
 * @author Aliunina P.A.
 * @version 1.0
 */

public class Main {

    /**
     * Указатель на главный фрейм
     */
    public static Renderer renderer;

    /**
     * Задаёт начальные значения всем полям ввода
     */
    private static void setDefaultValues() {
        renderer.spnPeriods.setValue("месяц");
        renderer.txtIncome.setText("");
        renderer.txtOtherIncome.setText("");
        renderer.pnlHasBenefits.getBtnYes().setSelected(false);
        renderer.pnlHasBenefits.getBtnNo().setSelected(true);
        renderer.pnlSocVulnerable.getBtnYes().setSelected(false);
        renderer.pnlSocVulnerable.getBtnNo().setSelected(true);
        renderer.txtChildCount.setText("");
        renderer.txtDisChildCount.setText("");
        renderer.txtDepCount.setText("");
        renderer.txtInsExpenses.setText("");
        renderer.txtEduExpenses.setText("");
        renderer.txtConsExpenses.setText("");
        renderer.txtBsnsExpenses.setText("");
        renderer.txtResult.setText("0.0");
    }

    /**
     * Класс-слушатель кнопки "Очистить"
     * Устанавливает всем полям ввода начальные значения
     * @see view.Renderer#btnClear
     */
    public static class ClearButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            setDefaultValues();
        }
    }

    /**
     * Класс-слушатель кнопки "Рассчитать"
     * Рассчитывает налог к уплате и выводит окно с налоговыми вычетами
     * @see view.Renderer#btnCalculate
     */
    public static class CalculateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int period = Util.getIntPeriod(renderer.spnPeriods.getValue().toString());
                Individual individual = new Individual();
                double resultTax = individual.calculateTax(period);
                renderer.txtResult.setText(String.format("%4.2f", resultTax));
                JOptionPane.showMessageDialog(null,
                        String.format("Стандартные: %4.2f\nСоциальные: %4.2f\nНа строительство: %4.2f",
                                new StandardTaxDeduction().calculate(individual),
                                new SocialTaxDeduction().calculate(individual),
                                new EstateTaxDeduction().calculate(individual)),
                        "Налоговые вычеты",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception exception) {
                renderer.txtResult.setText("0.00");
                JOptionPane.showMessageDialog(null, exception.getMessage(), "",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Класс-слушатель кнопки "О программе"
     * Выводит окно с информацией о программе
     * @see view.Renderer#btnAbout
     * @see view.AboutProgram
     */
    public static class AboutProgramListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new AboutProgram();
        }
    }

    /**
     * Класс-слушатель кнопки "Изменить основную ставку"
     * Выводит окно с полем ввода для изменения основной ставки подоходного налога.
     * @see Renderer#btnChangeBasicTax
     * @see Individual#getBasicIncomeTaxRate()
     */
    public static class ChangeBasicTaxListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String result = JOptionPane.showInputDialog(
                    renderer,
                    "Изменить основную ставку (в процентах)",
                    Individual.getBasicIncomeTaxRate() * 100
                    );
            try {
                if (result != null && result.matches("^[0-9]{0,2}[.]?[0-9]{0,2}$")
                    && Integer.parseInt(result) < 100) {
                    Individual.setBasicIncomeTaxRate(Double.parseDouble(result) / 100);
                } else if (result != null) {
                    throw new Exception();
                }
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, "Проверьте правильность ввода.");
            }
        }
    }

    public static void main(String[] args) {
        renderer = new Renderer();
        setDefaultValues();
    }
}
