package de.dc.spring.fx.dms.controller;

import java.time.LocalDate;
import java.util.Optional;

import org.controlsfx.control.textfield.TextFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import de.dc.fx.animation.other.AnimationType;
import de.dc.fx.animation.other.AnimationUtils;
import de.dc.spring.fx.dms.model.Ticket;
import de.dc.spring.fx.dms.service.TicketService;
import de.dc.spring.fx.dms.util.FolderUtil;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

@Controller
public class DMSMainController extends BaseDMSMainController {

	@FXML
	HomeTileController invoiceAnchorPaneController;
	
	@FXML
	HomeTileController howtoAnchorPaneController;
		
	@FXML
	HomeTileController formularAnchorPaneController;
	
	@FXML
	HomeTileController receiptAnchorPaneController;
	
	Parent lastPanel;
	
	@Autowired TicketService ticketService;
	@Autowired DMSDetailController dmsDetailController;
	@Autowired FolderUtil folderUtil;
	
	public void initialize() {
		invoiceAnchorPaneController.setImageBackground("#0F62C6");
		howtoAnchorPaneController.setImageBackground("#009AD2");
		formularAnchorPaneController.setImageBackground("#00A0A3");
		receiptAnchorPaneController.setImageBackground("#FF8350");
		lastPanel=homePanel;
		
		folderUtil.createIfNotExist();
		
		// TODO: Fill with DB data
		TextFields.bindAutoCompletion(searchText, ticketService.getAll());
		
		initTestData(false);
	}

	private void initTestData(boolean initTestData) {
		if (initTestData) {
			for (int i = 0; i < 10; i++) {
				String name="Ticket Number "+i;
				String description="A small description "+i;
				int categoryId=i;
				int userId = i;
				LocalDate createdOn=LocalDate.now();
				Ticket ticket=new Ticket(name, description, categoryId, userId, createdOn);
				ticketService.create(ticket);
			}
		}
	}
	
	@Override
	protected void onSwitchToAddDocument(MouseEvent event) {
		currentPaneLabel.setText("New Document");
		addDocumentPanel.toFront();
		AnimationUtils.createTransition(lastPanel, AnimationType.FADE_OUT_DOWN).play();
		lastPanel=addDocumentPanel;
		AnimationUtils.createTransition(addDocumentPanel, AnimationType.FADE_IN_LEFT).play();
	}

	@Override
	protected void onSwitchToExit(MouseEvent event) {
		currentPaneLabel.setText("Exit");
	}

	@Override
	protected void onSwitchToHome(MouseEvent event) {
		currentPaneLabel.setText("Dashboard");
		homePanel.toFront();
		AnimationUtils.createTransition(lastPanel, AnimationType.FADE_OUT_DOWN).play();
		lastPanel=homePanel;
		AnimationUtils.createTransition(homePanel, AnimationType.FADE_IN_LEFT).play();
	}

	@Override
	protected void onSwitchToLogOut(MouseEvent event) {
		currentPaneLabel.setText("Log Out");
	}

	@Override
	protected void onSwitchToPreferences(MouseEvent event) {
		currentPaneLabel.setText("Preferences");
	}

	@Override
	protected void onSwitchToViewDocuments(MouseEvent event) {
		currentPaneLabel.setText("Documents View");
		viewDocumentsPanel.toFront();
		AnimationUtils.createTransition(lastPanel, AnimationType.FADE_OUT_DOWN).play();
		lastPanel=viewDocumentsPanel;
		AnimationUtils.createTransition(viewDocumentsPanel, AnimationType.FADE_IN_LEFT).play();
	}

	@Override
	protected void onSwitchToCalendar(MouseEvent event) {
		currentPaneLabel.setText("Calendar");
		calendarPane.toFront();
		AnimationUtils.createTransition(lastPanel, AnimationType.FADE_OUT_DOWN).play();
		lastPanel=calendarPane;
		AnimationUtils.createTransition(calendarPane, AnimationType.FADE_IN_LEFT).play();		
	}

	@FXML 
	public void onSearchTextKeyReleased(KeyEvent event) {
		if (event.getCode().equals(KeyCode.ENTER)) {
			String[] searchFields = searchText.getText().split(":");
			String splittedId = searchFields[0].split("-")[1];
			long id = Long.valueOf(splittedId);
			Optional<Ticket> tickets = ticketService.findById(id);			
			showTicket(tickets.get());
			searchText.setText("");
		}
	}
	
	public void showTicket(Ticket ticket) {
		currentPaneLabel.setText("Document Details");
		dmsDetailPane.toFront();
		AnimationUtils.createTransition(lastPanel, AnimationType.FADE_OUT_DOWN).play();
		lastPanel=dmsDetailPane;
		dmsDetailController.setDetails(ticket);
		AnimationUtils.createTransition(dmsDetailPane, AnimationType.FADE_IN_LEFT).play();
		dmsDetailPane.toFront();
	}

	@Override
	protected void onSwitchToHelp(MouseEvent event) {
		currentPaneLabel.setText("Help");		
	}

	@Override
	protected void onSwitchToReport(MouseEvent event) {
		currentPaneLabel.setText("Report");		
	}

}
