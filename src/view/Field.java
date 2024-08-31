package view;

import util.Util;

import javax.swing.*;
import java.awt.*;

/**
 * <strong>Класс для создания панели "лейбл + поле для ввода"</strong>
 * @author Aliunina P.A.
 * @version 1.0
 */

public class Field extends JPanel {

    /**
     * Конструктор
     * Создает панель "лейбл + поле для ввода"
     * Устанавливает шрифт и размеры
     * @param lblLabel - лейбл
     * @param cmpComponent - поле для ввода
     */
    public Field(JLabel lblLabel, JComponent cmpComponent) {
        super();
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.add(lblLabel);

        cmpComponent.setFont(Util.getDefaultFont());
        cmpComponent.setPreferredSize(new Dimension(500, 47));
        this.add(cmpComponent);
    }
}
