package de.dc.spring.fx.dms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import de.dc.spring.fx.dms.model.Ticket;
import de.dc.spring.fx.dms.repository.TicketRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

@Controller
public class ViewDocumentsController extends BaseViewDocumentsController {

	@Autowired TicketRepository ticketRepository;
	@Autowired DMSMainController dmsMainController;
	
	ObservableList<Ticket> ticketData = FXCollections.observableArrayList();
	
	public void initialize() {
		idColumn.setCellValueFactory(new PropertyValueFactory<Ticket, String>("id"));
		nameColumn.setCellValueFactory(new PropertyValueFactory<Ticket, String>("name"));
		descriptionColumn.setCellValueFactory(new PropertyValueFactory<Ticket, String>("description"));
		categoryColumn.setCellValueFactory(new PropertyValueFactory<Ticket, String>("categoryId"));
		createdOnColumn.setCellValueFactory(new PropertyValueFactory<Ticket, String>("createdOn"));
		
		ticketData.addAll(ticketRepository.findAll());
		ticketDocument.setItems(ticketData);
		
		ticketDocument.setOnMouseClicked(e->{
			if (e.getClickCount()==2) {
				dmsMainController.showTicket(ticketDocument.getSelectionModel().getSelectedItem());
			}
		});
	}
}
