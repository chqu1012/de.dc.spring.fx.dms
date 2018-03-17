package de.dc.spring.fx.dms.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import de.dc.spring.fx.dms.shared.model.Category;
import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

@SuppressWarnings("all")
public abstract class BaseAddDocumentController {
  @FXML
  protected AnchorPane root;
  
  @FXML
  protected JFXTimePicker createdTimePicker;
  
  @FXML
  protected JFXTextField nameText;
  
  @FXML
  protected JFXDatePicker createdOnDatePicker;
  
  @FXML
  protected ComboBox<Category> categoryComboView;
  
  @FXML
  protected TextArea descriptionTextArea;
  
  @FXML
  protected Button createButton;
  
  @FXML
  protected Button cancelButton;
  
  @FXML
  protected JFXTextField idText;
  
  @FXML
  protected JFXTextField selectedFolder;
  
  @FXML
  protected JFXComboBox<String> folderComboView;
  
  @FXML
  protected ListView<File> folderListView;
  
  @FXML
  protected ListView<File> filesListView;
  
  @FXML
  protected abstract void onImportFiles(final ActionEvent event);
  
  @FXML
  protected abstract void onNewFolder(final ActionEvent event);
  
  @FXML
  protected abstract void onCancel(final ActionEvent event);
  
  @FXML
  protected abstract void onCreate(final ActionEvent event);
  
  @FXML
  protected abstract void onAddButton(final ActionEvent event);
}
