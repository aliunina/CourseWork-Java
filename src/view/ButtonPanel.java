package view;

import util.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <strong>Класс для создания панели с кнопками "Да" и "Нет"</strong>
 * @author Aliunina P.A.
 * @version 1.0
 */

public class ButtonPanel extends JPanel {

    /**
     * Кнопка "Да"
     */
    private JToggleButton btnYes;

    /**
     * Кнопка "Нет"
     */
    private JToggleButton btnNo;

    /**
     * Конструктор
     * Создает панель с кнопками "Да" и "Нет"
     */
    public ButtonPanel() {
        super();
        this.btnYes = new JToggleButton("Да");
        this.btnNo = new JToggleButton("Нет");
        this.btnYes.setFont(Util.getDefaultFont());
        this.btnNo.setFont(Util.getDefaultFont());
        this.btnYes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnNo.setSelected(false);
            }
        });
        btnNo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnYes.setSelected(false);
            }
        });
        this.btnYes.setPreferredSize(new Dimension(245, 42));
        this.btnNo.setPreferredSize(new Dimension(245, 42));
        this.btnNo.setSelected(true);
        this.add(btnYes);
        this.add(btnNo);
    }

    /**
     * Возвращает кнопку "Да"
     * @return btnYes
     */
    public JToggleButton getBtnYes() {
        return this.btnYes;
    }

    /**
     * Возвращает кнопку "Нет"
     * @return btnNo
     */
    public JToggleButton getBtnNo() {
        return this.btnNo;
    }
}
