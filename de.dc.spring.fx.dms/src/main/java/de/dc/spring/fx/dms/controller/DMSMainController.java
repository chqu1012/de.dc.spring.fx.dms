package de.dc.spring.fx.dms.controller;

import org.controlsfx.control.textfield.TextFields;
import org.springframework.stereotype.Controller;

import de.dc.fx.animation.other.AnimationType;
import de.dc.fx.animation.other.AnimationUtils;
import javafx.fxml.FXML;
import javafx.scene.Parent;
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
	
	public void initialize() {
		invoiceAnchorPaneController.setImageBackground("#0F62C6");
		howtoAnchorPaneController.setImageBackground("#009AD2");
		formularAnchorPaneController.setImageBackground("#00A0A3");
		receiptAnchorPaneController.setImageBackground("#FF8350");
		lastPanel=homePanel;
		
		// TODO: Fill with DB data
		TextFields.bindAutoCompletion(searchText, "Hey", "Hello", "Hello World", "Apple", "Cool", "Costa", "Cola", "Coca Cola");
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

}
