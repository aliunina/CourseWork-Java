package view;

import javax.swing.*;
import java.awt.*;
import controller.*;
import util.Util;

/**
 * <strong>Основной класс, отрисовывающий компоненты интерфейса</strong>
 * @author Aliunina P.A.
 * @version 1.0
 */

public class Renderer extends JFrame {

    /**
     * Панель хедера
     */
    public JPanel pnlHeader;

    /**
     * Панель тела приложения
     */
    public JPanel pnlBody;

    /**
     * Панель футера
     */
    public JPanel pnlFooter;

    /**
     * Список с выбором периодов
     */
    public JSpinner spnPeriods;

    /**
     * Панель с кнопками "Да" и "Нет" для обозначения есть ли льготы
     */
    public ButtonPanel pnlHasBenefits;

    /**
     * Панель с кнопками "Да" и "Нет" для обозначения является ли вдовой(вдовцом), опекуном и т. д.
     */
    public ButtonPanel pnlSocVulnerable;

    /**
     * Поле для ввода основного дохода
     */
    public JTextField txtIncome;

    /**
     * Поле для ввода дохода без ндс
     */
    public JTextField txtOtherIncome;

    /**
     * Поле для ввода количества детей
     */
    public JTextField txtChildCount;

    /**
     * Поле для ввода количества детей-инвалидов
     */
    public JTextField txtDisChildCount;

    /**
     * Поле для ввода количества иждивенцев
     */
    public JTextField txtDepCount;

    /**
     * Поле для ввода расходов на страхование
     */
    public JTextField txtInsExpenses;

    /**
     * Поле для ввода расходов на образование
     */
    public JTextField txtEduExpenses;

    /**
     * Поля для ввода расходов на строительство
     */
    public JTextField txtConsExpenses;
    /**
     * Поля для ввода расходов на предпринимательство
     */
    public JTextField txtBsnsExpenses;

    /**
     * Поле для вывода результата
     */
    public JTextField txtResult;

    /**
     * Кнопка "Рассчитать"
     */
    public JButton btnCalculate;

    /**
     * Кнопка "Очистить"
     */
    public JButton btnClear;

    /**
     * Кнопка "О программе"
     */
    public JButton btnAbout;

    /**
     * Кнопка "Изменить основную ставку"
     */
    public JButton btnChangeBasicTax;

    /**
     * Создает основные контейнеры окна
     */
    private void addContainers() {
        pnlHeader = new JPanel();
        pnlBody = new JPanel();
        pnlFooter = new JPanel();
        add(pnlHeader, BorderLayout.NORTH);
        add(pnlBody, BorderLayout.CENTER);
        add(pnlFooter, BorderLayout.SOUTH);
        pnlHeader.setLayout(new BoxLayout(pnlHeader, BoxLayout.Y_AXIS));
        pnlBody.setLayout(new BoxLayout(pnlBody, BoxLayout.Y_AXIS));
        pnlFooter.setLayout(new BorderLayout());
    }

    /**
     * Добавляет и заполняет header-панель
     */
    private void addHeaderContent() {
        JToolBar tbToolbar = new JToolBar();
        tbToolbar.setFloatable(false);
        tbToolbar.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        tbToolbar.setAlignmentY(JComponent.TOP_ALIGNMENT);
        btnAbout = new JButton("О программе");
        btnAbout.addActionListener(new Main.AboutProgramListener());
        btnChangeBasicTax = new JButton("Изменить основную ставку");
        btnChangeBasicTax.addActionListener(new Main.ChangeBasicTaxListener());
        tbToolbar.add(btnAbout);
        tbToolbar.add(btnChangeBasicTax);
        pnlHeader.add(tbToolbar);

        JLabel lblWelcome = new JLabel("Налоговый калькулятор");
        lblWelcome.setFont(Util.getBoldFont());
        lblWelcome.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        pnlHeader.add(lblWelcome);
        pnlHeader.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
    }

    /**
     * Добавляет и заполняет body-панель
     */
    private void addBodyContent() {
        spnPeriods = new JSpinner(new SpinnerListModel(new String[] {"год", "9 месяцев", "полугодие", "квартал", "месяц"}));
        pnlBody.add(new Field(new PropertyLabel("1. Период, за который производится расчет:"), spnPeriods));

        txtIncome = Util.createFloatFilteredField();
        txtOtherIncome = Util.createFloatFilteredField();
        txtIncome.setHorizontalAlignment(SwingConstants.RIGHT);
        txtOtherIncome.setHorizontalAlignment(SwingConstants.RIGHT);
        pnlBody.add(new Field(new PropertyLabel("2. Сумма выручки от реализации товаров (работ, услуг), " +
                "имущественных прав за выбранный период (без налога на добавленную стоимость), руб."),
                txtIncome));
        pnlBody.add(new Field(new PropertyLabel("3. Сумма внереализационных доходов за выбранный период " +
                "(без налога на добавленную стоимость),руб."),
                txtOtherIncome));

        pnlHasBenefits = new ButtonPanel();
        pnlSocVulnerable = new ButtonPanel();
        pnlBody.add(new Field(new PropertyLabel("4. Наличие права на льготы (инвалид I или II группы, " +
                "инвалид с детства, участник боевых действий на территории других государств и др.):"),
                pnlHasBenefits));
        pnlBody.add(new Field(new PropertyLabel("5. Являетесь ли Вы вдовой (вдовцом), одиноким родителем, " +
                "приемным родителем, опекуном или попечителем:"),
                pnlSocVulnerable));

        txtChildCount = Util.createIntFilteredField();
        txtDisChildCount = Util.createIntFilteredField();
        txtDepCount = Util.createIntFilteredField();
        txtChildCount.setHorizontalAlignment(SwingConstants.RIGHT);
        txtDisChildCount.setHorizontalAlignment(SwingConstants.RIGHT);
        txtDepCount.setHorizontalAlignment(SwingConstants.RIGHT);

        pnlBody.add(new Field(new PropertyLabel("6. Количество детей до 18 лет,"),
                txtChildCount));
        pnlBody.add(new Field(new PropertyLabel("из них количество детей-инвалидов"),
                txtDisChildCount));
        pnlBody.add(new Field(new PropertyLabel("7. Количество иждивенцев"),
                txtDepCount));

        txtInsExpenses = Util.createFloatFilteredField();
        txtEduExpenses = Util.createFloatFilteredField();
        txtConsExpenses = Util.createFloatFilteredField();
        txtBsnsExpenses = Util.createFloatFilteredField();
        txtInsExpenses.setHorizontalAlignment(SwingConstants.RIGHT);
        txtEduExpenses.setHorizontalAlignment(SwingConstants.RIGHT);
        txtConsExpenses.setHorizontalAlignment(SwingConstants.RIGHT);
        txtBsnsExpenses.setHorizontalAlignment(SwingConstants.RIGHT);
        pnlBody.add(new Field(new PropertyLabel("8. Сумма расходов за выбранный период по страховым " +
                "взносам по договорам добровольного страхования жизни и дополнительной пенсии, заключенным на " +
                "срок не менее трех лет, а также по договорам добровольного страхования медицинских расходов, руб."),
                txtInsExpenses));
        pnlBody.add(new Field(new PropertyLabel("9. Сумма расходов за выбранный период на получение " +
                "первого платного образования своего либо близких родственников, руб."),
                txtEduExpenses));
        pnlBody.add(new Field(new PropertyLabel("10. Сумма расходов за выбранный период на строительство " +
                "либо приобретение жилья для нуждающихся в улучшении жилищных условий, руб."),
                txtConsExpenses));
        pnlBody.add(new Field(new PropertyLabel("11. Сумма расходов за выбранный период, связанных с " +
                "осуществлением предпринимательской деятельности, руб."),
                txtBsnsExpenses));
        pnlBody.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    /**
     * Добавляет и заполняет footer-панель
     */
    private void addFooterContent() {
        JPanel pnlResult = new JPanel();
        JLabel lblResult = new JLabel("Налог к уплате:");
        txtResult = new JTextField();
        txtResult.setEditable(false);
        txtResult.setPreferredSize(new Dimension(200, 47));
        txtResult.setFont(Util.getBoldFont());
        lblResult.setFont(Util.getBoldFont());
        txtResult.setHorizontalAlignment(SwingConstants.RIGHT);
        pnlResult.add(lblResult);
        pnlResult.add(txtResult);
        pnlFooter.add(pnlResult, BorderLayout.WEST);

        JPanel pnlActions = new JPanel();
        btnCalculate = new JButton("Рассчитать");
        btnClear = new JButton("Очистить поля");
        btnCalculate.setFont(Util.getDefaultFont());
        btnClear.setFont(Util.getDefaultFont());
        btnCalculate.addActionListener(new Main.CalculateButtonListener());
        btnClear.addActionListener(new Main.ClearButtonListener());
        pnlActions.add(btnCalculate);
        pnlActions.add(btnClear);
        pnlFooter.add(pnlActions, BorderLayout.EAST);
        pnlFooter.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    /**
     * Задает основные свойства окна
     */
    private void setFrameProperties() {
        setIconImage(new ImageIcon("images/logo.png").getImage());
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(false);
    }

    /**
     * Конструктор
     * Создает основное окно приложения
     */
    public Renderer() {
        super("Расчёт подоходного налога");
        setFrameProperties();
        addContainers();
        addHeaderContent();
        addBodyContent();
        addFooterContent();
        setVisible(true);
    }
}
