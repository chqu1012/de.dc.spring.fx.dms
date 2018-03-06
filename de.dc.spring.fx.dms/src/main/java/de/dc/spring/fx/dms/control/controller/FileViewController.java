package de.dc.spring.fx.dms.control.controller;

import java.io.File;

import de.dc.spring.fx.dms.control.cell.FileSizeColumnFactory;
import de.dc.spring.fx.dms.control.cell.FileTreeCellFactory;
import javafx.application.HostServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.PropertyValueFactory;

public class FileViewController extends BaseFileViewController {

	ObservableList<File> fileData = FXCollections.observableArrayList();
	private String ticketFolderPath;
	private HostServices hostServices;
	
	public void initialize() {
		fileTreeView.setCellFactory(new FileTreeCellFactory());
		fileTreeView.setOnMouseClicked(e->{
			fileData.clear();
			fileData.addAll(fileTreeView.getSelectionModel().getSelectedItem().getValue().listFiles());
		});
		fileTable.setItems(fileData);
		fileTable.setOnMouseClicked(e->{
			if (e.getClickCount()==2) {
				onOpenFile(null);
			}
		});
		
		nameColumn.setCellValueFactory(new PropertyValueFactory<File, String>("name"));
		sizeColumn.setCellFactory(new FileSizeColumnFactory<File, String>());
	}

	public void setInput(String ticketFolderPath) {
		this.ticketFolderPath = ticketFolderPath;
		TreeItem<File> items = getItems(ticketFolderPath);
		fileTreeView.setRoot(items);
	}
	
	public TreeItem<File> getItems(String path) {
		TreeItem<File> treeItem = new TreeItem<File>(new File(path));
		treeItem.setExpanded(true);
		createTree(treeItem);
		return treeItem;
	}

	public void createTree(TreeItem<File> rootItem) {
		File[] files = rootItem.getValue().listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				TreeItem<File> newItem = new TreeItem<File>(file);
				newItem.setExpanded(true);
				rootItem.getChildren().add(newItem);
				createTree(newItem);
			}
		}
	}

	@Override
	protected void onNewFolder(ActionEvent event) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Create new folder");
		dialog.setHeaderText("Folder Name");
		dialog.setContentText("Please enter a new name for the new folder:");
		dialog.showAndWait().ifPresent(name ->{ 
			File selectedFolder = fileTreeView.getSelectionModel().getSelectedItem().getValue();
			String folderPath = selectedFolder.getAbsolutePath();
			File newFolder = new File(folderPath+"/"+name);
			newFolder.mkdirs();
			fileTreeView.getSelectionModel().getSelectedItem().getChildren().add(new TreeItem<File>(newFolder));
		});
	}

	@Override
	protected void onOpenDirectory(ActionEvent event) {
		hostServices.showDocument(ticketFolderPath);
	}

	@Override
	protected void onOpenFile(ActionEvent event) {
		File selectedItem = fileTable.getSelectionModel().getSelectedItem();
		if (selectedItem.isDirectory()) {
			fileData.clear();
			fileData.addAll(selectedItem.listFiles());
		}else {
			hostServices.showDocument(selectedItem.getAbsolutePath());
		}
	}

	public void setHostServices(HostServices hostServices) {
		this.hostServices = hostServices;
	}

}
