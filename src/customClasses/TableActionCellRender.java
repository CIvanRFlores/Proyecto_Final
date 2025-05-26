package customClasses;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;

public class TableActionCellRender extends JPanel implements TableCellRenderer{
	
	private static final long serialVersionUID = 1L;
	
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSeleted, boolean hasFocus, int row, int column) {
        ActionButtonPanel action = new ActionButtonPanel();
        return action;
    }
}
