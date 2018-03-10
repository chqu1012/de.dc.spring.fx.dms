package de.dc.spring.fx.dms.controller

import com.jfoenix.controls.JFXDatePicker
import de.dc.spring.fx.dms.model.Category
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.ComboBox
import javafx.scene.control.TextArea
import javafx.scene.control.TextField
import javafx.scene.layout.AnchorPane

abstract class BaseAddDocumentController {
	@FXML protected AnchorPane root
	@FXML protected TextField nameText
	@FXML protected JFXDatePicker createdOnDatePicker
	@FXML protected ComboBox<Category> categoryComboView
	@FXML protected TextArea descriptionTextArea
	@FXML protected Button createButton
	@FXML protected Button cancelButton

	@FXML def protected abstract void onCancel(ActionEvent event)

	@FXML def protected abstract void onCreate(ActionEvent event)

	@FXML def protected abstract void onAddButton(ActionEvent event)

}
