package customClasses;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;

public class TextWrapCellRender implements TableCellRenderer{
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JTextArea textArea = new JTextArea(value!=null ? value.toString() : "");
        textArea.setForeground(isSelected ? Color.decode("#2EA623") : Color.decode("#244E23"));
        textArea.setFont(table.getFont());
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setOpaque(true);

        return textArea;
    }
}
