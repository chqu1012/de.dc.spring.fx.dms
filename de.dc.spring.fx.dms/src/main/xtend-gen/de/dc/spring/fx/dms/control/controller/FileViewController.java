package de.dc.spring.fx.dms.control.controller;

import com.google.common.collect.Iterables;
import de.dc.spring.fx.dms.control.cell.FileSizeColumnFactory;
import de.dc.spring.fx.dms.control.cell.FileTreeCellFactory;
import de.dc.spring.fx.dms.control.controller.BaseFileViewController;
import de.dc.spring.fx.dms.util.ImageHelper;
import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import javafx.application.HostServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class FileViewController extends BaseFileViewController {
  private ObservableList<File> fileData = FXCollections.<File>observableArrayList();
  
  private String ticketFolderPath;
  
  private HostServices hostServices;
  
  private FileChooser fc = new FileChooser();
  
  public boolean initialize() {
    boolean _xblockexpression = false;
    {
      FileTreeCellFactory _fileTreeCellFactory = new FileTreeCellFactory();
      this.fileTreeView.setCellFactory(_fileTreeCellFactory);
      final EventHandler<MouseEvent> _function = (MouseEvent it) -> {
        TreeItem<File> selectedItem = this.fileTreeView.getSelectionModel().getSelectedItem();
        if ((selectedItem != null)) {
          this.fileData.clear();
          File[] _listFiles = selectedItem.getValue().listFiles();
          Iterables.<File>addAll(this.fileData, ((Iterable<? extends File>)Conversions.doWrapArray(_listFiles)));
        }
      };
      this.fileTreeView.setOnMouseClicked(_function);
      this.fileTable.setItems(this.fileData);
      final EventHandler<MouseEvent> _function_1 = (MouseEvent e) -> {
        int _clickCount = e.getClickCount();
        boolean _tripleEquals = (_clickCount == 2);
        if (_tripleEquals) {
          this.onOpenFile(null);
        } else {
          File selectedItem = this.fileTable.getSelectionModel().getSelectedItem();
          if ((selectedItem != null)) {
            String name = selectedItem.getName();
            if ((((name.endsWith(".png") || name.endsWith(".bmp")) || name.endsWith(".jpg")) || 
              name.endsWith(".jpeg"))) {
              this.imageViewScrollPane.toFront();
              this.imageView.setImage(ImageHelper.getImage(selectedItem));
            }
          }
        }
      };
      this.fileTable.setOnMouseClicked(_function_1);
      PropertyValueFactory<File, String> _propertyValueFactory = new PropertyValueFactory<File, String>("name");
      this.nameColumn.setCellValueFactory(_propertyValueFactory);
      FileSizeColumnFactory<File, String> _fileSizeColumnFactory = new FileSizeColumnFactory<File, String>();
      this.sizeColumn.setCellFactory(_fileSizeColumnFactory);
      ObservableList<FileChooser.ExtensionFilter> _extensionFilters = this.fc.getExtensionFilters();
      FileChooser.ExtensionFilter _extensionFilter = new FileChooser.ExtensionFilter("All Files", "*.*");
      FileChooser.ExtensionFilter _extensionFilter_1 = new FileChooser.ExtensionFilter("MS Excel", "*.xlsx");
      FileChooser.ExtensionFilter _extensionFilter_2 = new FileChooser.ExtensionFilter("MS Word", "*.docx");
      FileChooser.ExtensionFilter _extensionFilter_3 = new FileChooser.ExtensionFilter("TEXT", "*.txt");
      FileChooser.ExtensionFilter _extensionFilter_4 = new FileChooser.ExtensionFilter("PDF", "*.pdf");
      FileChooser.ExtensionFilter _extensionFilter_5 = new FileChooser.ExtensionFilter("GIF", "*.gif");
      FileChooser.ExtensionFilter _extensionFilter_6 = new FileChooser.ExtensionFilter("JEPG", "*.jpeg");
      FileChooser.ExtensionFilter _extensionFilter_7 = new FileChooser.ExtensionFilter("JPG", "*.jpg");
      FileChooser.ExtensionFilter _extensionFilter_8 = new FileChooser.ExtensionFilter("PNG", "*.png");
      _xblockexpression = Iterables.<FileChooser.ExtensionFilter>addAll(_extensionFilters, Collections.<FileChooser.ExtensionFilter>unmodifiableList(CollectionLiterals.<FileChooser.ExtensionFilter>newArrayList(_extensionFilter, _extensionFilter_1, _extensionFilter_2, _extensionFilter_3, _extensionFilter_4, _extensionFilter_5, _extensionFilter_6, _extensionFilter_7, _extensionFilter_8)));
    }
    return _xblockexpression;
  }
  
  public void setInput(final String ticketFolderPath) {
    this.ticketFolderPath = ticketFolderPath;
    new File(ticketFolderPath).mkdirs();
    this.fileTreeView.setRoot(this.getItems(ticketFolderPath));
    this.fileData.clear();
    this.imageView.setImage(null);
  }
  
  public TreeItem<File> getItems(final String path) {
    File _file = new File(path);
    TreeItem<File> _treeItem = new TreeItem<File>(_file);
    final Procedure1<TreeItem<File>> _function = (TreeItem<File> it) -> {
      it.setExpanded(true);
      this.createTree(it);
    };
    return ObjectExtensions.<TreeItem<File>>operator_doubleArrow(_treeItem, _function);
  }
  
  public void createTree(final TreeItem<File> rootItem) {
    File[] files = rootItem.getValue().listFiles();
    if ((files != null)) {
      final File[] _converted_files = (File[])files;
      final Consumer<File> _function = (File file) -> {
        boolean _isDirectory = file.isDirectory();
        if (_isDirectory) {
          ObservableList<TreeItem<File>> _children = rootItem.getChildren();
          TreeItem<File> _treeItem = new TreeItem<File>(file);
          final Procedure1<TreeItem<File>> _function_1 = (TreeItem<File> it) -> {
            it.setExpanded(true);
            this.createTree(it);
          };
          TreeItem<File> _doubleArrow = ObjectExtensions.<TreeItem<File>>operator_doubleArrow(_treeItem, _function_1);
          _children.add(_doubleArrow);
        }
      };
      ((List<File>)Conversions.doWrapArray(_converted_files)).forEach(_function);
    }
  }
  
  @Override
  public void onNewFolder(final ActionEvent event) {
    TextInputDialog _textInputDialog = new TextInputDialog();
    final Procedure1<TextInputDialog> _function = (TextInputDialog it) -> {
      it.setTitle("Create new folder");
      it.setHeaderText("Folder Name");
      it.setContentText("Please enter a new name for the new folder:");
    };
    TextInputDialog dialog = ObjectExtensions.<TextInputDialog>operator_doubleArrow(_textInputDialog, _function);
    Optional<String> _showAndWait = dialog.showAndWait();
    final Consumer<String> _function_1 = (String name) -> {
      File selectedFolder = this.fileTreeView.getSelectionModel().getSelectedItem().getValue();
      String folderPath = selectedFolder.getAbsolutePath();
      StringConcatenation _builder = new StringConcatenation();
      _builder.append(folderPath);
      _builder.append("/");
      _builder.append(name);
      String _string = _builder.toString();
      File newFolder = new File(_string);
      newFolder.mkdirs();
      ObservableList<TreeItem<File>> _children = this.fileTreeView.getSelectionModel().getSelectedItem().getChildren();
      TreeItem<File> _treeItem = new TreeItem<File>(newFolder);
      _children.add(_treeItem);
    };
    _showAndWait.ifPresent(_function_1);
  }
  
  @Override
  public void onOpenDirectory(final ActionEvent event) {
    this.hostServices.showDocument(this.ticketFolderPath);
  }
  
  @Override
  public void onOpenFile(final ActionEvent event) {
    File selectedItem = this.fileTable.getSelectionModel().getSelectedItem();
    if ((selectedItem != null)) {
      boolean _isDirectory = selectedItem.isDirectory();
      if (_isDirectory) {
        this.fileData.clear();
        File[] _listFiles = selectedItem.listFiles();
        Iterables.<File>addAll(this.fileData, ((Iterable<? extends File>)Conversions.doWrapArray(_listFiles)));
      } else {
        this.hostServices.showDocument(selectedItem.getAbsolutePath());
      }
    }
  }
  
  public HostServices setHostServices(final HostServices hostServices) {
    return this.hostServices = hostServices;
  }
  
  @Override
  public void onImportFilesFromTree(final ActionEvent event) {
    this.onImportFiles(true);
  }
  
  @Override
  public void onImportFilesFromTable(final ActionEvent event) {
    this.onImportFiles(false);
  }
  
  public void onImportFiles(final boolean isTree) {
    Stage _stage = new Stage();
    List<File> list = this.fc.showOpenMultipleDialog(_stage);
    if ((list != null)) {
      TreeItem<File> selectedItem = this.fileTreeView.getSelectionModel().getSelectedItem();
      final File selectedFolder = selectedItem.getValue();
      boolean _isDirectory = selectedFolder.isDirectory();
      if (_isDirectory) {
        final Consumer<File> _function = (File file) -> {
          try {
            this.fileData.add(file);
            FileUtils.copyFileToDirectory(file, selectedFolder);
            this.fileTreeView.refresh();
            this.fileTable.refresh();
          } catch (Throwable _e) {
            throw Exceptions.sneakyThrow(_e);
          }
        };
        list.forEach(_function);
      }
    }
  }
  
  @Override
  public void onDeleteFolder(final ActionEvent event) {
    final ObservableList<TreeItem<File>> items = this.fileTreeView.getSelectionModel().getSelectedItems();
    Alert _alert = new Alert(Alert.AlertType.CONFIRMATION);
    final Procedure1<Alert> _function = (Alert it) -> {
      it.setTitle("Confirmation Dialog");
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Delete ");
      int _size = items.size();
      _builder.append(_size);
      _builder.append(" File / Folders");
      it.setHeaderText(_builder.toString());
      it.setContentText("Do you really want to delete?");
    };
    Alert alert = ObjectExtensions.<Alert>operator_doubleArrow(_alert, _function);
    Optional<ButtonType> result = alert.showAndWait();
    ButtonType _get = result.get();
    boolean _tripleEquals = (_get == ButtonType.OK);
    if (_tripleEquals) {
      final Consumer<TreeItem<File>> _function_1 = (TreeItem<File> file) -> {
        file.getValue().delete();
      };
      items.forEach(_function_1);
    }
    this.fileTreeView.refresh();
  }
  
  @Override
  public void onDeleteFromTable(final ActionEvent event) {
    final ObservableList<File> items = this.fileTable.getSelectionModel().getSelectedItems();
    Alert _alert = new Alert(Alert.AlertType.CONFIRMATION);
    final Procedure1<Alert> _function = (Alert it) -> {
      it.setTitle("Confirmation Dialog");
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Delete ");
      int _size = items.size();
      _builder.append(_size);
      _builder.append(" File / Folders");
      it.setHeaderText(_builder.toString());
      it.setContentText("Do you really want to delete?");
    };
    Alert alert = ObjectExtensions.<Alert>operator_doubleArrow(_alert, _function);
    Optional<ButtonType> result = alert.showAndWait();
    ButtonType _get = result.get();
    boolean _tripleEquals = (_get == ButtonType.OK);
    if (_tripleEquals) {
      final Consumer<File> _function_1 = (File file) -> {
        file.delete();
      };
      items.forEach(_function_1);
      ObservableList<File> _items = this.fileTable.getItems();
      Iterables.removeAll(_items, items);
    }
    this.fileTable.refresh();
  }
}
