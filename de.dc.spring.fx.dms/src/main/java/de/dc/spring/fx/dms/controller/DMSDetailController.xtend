package de.dc.spring.fx.dms.controller

import java.io.IOException
import java.text.DecimalFormat
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import de.dc.spring.fx.dms.control.controller.FileViewController
import de.dc.spring.fx.dms.model.Ticket
import de.dc.spring.fx.dms.util.FolderUtil
import javafx.application.HostServices
import javafx.event.ActionEvent
import javafx.fxml.FXMLLoader
import javafx.scene.control.SplitPane

@Controller class DMSDetailController extends BaseDMSDetailController {
	
	@Autowired FolderUtil folderUtil
	@Autowired HostServices hostServices
	@Autowired DMSMainController dmsMainController
	
	public static final DecimalFormat format = new DecimalFormat("00000")
	
	FileViewController fileViewController

	def initialize() throws IOException {
		var loader = new FXMLLoader(class.getResource("/de/dc/spring/fx/dms/control/FileView.fxml"))
		var p = loader.load
		fileViewController = loader.controller
		fileViewController.hostServices = hostServices
		root.fullAnchor
		p.fullAnchor
		splitPaneRoot.children+=p
	}

	def setDetails(Ticket ticket) {
		documentIdLabel.text = '''DMS-«format.format(ticket.id)»'''
		descriptionLabel.text = ticket.name
		descriptionText.text = if(ticket.description === null) "" else ticket.description
		createdOnLabel.text = ticket.createdOn.toString
		lastUpdateÖabel.text = ticket.createdOn.toString
		fileViewController.input = folderUtil.getFolderByTicket(ticket).absolutePath
	}

	override onClipboardButton(ActionEvent event) { // TODO Auto-generated method stub
	}

	override onCloneButton(ActionEvent event) { // TODO Auto-generated method stub
	}

	override onCommentButton(ActionEvent event) { // TODO Auto-generated method stub
	}

	override onExplorerButton(ActionEvent event) { // TODO Auto-generated method stub
	}

	override onExportButton(ActionEvent event) { // TODO Auto-generated method stub
	}

	override onFilesButton(ActionEvent event) { // TODO Auto-generated method stub
	}

	override onLinkButton(ActionEvent event) { // TODO Auto-generated method stub
	}

	override onSaveButton(ActionEvent event) { // TODO Auto-generated method stub
	}

	override onCloseButton(ActionEvent event) {
		dmsMainController.onSwitchToViewDocuments(null)
	}
}
