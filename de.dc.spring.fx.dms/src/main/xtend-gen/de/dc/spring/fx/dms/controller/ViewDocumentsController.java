package de.dc.spring.fx.dms.controller;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import de.dc.spring.fx.dms.controller.BaseViewDocumentsController;
import de.dc.spring.fx.dms.controller.DMSMainController;
import de.dc.spring.fx.dms.model.Ticket;
import de.dc.spring.fx.dms.repository.TicketRepository;
import de.dc.spring.fx.dms.util.FolderUtil;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@SuppressWarnings("all")
public class ViewDocumentsController extends BaseViewDocumentsController {
  @Autowired
  private TicketRepository ticketRepository;
  
  @Autowired
  private DMSMainController dmsMainController;
  
  @Autowired
  private FolderUtil folderUtil;
  
  public ObservableList<Ticket> ticketData = FXCollections.<Ticket>observableArrayList();
  
  public void initialize() {
    PropertyValueFactory<Ticket, String> _propertyValueFactory = new PropertyValueFactory<Ticket, String>("id");
    this.idColumn.setCellValueFactory(_propertyValueFactory);
    PropertyValueFactory<Ticket, String> _propertyValueFactory_1 = new PropertyValueFactory<Ticket, String>("name");
    this.nameColumn.setCellValueFactory(_propertyValueFactory_1);
    PropertyValueFactory<Ticket, String> _propertyValueFactory_2 = new PropertyValueFactory<Ticket, String>("createdOn");
    this.createdOnColumn.setCellValueFactory(_propertyValueFactory_2);
    final Predicate<Ticket> _function = (Ticket p) -> {
      return true;
    };
    final FilteredList<Ticket> filteredData = new FilteredList<Ticket>(this.ticketData, _function);
    final ChangeListener<String> _function_1 = (ObservableValue<? extends String> obs, String oldValue, String newValue) -> {
      final Predicate<Ticket> _function_2 = (Ticket ticket) -> {
        if (((newValue == null) || newValue.isEmpty())) {
          return true;
        }
        String lowerCaseFilter = newValue.toLowerCase();
        boolean _contains = ticket.getId().toString().toLowerCase().contains(lowerCaseFilter);
        if (_contains) {
          return true;
        } else {
          boolean _contains_1 = ticket.getName().toLowerCase().contains(lowerCaseFilter);
          if (_contains_1) {
            return true;
          } else {
            boolean _contains_2 = ticket.getDescription().toLowerCase().contains(lowerCaseFilter);
            if (_contains_2) {
              return true;
            }
          }
        }
        return false;
      };
      filteredData.setPredicate(_function_2);
    };
    this.searchText.textProperty().addListener(_function_1);
    List<Ticket> _findAll = this.ticketRepository.findAll();
    Iterables.<Ticket>addAll(this.ticketData, _findAll);
    this.ticketDocument.setItems(filteredData);
    final EventHandler<MouseEvent> _function_2 = (MouseEvent e) -> {
      try {
        Ticket ticket = this.ticketDocument.getSelectionModel().getSelectedItem();
        String _xifexpression = null;
        String _description = ticket.getDescription();
        boolean _tripleEquals = (_description == null);
        if (_tripleEquals) {
          _xifexpression = "";
        } else {
          _xifexpression = ticket.getDescription();
        }
        this.descriptionText.setText(_xifexpression);
        StringConcatenation _builder = new StringConcatenation();
        int _size = this.ticketData.size();
        _builder.append(_size);
        this.countOfTicketsLabel.setText(_builder.toString());
        Path path = Paths.get(this.folderUtil.getFolderByTicket(ticket).getAbsolutePath());
        final Predicate<Path> _function_3 = (Path p) -> {
          boolean _isDirectory = p.toFile().isDirectory();
          return (!_isDirectory);
        };
        long countOfFiles = Files.walk(path).parallel().filter(_function_3).count();
        StringConcatenation _builder_1 = new StringConcatenation();
        _builder_1.append(countOfFiles);
        this.countOfAttachmentsLabel.setText(_builder_1.toString());
      } catch (Throwable _e) {
        throw Exceptions.sneakyThrow(_e);
      }
    };
    this.ticketDocument.setOnMouseClicked(_function_2);
    this.fullAnchor(this.root);
  }
  
  @Override
  public void onDeleteButton(final ActionEvent event) {
    Alert _alert = new Alert(Alert.AlertType.CONFIRMATION);
    final Procedure1<Alert> _function = (Alert it) -> {
      it.setTitle("Confirmation Dialog");
      it.setHeaderText("Do you really want to delete this ticket");
      it.setContentText("Delete?");
    };
    final Alert alert = ObjectExtensions.<Alert>operator_doubleArrow(_alert, _function);
    final Optional<ButtonType> result = alert.showAndWait();
    ButtonType _get = result.get();
    boolean _equals = Objects.equal(_get, ButtonType.OK);
    if (_equals) {
      Ticket selectedItem = this.ticketDocument.getSelectionModel().getSelectedItem();
      this.ticketData.remove(selectedItem);
      this.ticketRepository.delete(selectedItem);
      this.folderUtil.deleteFolderWithContent(selectedItem);
    }
  }
  
  @Override
  public void onNewButton(final ActionEvent event) {
    this.dmsMainController.onSwitchToAddDocument(null);
  }
  
  @Override
  public void onOpenButton(final ActionEvent event) {
    this.dmsMainController.showTicket(this.ticketDocument.getSelectionModel().getSelectedItem());
  }
  
  @Override
  public void onAddTicketButton(final ActionEvent event) {
    this.onNewButton(event);
  }
  
  @Override
  public void onDeleteTicketButton(final ActionEvent event) {
    this.onDeleteButton(event);
  }
  
  @Override
  public void onOpenTicketButton(final ActionEvent event) {
    this.onOpenButton(event);
  }
}
