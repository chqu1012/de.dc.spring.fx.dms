package de.dc.spring.fx.dms.control.cell;

import java.io.File;
import java.text.DecimalFormat;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class FileSizeColumnFactory<S, T> implements Callback<TableColumn<S, T>, TableCell<S, T>> {

	private static DecimalFormat decimalFormat = new DecimalFormat("###0.00 kB");
	
	@Override
	public TableCell<S, T> call(TableColumn<S, T> p) {
		TableCell<S, T> cell = new TableCell<S, T>() {
			@Override
			protected void updateItem(Object item, boolean empty) {
				File file = null;
				if (getTableRow() != null) {
					file = (File) getTableRow().getItem();
				}
				super.updateItem((T) item, empty);
				if (file != null) {
					setText(String.valueOf(decimalFormat.format(file.length()/1024d)));
				} else {
					setText("");
				}
			}
		};
		return cell;
	}

}