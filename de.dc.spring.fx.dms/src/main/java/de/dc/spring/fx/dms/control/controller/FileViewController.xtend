package de.dc.spring.fx.dms.control.controller

import de.dc.spring.fx.dms.control.cell.FileSizeColumnFactory
import de.dc.spring.fx.dms.control.cell.FileTreeCellFactory
import java.io.File
import javafx.application.HostServices
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.event.ActionEvent
import javafx.scene.control.Alert
import javafx.scene.control.Alert.AlertType
import javafx.scene.control.ButtonType
import javafx.scene.control.TextInputDialog
import javafx.scene.control.TreeItem
import javafx.scene.control.cell.PropertyValueFactory
import javafx.stage.FileChooser
import javafx.stage.Stage
import org.apache.commons.io.FileUtils

import static extension de.dc.spring.fx.dms.util.ImageHelper.getImage

class FileViewController extends BaseFileViewController {
	
	ObservableList<File> fileData = FXCollections::observableArrayList
	String ticketFolderPath
	HostServices hostServices
	FileChooser fc = new FileChooser

	def initialize() {
		fileTreeView.cellFactory = new FileTreeCellFactory
		fileTreeView.onMouseClicked =[
			var selectedItem = fileTreeView.selectionModel.selectedItem
			if (selectedItem !== null) {
				fileData.clear
				fileData+=selectedItem.value.listFiles
			}
		]
		fileTable.items = fileData
		fileTable.onMouseClicked = [e |
			if (e.clickCount === 2) {
				onOpenFile(null)
			} else {
				var selectedItem = fileTable.selectionModel.selectedItem
				if (selectedItem !== null) {
					var name = selectedItem.name
					if (name.endsWith(".png") || name.endsWith(".bmp") || name.endsWith(".jpg") ||
						name.endsWith(".jpeg")) {
						imageViewScrollPane.toFront
						imageView.setImage(selectedItem.image)
					}
				}
			}
		]
		nameColumn.cellValueFactory = new PropertyValueFactory("name")
		sizeColumn.cellFactory = new FileSizeColumnFactory
		fc.extensionFilters += #[
			new FileChooser.ExtensionFilter("All Files", "*.*"),
			new FileChooser.ExtensionFilter("MS Excel", "*.xlsx"), 
			new FileChooser.ExtensionFilter("MS Word", "*.docx"),
			new FileChooser.ExtensionFilter("TEXT", "*.txt"), 
			new FileChooser.ExtensionFilter("PDF", "*.pdf"),
			new FileChooser.ExtensionFilter("GIF", "*.gif"), 
			new FileChooser.ExtensionFilter("JEPG", "*.jpeg"),
			new FileChooser.ExtensionFilter("JPG", "*.jpg"), 
			new FileChooser.ExtensionFilter("PNG", "*.png")
		]
	}

	def setInput(String ticketFolderPath) {
		this.ticketFolderPath = ticketFolderPath
		new File(ticketFolderPath).mkdirs
		fileTreeView.root = ticketFolderPath.items
		fileData.clear
		imageView.setImage(null)
	}

	def getItems(String path) {
		new TreeItem(new File(path))=>[
			expanded=true
			it.createTree
		]
	}

	def createTree(TreeItem<File> rootItem) {
		var files = rootItem.value.listFiles
		if (files !== null) {
			files.forEach[file|
				if(file.isDirectory){
					rootItem.children += new TreeItem(file) =>[
						expanded = true
						it.createTree
					]
				}
			]
		}
	}

	override onNewFolder(ActionEvent event) {
		var dialog = new TextInputDialog =>[
			title = "Create new folder"
			headerText = "Folder Name"
			contentText = "Please enter a new name for the new folder:"
		]
		dialog.showAndWait.ifPresent = [name |
			var selectedFolder = fileTreeView.selectionModel.selectedItem.value
			var folderPath = selectedFolder.absolutePath
			var newFolder = new File('''«folderPath»/«name»'''.toString)
			newFolder.mkdirs
			fileTreeView.selectionModel.selectedItem.children += new TreeItem(newFolder)
		]
	}

	override onOpenDirectory(ActionEvent event) {
		hostServices.showDocument = ticketFolderPath
	}

	override onOpenFile(ActionEvent event) {
		var selectedItem = fileTable.selectionModel.selectedItem
		if (selectedItem !== null) {
			if (selectedItem.isDirectory) {
				fileData.clear
				fileData+=selectedItem.listFiles
			} else {
				hostServices.showDocument = selectedItem.absolutePath
			}
		}
	}

	def setHostServices(HostServices hostServices) {
		this.hostServices = hostServices
	}

	override onImportFilesFromTree(ActionEvent event) {
		onImportFiles = true
	}

	override onImportFilesFromTable(ActionEvent event) {
		onImportFiles = false
	}

	def onImportFiles(boolean isTree) {
		var list = fc.showOpenMultipleDialog(new Stage)
		if (list !== null) {
			var selectedItem = fileTreeView.selectionModel.selectedItem
			val selectedFolder = selectedItem.value
			if (selectedFolder.isDirectory) {
				list.forEach[file |
					fileData+=file
					FileUtils::copyFileToDirectory(file, selectedFolder)
					fileTreeView.refresh
					fileTable.refresh
				]
			}
		}
	}

	override onDeleteFolder(ActionEvent event) {
		val items = fileTreeView.selectionModel.getSelectedItems()
		var alert = new Alert(AlertType::CONFIRMATION)=>[
			title="Confirmation Dialog"
			headerText='''Delete «items.size» File / Folders'''
			contentText="Do you really want to delete?"
		]
		var result = alert.showAndWait
		if (result.get === ButtonType::OK) {
			items.forEach[file|file.value.delete]
		}
		fileTreeView.refresh
	}

	override onDeleteFromTable(ActionEvent event) {
		val items = fileTable.getSelectionModel().getSelectedItems()
		var Alert alert = new Alert(AlertType::CONFIRMATION)=>[
			title="Confirmation Dialog"
			headerText='''Delete «items.size» File / Folders'''
			contentText="Do you really want to delete?"
		]
		var result = alert.showAndWait
		if (result.get === ButtonType::OK) {
			items.forEach[file|file.delete]
			fileTable.items-=items
		}
		fileTable.refresh
	}
}
