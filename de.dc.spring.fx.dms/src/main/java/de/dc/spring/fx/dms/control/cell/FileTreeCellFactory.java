package de.dc.spring.fx.dms.control.cell;

import java.io.File;

import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeView;
import javafx.util.Callback;

public class FileTreeCellFactory implements Callback<TreeView<File>, TreeCell<File>>{

	@Override
	public TreeCell<File> call(TreeView<File> param) {
		return new TreeCell<File>() {
			@Override
			public void updateItem(File item, boolean empty) {
				super.updateItem(item, empty);
				String text = "";
				if (!empty) {
					text=item.getName();
					if (item.isDirectory()) {
						text=text+" ("+item.listFiles().length+")";
					}
				}
				setText(text);
			}
		};
	}

}
