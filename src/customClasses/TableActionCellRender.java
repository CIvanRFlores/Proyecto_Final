package customClasses;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;

public class TableActionCellRender implements TableCellRenderer{
	
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        ActionButtonPanel action = new ActionButtonPanel();
        return action;
    }
  
}
