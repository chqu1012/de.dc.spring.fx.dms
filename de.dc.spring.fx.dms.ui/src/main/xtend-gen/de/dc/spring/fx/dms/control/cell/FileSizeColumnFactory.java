package de.dc.spring.fx.dms.control.cell;

import java.io.File;
import java.text.DecimalFormat;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

@SuppressWarnings("all")
public class FileSizeColumnFactory<S extends Object, T extends Object> implements Callback<TableColumn<S, T>, TableCell<S, T>> {
  private static DecimalFormat decimalFormat = new DecimalFormat("###0.00 kB");
  
  @Override
  public TableCell<S, T> call(final TableColumn<S, T> p) {
    return new TableCell<S, T>() {
      @Override
      public void updateItem(final Object item, final boolean empty) {
        Object _item = this.getTableRow().getItem();
        File file = ((File) _item);
        super.updateItem(((T) item), empty);
        if ((file != null)) {
          long _length = file.length();
          double _divide = (_length / 1024d);
          this.setText(String.valueOf(FileSizeColumnFactory.decimalFormat.format(_divide)));
        } else {
          this.setText("");
        }
      }
    };
  }
}
