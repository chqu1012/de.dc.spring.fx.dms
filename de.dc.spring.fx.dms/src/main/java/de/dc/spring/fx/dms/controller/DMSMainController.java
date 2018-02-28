package de.dc.spring.fx.dms.controller;

import org.springframework.stereotype.Controller;

import javafx.fxml.FXML;
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
	
	public void initialize() {
		invoiceAnchorPaneController.setImageBackground("#0F62C6");
		howtoAnchorPaneController.setImageBackground("#009AD2");
		formularAnchorPaneController.setImageBackground("#00A0A3");
		receiptAnchorPaneController.setImageBackground("#FF8350");
	}
	
	@Override
	protected void onSwitchToAddDocument(MouseEvent event) {
		addDocumentPanel.toFront();
	}

	@Override
	protected void onSwitchToExit(MouseEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onSwitchToHome(MouseEvent event) {
		homePanel.toFront();
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
	}

}
