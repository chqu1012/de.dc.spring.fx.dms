package de.dc.spring.fx.dms.controller

import com.jfoenix.controls.JFXComboBox
import com.jfoenix.controls.JFXDatePicker
import com.jfoenix.controls.JFXTextField
import com.jfoenix.controls.JFXTimePicker
import de.dc.spring.fx.dms.model.Category
import java.io.File
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.ComboBox
import javafx.scene.control.ListView
import javafx.scene.control.TextArea
import javafx.scene.layout.AnchorPane

abstract class BaseAddDocumentController {
	
	@FXML protected AnchorPane root
	@FXML protected JFXTimePicker createdTimePicker
	@FXML protected JFXTextField nameText
	@FXML protected JFXDatePicker createdOnDatePicker
	@FXML protected ComboBox<Category> categoryComboView
	@FXML protected TextArea descriptionTextArea
	@FXML protected Button createButton
	@FXML protected Button cancelButton
    @FXML protected JFXTextField idText
    @FXML protected JFXTextField selectedFolder

    @FXML protected JFXComboBox<String> folderComboView;
    @FXML protected ListView<File> folderListView;
    @FXML protected ListView<File> filesListView;
 
    @FXML def protected abstract void onImportFiles(ActionEvent event)
    @FXML def protected abstract void onNewFolder(ActionEvent event)
	@FXML def protected abstract void onCancel(ActionEvent event)
	@FXML def protected abstract void onCreate(ActionEvent event)
	@FXML def protected abstract void onAddButton(ActionEvent event)

}
