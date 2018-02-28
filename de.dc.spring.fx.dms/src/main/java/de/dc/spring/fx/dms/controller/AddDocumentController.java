package de.dc.spring.fx.dms.controller;

import org.springframework.stereotype.Controller;

import javafx.event.ActionEvent;

@Controller
public class AddDocumentController extends BaseAddDocumentController {

	@Override
	protected void onCancel(ActionEvent event) {
		root.getParent().toBack();
	}

	@Override
	protected void onCreate(ActionEvent event) {
		root.getParent().toBack();
	}

}
