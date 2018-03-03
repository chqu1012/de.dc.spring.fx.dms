package de.dc.spring.fx.dms.controller;

import de.dc.spring.fx.dms.model.Ticket;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public abstract class BaseViewDocumentsController {

	@FXML
	protected TableView<Ticket> ticketDocument;

	@FXML
	protected TableColumn<Ticket, String> idColumn;

	@FXML
	protected TableColumn<Ticket, String> nameColumn;

	@FXML
	protected TableColumn<Ticket, String> descriptionColumn;

	@FXML
	protected TableColumn<Ticket, String> categoryColumn;

	@FXML
	protected TableColumn<Ticket, String> createdOnColumn;

}
