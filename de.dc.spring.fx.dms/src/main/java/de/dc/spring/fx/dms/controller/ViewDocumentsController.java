package de.dc.spring.fx.dms.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import de.dc.spring.fx.dms.model.Ticket;
import de.dc.spring.fx.dms.repository.TicketRepository;
import de.dc.spring.fx.dms.util.FolderUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;

@Controller
public class ViewDocumentsController extends BaseViewDocumentsController {

	@Autowired TicketRepository ticketRepository;
	@Autowired DMSMainController dmsMainController;
	@Autowired FolderUtil folderUtil;
	
	public ObservableList<Ticket> ticketData = FXCollections.observableArrayList();

	public void initialize() {
		idColumn.setCellValueFactory(new PropertyValueFactory<Ticket, String>("id"));
		nameColumn.setCellValueFactory(new PropertyValueFactory<Ticket, String>("name"));
		createdOnColumn.setCellValueFactory(new PropertyValueFactory<Ticket, String>("createdOn"));

		FilteredList<Ticket> filteredData = new FilteredList<>(ticketData, p -> true);
		searchText.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(ticket -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();
                if (ticket.getId().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; 
                } else if (ticket.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (ticket.getDescription().toLowerCase().contains(lowerCaseFilter)) {
                    return true; 
                }
                return false; // Does not match.
            });
        });
		
		
		ticketData.addAll(ticketRepository.findAll());
		ticketDocument.setItems(filteredData);

		ticketDocument.setOnMouseClicked(e -> {
			Ticket ticket = ticketDocument.getSelectionModel().getSelectedItem();
			descriptionText.setText(ticket.getDescription()==null?"":ticket.getDescription());
			countOfTicketsLabel.setText(ticketData.size()+"");
			Path path = Paths.get(folderUtil.getFolderByTicket(ticket).getAbsolutePath());
			long countOfFiles;
			try {
				countOfFiles = Files.walk(path).parallel().filter(p -> !p.toFile().isDirectory()).count();
				countOfAttachmentsLabel.setText(countOfFiles+"");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});


		fullAnchor(root);
	}

	@Override
	protected void onDeleteButton(ActionEvent event) {
		Ticket selectedItem = ticketDocument.getSelectionModel().getSelectedItem();
		ticketData.remove(selectedItem);
		ticketRepository.delete(selectedItem);
	}

	@Override
	protected void onNewButton(ActionEvent event) {
		dmsMainController.onSwitchToAddDocument(null);
	}

	@Override
	protected void onOpenButton(ActionEvent event) {
		dmsMainController.showTicket(ticketDocument.getSelectionModel().getSelectedItem());
	}

	@Override
	protected void onAddTicketButton(ActionEvent event) {
		onNewButton(event);
	}

	@Override
	protected void onDeleteTicketButton(ActionEvent event) {
		onDeleteButton(event);
	}

	@Override
	protected void onOpenTicketButton(ActionEvent event) {
		onOpenButton(event);
	}
}
