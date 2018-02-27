package de.dc.spring.fx.dms.controller;

import org.springframework.stereotype.Controller;

import javafx.scene.input.MouseEvent;

@Controller
public class DMSMainController extends BaseDMSMainController {

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
		// TODO Auto-generated method stub

	}

}
