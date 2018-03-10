package de.dc.spring.fx.dms.control.controller;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;

@SuppressWarnings("all")
public abstract class BaseFileViewController {
  @FXML
  protected TreeView<File> fileTreeView;
  
  @FXML
  protected TableView<File> fileTable;
  
  @FXML
  protected TableColumn<File, String> nameColumn;
  
  @FXML
  protected TableColumn<File, String> sizeColumn;
  
  @FXML
  protected ScrollPane imageViewScrollPane;
  
  @FXML
  protected ImageView imageView;
  
  @FXML
  protected ScrollPane textfieldScrollPane;
  
  @FXML
  protected Label fileExtensionLabel;
  
  @FXML
  protected Label fileSizeLabel;
  
  @FXML
  protected Label createdOnLabel;
  
  @FXML
  protected Label lastUpdateLabel;
  
  @FXML
  protected abstract void onNewFolder(final ActionEvent event);
  
  @FXML
  protected abstract void onOpenDirectory(final ActionEvent event);
  
  @FXML
  protected abstract void onOpenFile(final ActionEvent event);
  
  @FXML
  protected abstract void onImportFilesFromTree(final ActionEvent event);
  
  @FXML
  protected abstract void onImportFilesFromTable(final ActionEvent event);
  
  @FXML
  protected abstract void onDeleteFolder(final ActionEvent event);
  
  @FXML
  protected abstract void onDeleteFromTable(final ActionEvent event);
}
