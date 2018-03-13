package de.dc.spring.fx.dms.control.cell

import java.io.File
import java.text.DecimalFormat
import javafx.scene.control.TableCell
import javafx.scene.control.TableColumn
import javafx.util.Callback

class FileSizeColumnFactory<S, T> implements Callback<TableColumn<S, T>, TableCell<S, T>> {
	
	static DecimalFormat decimalFormat = new DecimalFormat("###0.00 kB")

	override call(TableColumn<S, T> p) {
		new TableCell<S, T>() {
			override updateItem(Object item, boolean empty) {
				var File file = tableRow.item as File
				super.updateItem((item as T), empty)
				if (file !== null) {
					text = String.valueOf(decimalFormat.format(file.length / 1024d))
				} else {
					text = ''
				}
			}
		}
	}
}
