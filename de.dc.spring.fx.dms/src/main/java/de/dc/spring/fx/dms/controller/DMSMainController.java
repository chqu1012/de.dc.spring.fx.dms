package de.dc.spring.fx.dms.controller;

import java.time.LocalDate;

import org.controlsfx.control.textfield.TextFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import de.dc.fx.animation.other.AnimationType;
import de.dc.fx.animation.other.AnimationUtils;
import de.dc.spring.fx.dms.model.Ticket;
import de.dc.spring.fx.dms.service.TicketService;
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
	
	public void initialize() {
		invoiceAnchorPaneController.setImageBackground("#0F62C6");
		howtoAnchorPaneController.setImageBackground("#009AD2");
		formularAnchorPaneController.setImageBackground("#00A0A3");
		receiptAnchorPaneController.setImageBackground("#FF8350");
		lastPanel=homePanel;
		
		// TODO: Fill with DB data
		TextFields.bindAutoCompletion(searchText, ticketService.getAutocompletion());
		
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
		addDocumentPanel.toFront();
		AnimationUtils.createTransition(lastPanel, AnimationType.FADE_OUT_DOWN).play();
		lastPanel=addDocumentPanel;
		AnimationUtils.createTransition(addDocumentPanel, AnimationType.FADE_IN_LEFT).play();
	}

	@Override
	protected void onSwitchToExit(MouseEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onSwitchToHome(MouseEvent event) {
		homePanel.toFront();
		AnimationUtils.createTransition(lastPanel, AnimationType.FADE_OUT_DOWN).play();
		lastPanel=homePanel;
		AnimationUtils.createTransition(homePanel, AnimationType.FADE_IN_LEFT).play();
	}

	@Override
	protected void onSwitchToLogOut(MouseEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onSwitchToPreferences(MouseEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onSwitchToViewDocuments(MouseEvent event) {
		viewDocumentsPanel.toFront();
		AnimationUtils.createTransition(lastPanel, AnimationType.FADE_OUT_DOWN).play();
		lastPanel=viewDocumentsPanel;
		AnimationUtils.createTransition(viewDocumentsPanel, AnimationType.FADE_IN_LEFT).play();
	}

	@Override
	protected void onSwitchToCalendar(MouseEvent event) {
		// TODO Auto-generated method stub
		
	}

	@FXML 
	public void onSearchTextKeyReleased(KeyEvent event) {
		if (event.getCode().equals(KeyCode.ENTER)) {
			searchText.setText("");
		}
	}
	
	public void showTicket(Ticket ticket) {
		dmsDetailPane.toFront();
		AnimationUtils.createTransition(lastPanel, AnimationType.FADE_OUT_DOWN).play();
		lastPanel=dmsDetailPane;
		dmsDetailController.setDetails(ticket);
		AnimationUtils.createTransition(dmsDetailPane, AnimationType.FADE_IN_LEFT).play();
		dmsDetailPane.toFront();
	}

}
