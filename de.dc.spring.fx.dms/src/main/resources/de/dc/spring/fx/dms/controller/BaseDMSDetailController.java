package de.dc.spring.fx.dms.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public abstract class BaseDMSDetailController {

    @FXML
    protected TableView<?> filesTableView;

    @FXML
    protected TableColumn<?, ?> fileExtensionColumn;

    @FXML
    protected TableColumn<?, ?> filenameColumn;

    @FXML
    protected TableColumn<?, ?> filesizeColumn;

    @FXML
    protected TableColumn<?, ?> fileUpdateOnColumn;

    @FXML
    protected TableColumn<?, ?> fileSourceColumn;

    @FXML
    protected TextField desriptionText;

    @FXML
    protected TextField authorText;

    @FXML
    protected ComboBox<?> statusCombo;

    @FXML
    protected ComboBox<?> categoryCombo;

    @FXML
    protected Label createdOnLabel;

    @FXML
    protected Label lastUpdate÷abel;

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
