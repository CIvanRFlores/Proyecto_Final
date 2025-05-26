package customClasses;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class TableActionCellEditor extends DefaultCellEditor {
	
	private static final long serialVersionUID = -1928193725932346802L;

	public TableActionEvent event;

    public TableActionCellEditor(TableActionEvent event) {
        super(new JCheckBox());
        this.event = event;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
    	ActionButtonPanel action = new ActionButtonPanel();
        action.initEvent(event, row);
        action.setBackground(table.getSelectionBackground());
        return action;
    }
}