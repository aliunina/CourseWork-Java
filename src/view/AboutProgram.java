package view;

import util.Util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * <strong>Класс для создания окна "О программе"</strong>
 * @author Aliunina P.A.
 * @version 1.0
 */

public class AboutProgram extends JFrame {

    /**
     * Панель контента
     */
    JPanel pnlContent;

    /**
     * Панель футера
     */
    JPanel pnlFooter;

    /**
     * Конструктор
     * Создаёт окно "О программе" и добавляет в него элементы"
     */
    public AboutProgram() {
        super("О программе");

        setLayout(new BorderLayout());
        pnlContent = new JPanel();
        pnlFooter = new JPanel();
        pnlContent.setLayout(new BoxLayout(pnlContent, BoxLayout.Y_AXIS));
        JLabel lblTheme = new JLabel("<html><div style='text-align: center;'>Программа для расчета подоходного налога с физических лиц в " +
                "соответствии с законодательством Республики Беларусь.</div></html>");
        lblTheme.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        lblTheme.setFont(Util.getBoldFont());
        lblTheme.setHorizontalAlignment(SwingConstants.CENTER);
        pnlContent.add(lblTheme);

        BufferedImage imgTaxes;
        try {
            imgTaxes = ImageIO.read(new File("images/taxes.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JLabel lblImageAbout = new JLabel(new ImageIcon(imgTaxes));
        lblImageAbout.setPreferredSize(new Dimension(100, 250));

        pnlContent.add(lblImageAbout);

        JLabel lblFeatures = new JLabel("<html>Программа позволяет:<br/>" +
        "1. Вводить данные о доходах и характеристиках физического лица<br/>" +
        "2. Рассчитывать налоговые вычеты и сумму налога к уплате<br/>" +
        "3. Корректировать основную ставку подоходного налога</html>");
        lblFeatures.setFont(Util.getDefaultFont());
        lblFeatures.setHorizontalAlignment(SwingConstants.CENTER);
        lblFeatures.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
        pnlContent.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblFooter = new JLabel("<html><div style='text-align: center;'>Authored by Aliunina Polina, " +
                "Minsk, 2023.</div></html>");
        lblFooter.setFont(Util.getDefaultFont());
        pnlFooter.add(lblFooter);
        pnlContent.add(lblFeatures);

        add(pnlContent, BorderLayout.NORTH);
        add(pnlFooter, BorderLayout.SOUTH);
        setSize(600, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

    }
}
