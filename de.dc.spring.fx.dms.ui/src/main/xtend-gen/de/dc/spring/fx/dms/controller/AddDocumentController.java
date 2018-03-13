package de.dc.spring.fx.dms.controller;

import com.jfoenix.validation.RequiredFieldValidator;
import com.jfoenix.validation.base.ValidatorBase;
import de.dc.spring.fx.dms.controller.BaseAddDocumentController;
import de.dc.spring.fx.dms.controller.DMSMainController;
import de.dc.spring.fx.dms.controller.ViewDocumentsController;
import de.dc.spring.fx.dms.shared.model.Category;
import de.dc.spring.fx.dms.shared.model.Ticket;
import de.dc.spring.fx.dms.util.FolderUtil;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.function.Consumer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TextInputDialog;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@SuppressWarnings("all")
public class AddDocumentController extends BaseAddDocumentController {
  @Autowired
  private ViewDocumentsController viewDocumentController;
  
  @Autowired
  private DMSMainController dmsMainController;
  
  @Autowired
  private FolderUtil folderUtil;
  
  private ObservableList<Category> categoryData = FXCollections.<Category>observableArrayList();
  
  private ObservableList<String> folderTemplates = FXCollections.<String>observableArrayList(new String[] { "Attachments", "Images", "Documents", "Pdfs", "Templates" });
  
  private ObservableList<File> createdFolders = FXCollections.<File>observableArrayList();
  
  private ObservableList<File> importedFiles = FXCollections.<File>observableArrayList();
  
  public void initialize() {
    this.categoryComboView.setItems(this.categoryData);
    this.categoryComboView.getSelectionModel().select(0);
    this.createdOnDatePicker.setValue(LocalDate.now());
    this.createdTimePicker.setValue(LocalTime.now());
    final RequiredFieldValidator validator = new RequiredFieldValidator();
    validator.setMessage("Name is Required");
    ObservableList<ValidatorBase> _validators = this.nameText.getValidators();
    _validators.add(validator);
    final ChangeListener<Boolean> _function = (ObservableValue<? extends Boolean> o, Boolean oldVal, Boolean newVal) -> {
      if ((!(newVal).booleanValue())) {
        this.nameText.validate();
      }
    };
    this.nameText.focusedProperty().addListener(_function);
    this.nameText.validate();
    this.folderComboView.setItems(this.folderTemplates);
    this.folderListView.setItems(this.createdFolders);
    this.filesListView.setItems(this.importedFiles);
  }
  
  @Override
  public void onCancel(final ActionEvent event) {
    this.root.getParent().toBack();
  }
  
  @Override
  public void onCreate(final ActionEvent event) {
    LocalDateTime currentDateTime = LocalDateTime.of(this.createdOnDatePicker.getValue(), this.createdTimePicker.getValue());
    String _text = this.nameText.getText();
    String _text_1 = this.descriptionTextArea.getText();
    int _selectedIndex = this.categoryComboView.getSelectionModel().getSelectedIndex();
    Ticket ticket = new Ticket(_text, _text_1, _selectedIndex, 0, currentDateTime);
    ticket.setUpdatedOn(currentDateTime);
    this.clearFields();
  }
  
  public void clearFields() {
    this.nameText.setText("");
    this.descriptionTextArea.setText("");
    this.createdOnDatePicker.setValue(LocalDate.now());
  }
  
  @Override
  public void onAddButton(final ActionEvent event) {
    TextInputDialog _textInputDialog = new TextInputDialog();
    final Procedure1<TextInputDialog> _function = (TextInputDialog it) -> {
      it.setTitle("Create new Category");
      it.setHeaderText("Category Name");
      it.setContentText("Please enter a new name for the category:");
    };
    TextInputDialog dialog = ObjectExtensions.<TextInputDialog>operator_doubleArrow(_textInputDialog, _function);
    final Consumer<String> _function_1 = (String name) -> {
    };
    dialog.showAndWait().ifPresent(_function_1);
  }
  
  @Override
  protected void onImportFiles(final ActionEvent event) {
  }
  
  @Override
  protected void onNewFolder(final ActionEvent event) {
  }
}
