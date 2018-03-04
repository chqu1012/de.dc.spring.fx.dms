package de.dc.spring.fx.dms.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public abstract class BaseDMSDetailController {

	@FXML
	protected Hyperlink documentIdLabel;

	@FXML
	protected Label descriptionLabel;

	@FXML
	protected AnchorPane splitPaneRoot;

	@FXML
	protected TextField authorText;

	@FXML
	protected ComboBox<?> statusCombo;

	@FXML
	protected ComboBox<?> categoryCombo;

	@FXML
	protected Label createdOnLabel;

	@FXML
	protected Label lastUpdate�abel;

	@FXML
	protected TextArea descriptionText;

	@FXML
	protected abstract void onClipboardButton(ActionEvent event);

	@FXML
	protected abstract void onCloneButton(ActionEvent event);

	@FXML
	protected abstract void onCommentButton(ActionEvent event);

	@FXML
	protected abstract void onExplorerButton(ActionEvent event);

	@FXML
	protected abstract void onExportButton(ActionEvent event);

	@FXML
	protected abstract void onFilesButton(ActionEvent event);

	@FXML
	protected abstract void onLinkButton(ActionEvent event);

	@FXML
	protected abstract void onSaveButton(ActionEvent event);

}
