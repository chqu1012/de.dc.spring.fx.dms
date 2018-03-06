package de.dc.spring.fx.dms.control.controller;

import java.io.File;

import de.dc.spring.fx.dms.control.cell.FileSizeColumnFactory;
import de.dc.spring.fx.dms.control.cell.FileTreeCellFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.PropertyValueFactory;

public class FileViewController extends BaseFileViewController {

//	private static final ImageView FOLDER_ICO = new ImageView(new Image(FileViewController.class.getResourceAsStream("/de/dc/spring/fx/dms/image/icons8_Folder_96px.png")));
	
	ObservableList<File> fileData = FXCollections.observableArrayList();
	
	public void initialize() {
		fileTreeView.setCellFactory(new FileTreeCellFactory());
		fileTreeView.setOnMouseClicked(e->{
			fileData.clear();
			fileData.addAll(fileTreeView.getSelectionModel().getSelectedItem().getValue().listFiles());
		});
		fileTable.setItems(fileData);
		
		nameColumn.setCellValueFactory(new PropertyValueFactory<File, String>("name"));
		sizeColumn.setCellFactory(new FileSizeColumnFactory<File, String>());
	}

	public void setInput(String ticketFolderPath) {
		TreeItem<File> items = getItems(ticketFolderPath);
		fileTreeView.setRoot(items);
	}
	
	public TreeItem<File> getItems(String path) {
		TreeItem<File> treeItem = new TreeItem<File>(new File(path));
		treeItem.setExpanded(true);

		// create tree structure
		createTree(treeItem);
		return treeItem;
	}

	public void createTree(TreeItem<File> rootItem) {
		File[] files = rootItem.getValue().listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
//				ImageIcon icon = (ImageIcon) FileSystemView.getFileSystemView().getSystemIcon(file);
//				Image img = ImageHelper.iconToImageFX(icon);
//				ImageView image = new ImageView(new Image(getClass().getResourceAsStream("/de/dc/spring/fx/dms/image/icons8_Folder_96px.png")));
				TreeItem<File> newItem = new TreeItem<File>(file);
				newItem.setExpanded(true);
				rootItem.getChildren().add(newItem);
				createTree(newItem);
			}
		}
	}

	@Override
	protected void onNewFolder(ActionEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onOpenDirectory(ActionEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onOpenFile(ActionEvent event) {
		// TODO Auto-generated method stub

	}

}
