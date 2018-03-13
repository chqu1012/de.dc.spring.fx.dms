package de.dc.spring.fx.dms.control.cell

import java.io.File
import javafx.scene.control.TreeCell
import javafx.scene.control.TreeView
import javafx.util.Callback

class FileTreeCellFactory implements Callback<TreeView<File>, TreeCell<File>> {
	override TreeCell<File> call(TreeView<File> param) {
		new TreeCell<File>() {
			override updateItem(File item, boolean isEmpty) {
				super.updateItem(item, isEmpty)
				var String text = ""
				if (empty) {
					setGraphic(null)
				}
				if (!isEmpty) {
					text = item.name
					if (item.isDirectory) {
						text = '''«text» («item.listFiles.length»)'''
					}
				}
				setText(text)
			}
		}
	}
}
