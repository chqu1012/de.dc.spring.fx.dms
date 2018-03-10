package de.dc.spring.fx.dms.controller;

import com.jfoenix.controls.JFXTextField;

import de.dc.spring.fx.dms.model.Ticket;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public abstract class BaseViewDocumentsController extends AbstractLayoutController {

	@FXML
	protected JFXTextField searchText;

	@FXML
	protected TableView<Ticket> ticketDocument;

	@FXML
	protected TableColumn<Ticket, String> idColumn;

	@FXML
	protected TableColumn<Ticket, String> nameColumn;

	@FXML
	protected TableColumn<Ticket, String> createdOnColumn;

	@FXML
	protected AnchorPane root;

	@FXML
	protected TextArea descriptionText;

	@FXML
	protected ImageView categoryImageView;

	@FXML
	protected Label categoryLabel;

	@FXML
	protected Label countOfTicketsLabel;

	@FXML
	protected Label countOfAttachmentsLabel;

	@FXML
	protected abstract void onDeleteButton(ActionEvent event);

	@FXML
	protected abstract void onNewButton(ActionEvent event);

	@FXML
	protected abstract void onOpenButton(ActionEvent event);
}
