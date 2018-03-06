package de.dc.spring.fx.dms.control.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.apache.commons.io.FileUtils;

import de.dc.spring.fx.dms.control.cell.FileSizeColumnFactory;
import de.dc.spring.fx.dms.control.cell.FileTreeCellFactory;
import de.dc.spring.fx.dms.util.ImageHelper;
import javafx.application.HostServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileViewController extends BaseFileViewController {

	ObservableList<File> fileData = FXCollections.observableArrayList();
	private String ticketFolderPath;
	private HostServices hostServices;
	private FileChooser fc = new FileChooser();
	
	public void initialize() {
		fileTreeView.setCellFactory(new FileTreeCellFactory());
		fileTreeView.setOnMouseClicked(e->{
			TreeItem<File> selectedItem = fileTreeView.getSelectionModel().getSelectedItem();
			if (selectedItem!=null) {
				fileData.clear();
				fileData.addAll(selectedItem.getValue().listFiles());
			}
		});
		fileTable.setItems(fileData);
		fileTable.setOnMouseClicked(e->{
			if (e.getClickCount()==2) {
				onOpenFile(null);
			}else {
				File selectedItem = fileTable.getSelectionModel().getSelectedItem();
				if (selectedItem!=null) {
					String name = selectedItem.getName();
					if (name.endsWith(".png") ||name.endsWith(".bmp")||name.endsWith(".jpg")||name.endsWith(".jpeg")) {
						imageViewScrollPane.toFront();
						imageView.setImage(ImageHelper.getImage(selectedItem));
					}
				}
			}
		});
		
		nameColumn.setCellValueFactory(new PropertyValueFactory<File, String>("name"));
		sizeColumn.setCellFactory(new FileSizeColumnFactory<File, String>());
		
		fc.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("All Files", "*.*"),
				new FileChooser.ExtensionFilter("MS Excel", "*.xlsx"),
				new FileChooser.ExtensionFilter("MS Word", "*.docx"),
				new FileChooser.ExtensionFilter("TEXT", "*.txt"),
				new FileChooser.ExtensionFilter("PDF", "*.pdf"),
				new FileChooser.ExtensionFilter("GIF", "*.gif"),
				new FileChooser.ExtensionFilter("JEPG", "*.jpeg"),
				new FileChooser.ExtensionFilter("JPG", "*.jpg"),
				new FileChooser.ExtensionFilter("PNG", "*.png")
		);
	}

	public void setInput(String ticketFolderPath) {
		this.ticketFolderPath = ticketFolderPath;
		File folder = new File(ticketFolderPath);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		TreeItem<File> items = getItems(ticketFolderPath);
		fileTreeView.setRoot(items);
		fileData.clear();
		imageView.setImage(null);
		
	}
	
	public TreeItem<File> getItems(String path) {
		TreeItem<File> treeItem = new TreeItem<File>(new File(path));
		treeItem.setExpanded(true);
		createTree(treeItem);
		return treeItem;
	}

	public void createTree(TreeItem<File> rootItem) {
		File[] files = rootItem.getValue().listFiles();
		if (files !=null) {
			for (File file : files) {
				if (file.isDirectory()) {
					TreeItem<File> newItem = new TreeItem<File>(file);
					newItem.setExpanded(true);
					rootItem.getChildren().add(newItem);
					createTree(newItem);
				}
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
		if (selectedItem!=null) {
			if (selectedItem.isDirectory()) {
				fileData.clear();
				fileData.addAll(selectedItem.listFiles());
			}else {
				hostServices.showDocument(selectedItem.getAbsolutePath());
			}
		}
	}

	public void setHostServices(HostServices hostServices) {
		this.hostServices = hostServices;
	}

	@Override
	protected void onImportFilesFromTree(ActionEvent event) {
		onImportFiles(true);
	}

	@Override
	protected void onImportFilesFromTable(ActionEvent event) {
		onImportFiles(false);
	}
	
	
	protected void onImportFiles(boolean isTree) {
		List<File> list = fc.showOpenMultipleDialog(new Stage());
		if (list!=null) {
			TreeItem<File> selectedItem = fileTreeView.getSelectionModel().getSelectedItem();
			File selectedFolder = selectedItem.getValue();
			if (selectedFolder.isDirectory()) {
				list.forEach(file->{
					try {
						fileData.add(file);
						FileUtils.copyFileToDirectory(file, selectedFolder);
						fileTreeView.refresh();
						fileTable.refresh();
					} catch (IOException e) {
						e.printStackTrace();
					}
				});
			}else {
				System.err.println("NOT FOLDER SELECTED!");
			}
		}
	}

	@Override
	protected void onDeleteFolder(ActionEvent event) {
		ObservableList<TreeItem<File>> items = fileTreeView.getSelectionModel().getSelectedItems();
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText("Delete "+items.size()+" File / Folders");
		alert.setContentText("Do you really want to delete?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			items.forEach(file-> file.getValue().delete());
		} 
		fileTreeView.refresh();
	}

	@Override
	protected void onDeleteFromTable(ActionEvent event) {
		ObservableList<File> items = fileTable.getSelectionModel().getSelectedItems();
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText("Delete "+items.size()+" File / Folders");
		alert.setContentText("Do you really want to delete?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			items.forEach(file-> file.delete());
			fileTable.getItems().removeAll(items);
		} 
		fileTable.refresh();
	}

}
