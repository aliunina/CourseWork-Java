package util;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;

/**
 * <strong>Вспомогательный класс</strong>
 * @author Aliunina P.A.
 * @version 1.0
 */

public class Util {

    /**
     * Возвращает числовое значение периода
     * @param period - строковое значение периода
     * @return result - числовое значение периода
     */
    public static int getIntPeriod(String period) {
        int result = 1;
        switch (period) {
            case "квартал": {
                result = 3;
                break;
            }
            case "полугодие": {
                result = 6;
                break;
            }
            case "9 месяцев": {
                result = 9;
                break;
            }
            case "год": {
                result = 12;
                break;
            }
        }
        return result;
    }

    /**
     * Возвращает стандартный шрифт
     * @return Font - стандартный шрифт
     */
    public static Font getDefaultFont() {
        return new Font("Courier", Font.PLAIN, 16);
    }

    /**
     * Возвращает жирный, большой шрифт
     * @return Font - жирный, большой шрифт
     */
    public static Font getBoldFont() {
        return new Font("Courier", Font.BOLD, 20);
    }

    /**
     * Создает текстовое поле для дробных чисел
     * @return field - текстовое поле для дробных чисел
     */
    public static JTextField createFloatFilteredField() {
        JTextField field = new JTextField();
        AbstractDocument document = (AbstractDocument) field.getDocument();
        final int maxCharacters = 15;
        document.setDocumentFilter(new DocumentFilter() {
            public void replace(FilterBypass fb, int offs, int length, String str, AttributeSet a)
                    throws BadLocationException {
                String text = fb.getDocument().getText(0, fb.getDocument().getLength());
                text += str;
                if ((fb.getDocument().getLength() + str.length() - length) <= maxCharacters
                        && text.matches("^[0-9]+[.]?[0-9]{0,2}$")) {
                    super.replace(fb, offs, length, str, a);
                }
            }

            public void insertString(FilterBypass fb, int offs, String str, AttributeSet a)
                    throws BadLocationException {
                String text = fb.getDocument().getText(0, fb.getDocument().getLength());
                text += str;
                if ((fb.getDocument().getLength() + str.length()) <= maxCharacters
                        && text.matches("^[0-9]+[.]?[0-9]{0,2}$")) {
                    super.insertString(fb, offs, str, a);
                }
            }
        });
        return field;
    }

    /**
     * Создает текстовое поле для целых чисел
     * @return field - текстовое поле для целых чисел
     */
    public static JTextField createIntFilteredField() {
        JTextField field = new JTextField();
        AbstractDocument document = (AbstractDocument) field.getDocument();
        final int maxCharacters = 2;
        document.setDocumentFilter(new DocumentFilter() {
            public void replace(FilterBypass fb, int offs, int length, String str, AttributeSet a)
                    throws BadLocationException {
                String text = fb.getDocument().getText(0, fb.getDocument().getLength());
                text += str;
                if ((fb.getDocument().getLength() + str.length() - length) <= maxCharacters
                        && text.matches("^[0-9]+$")) {
                    super.replace(fb, offs, length, str, a);
                }
            }

            public void insertString(FilterBypass fb, int offs, String str, AttributeSet a)
                    throws BadLocationException {
                String text = fb.getDocument().getText(0, fb.getDocument().getLength());
                text += str;
                if ((fb.getDocument().getLength() + str.length()) <= maxCharacters
                        && text.matches("^[0-9]+$")) {
                    super.insertString(fb, offs, str, a);
                }
            }
        });
        return field;
    }
}
