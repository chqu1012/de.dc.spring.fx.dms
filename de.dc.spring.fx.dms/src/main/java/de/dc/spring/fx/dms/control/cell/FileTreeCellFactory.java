package de.dc.spring.fx.dms.control.cell;

import java.io.File;

import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeView;
import javafx.util.Callback;

public class FileTreeCellFactory implements Callback<TreeView<File>, TreeCell<File>>{

	public static final File folderFile= new File("de/dc/spring/fx/dms/image/icons8_Folder_96px.png");
	
	@Override
	public TreeCell<File> call(TreeView<File> param) {
		return new TreeCell<File>() {
			@Override
			public void updateItem(File item, boolean empty) {
				super.updateItem(item, empty);
				String text = "";
				if (empty) {
	                setGraphic(null);
	            }
				if (!empty) {
					text=item.getName();
					if (item.isDirectory()) {
						text=text+" ("+item.listFiles().length+")";
					}
//					 Image image = ImageHelper.getImage(folderFile);
//					setGraphic(new ImageView(image));
				}
				setText(text);
			}
		};
	}

}
