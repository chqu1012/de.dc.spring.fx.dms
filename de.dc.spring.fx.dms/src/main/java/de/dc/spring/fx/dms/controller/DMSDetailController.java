package de.dc.spring.fx.dms.controller;

import java.text.DecimalFormat;

import org.springframework.stereotype.Controller;

import de.dc.spring.fx.dms.model.Ticket;
import javafx.event.ActionEvent;

@Controller
public class DMSDetailController extends BaseDMSDetailController {

	public static final DecimalFormat format = new DecimalFormat("00000");
	
	public void setDetails(Ticket ticket) {
		documentIdLabel.setText("DMS-"+format.format(ticket.getId()));
		descriptionLabel.setText(ticket.getName());
		descriptionText.setText(ticket.getDescription()==null?"":ticket.getDescription());
		createdOnLabel.setText(ticket.getCreatedOn().toString());
		lastUpdate÷abel.setText(ticket.getCreatedOn().toString());
	}
	
	@Override
	protected void onClipboardButton(ActionEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onCloneButton(ActionEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onCommentButton(ActionEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onExplorerButton(ActionEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onExportButton(ActionEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onFilesButton(ActionEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onLinkButton(ActionEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onSaveButton(ActionEvent event) {
		// TODO Auto-generated method stub

	}

}
