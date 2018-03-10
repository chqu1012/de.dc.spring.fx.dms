package de.dc.spring.fx.dms.controller;

import com.google.common.collect.Iterables;
import de.dc.spring.fx.dms.controller.BaseAddDocumentController;
import de.dc.spring.fx.dms.controller.DMSMainController;
import de.dc.spring.fx.dms.controller.ViewDocumentsController;
import de.dc.spring.fx.dms.model.Category;
import de.dc.spring.fx.dms.model.Ticket;
import de.dc.spring.fx.dms.repository.CategoryRepository;
import de.dc.spring.fx.dms.service.TicketService;
import de.dc.spring.fx.dms.util.FolderUtil;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Consumer;
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
  private CategoryRepository categoryRepository;
  
  @Autowired
  private TicketService ticketService;
  
  @Autowired
  private FolderUtil folderUtil;
  
  private ObservableList<Category> categoryData = FXCollections.<Category>observableArrayList();
  
  public void initialize() {
    List<Category> _findAll = this.categoryRepository.findAll();
    Iterables.<Category>addAll(this.categoryData, _findAll);
    this.categoryComboView.setItems(this.categoryData);
    this.categoryComboView.getSelectionModel().select(0);
    this.createdOnDatePicker.setValue(LocalDate.now());
  }
  
  @Override
  public void onCancel(final ActionEvent event) {
    this.root.getParent().toBack();
  }
  
  @Override
  public void onCreate(final ActionEvent event) {
    String _text = this.nameText.getText();
    String _text_1 = this.descriptionTextArea.getText();
    int _selectedIndex = this.categoryComboView.getSelectionModel().getSelectedIndex();
    LocalDate _value = this.createdOnDatePicker.getValue();
    Ticket ticket = new Ticket(_text, _text_1, _selectedIndex, 0, _value);
    ticket.setUpdatedOn(LocalDate.now());
    this.ticketService.create(ticket);
    this.viewDocumentController.ticketData.add(ticket);
    this.folderUtil.createFolder(ticket);
    this.dmsMainController.showTicket(ticket);
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
      LocalDate _now = LocalDate.now();
      Category _category = new Category(name, _now);
      Category c = this.categoryRepository.<Category>save(_category);
      this.categoryData.add(c);
      this.categoryComboView.getSelectionModel().select(c);
    };
    dialog.showAndWait().ifPresent(_function_1);
  }
}
