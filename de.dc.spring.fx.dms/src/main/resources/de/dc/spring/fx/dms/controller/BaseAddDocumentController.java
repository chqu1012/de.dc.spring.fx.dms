package de.dc.spring.fx.dms.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public abstract class BaseAddDocumentController {

	@FXML 
	protected AnchorPane root;
	
    @FXML
    protected TextField nameText;

    @FXML
    protected ComboBox<?> categoryComboView;

    @FXML
    protected TextArea descriptionTextArea;

    @FXML
    protected Button createButton;

    @FXML
    protected Button cancelButton;

    @FXML
    protected abstract void onCancel(ActionEvent event);

    @FXML
    protected abstract void onCreate(ActionEvent event);
}
