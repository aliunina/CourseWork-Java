package view;

import util.Util;

import javax.swing.*;
import java.awt.*;

/* <strong>Класс для создания лейбла</strong>
 * @author Aliunina P.A.
 * @version 1.0
 */

public class PropertyLabel extends JLabel {

    /**
     * Конструктор
     * Создает лейбл определенного размера с переносом текста
     * Задает шрифт лейбла
     * @param text - текст лейбла
     */
    public PropertyLabel(String text) {
        super("<html>" + text + "</html>");
        setPreferredSize(new Dimension(1000, 47));
        setFont(Util.getDefaultFont());
    }
}
