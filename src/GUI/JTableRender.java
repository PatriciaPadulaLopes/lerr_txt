
package GUI;

import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableCellRenderer;


public class JTableRender extends DefaultTableCellRenderer  {
    
    
     @Override
     protected void setValue(Object value) {
        if (value instanceof ImageIcon) {
            if (value != null) {
                ImageIcon d = (ImageIcon) value;
                setIcon(d);
            } else {
                setText("");
                setIcon(null);
            }
        } else {
            super.setValue(value);
        }
    }
}
