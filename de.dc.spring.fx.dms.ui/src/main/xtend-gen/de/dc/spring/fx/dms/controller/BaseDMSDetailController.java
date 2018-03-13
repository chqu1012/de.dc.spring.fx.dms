package de.dc.spring.fx.dms.controller;

import de.dc.spring.fx.dms.controller.AbstractLayoutController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

@SuppressWarnings("all")
public abstract class BaseDMSDetailController extends AbstractLayoutController {
  @FXML
  protected BorderPane root;
  
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
  protected Label lastUpdate÷abel;
  
  @FXML
  protected TextArea descriptionText;
  
  @FXML
  protected abstract void onClipboardButton(final ActionEvent event);
  
  @FXML
  protected abstract void onCloneButton(final ActionEvent event);
  
  @FXML
  protected abstract void onCommentButton(final ActionEvent event);
  
  @FXML
  protected abstract void onExplorerButton(final ActionEvent event);
  
  @FXML
  protected abstract void onExportButton(final ActionEvent event);
  
  @FXML
  protected abstract void onFilesButton(final ActionEvent event);
  
  @FXML
  protected abstract void onLinkButton(final ActionEvent event);
  
  @FXML
  protected abstract void onSaveButton(final ActionEvent event);
  
  @FXML
  protected abstract void onCloseButton(final ActionEvent event);
}
