package de.dc.spring.fx.dms.controller

import de.dc.fx.animation.other.AnimationUtils
import de.dc.spring.fx.dms.shared.model.Ticket
import de.dc.spring.fx.dms.service.TicketService
import de.dc.spring.fx.dms.util.FolderUtil
import javafx.fxml.FXML
import javafx.scene.Parent
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.scene.input.MouseEvent
import javafx.scene.layout.Pane
import org.controlsfx.control.textfield.TextFields
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller

import static de.dc.fx.animation.other.AnimationType.*

@Controller 
class DMSMainController extends BaseDMSMainController {
	
	@FXML HomeTileController invoiceAnchorPaneController
	@FXML HomeTileController howtoAnchorPaneController
	@FXML HomeTileController formularAnchorPaneController
	@FXML HomeTileController receiptAnchorPaneController
	
	Parent lastPanel
	
	@Autowired TicketService ticketService
	@Autowired DMSDetailController dmsDetailController
	@Autowired FolderUtil folderUtil

	def initialize() {
		invoiceAnchorPaneController.imageBackground = "#0F62C6"
		howtoAnchorPaneController.imageBackground = "#009AD2"
		formularAnchorPaneController.imageBackground = "#00A0A3"
		receiptAnchorPaneController.imageBackground = "#FF8350"
		lastPanel = homePanel
		folderUtil.createIfNotExist

		TextFields.bindAutoCompletion(searchText, ticketService.all)
	}

	override onSwitchToAddDocument(MouseEvent event) {
		changePanel("New Document", addDocumentPanel)
	}

	override onSwitchToExit(MouseEvent event) {
		currentPaneLabel.text = "Exit"
	}

	override onSwitchToHome(MouseEvent event) {
		changePanel("Dashboard", homePanel)
	}

	override onSwitchToLogOut(MouseEvent event) {
		currentPaneLabel.text = "Log Out"
	}

	override onSwitchToPreferences(MouseEvent event) {
		currentPaneLabel.text = "Preferences"
	}

	override onSwitchToViewDocuments(MouseEvent event) {
		changePanel("Documents View", viewDocumentsPanel)
	}

	override onSwitchToCalendar(MouseEvent event) {
		changePanel("Calendar", calendarPane)
	}

	@FXML def onSearchTextKeyReleased(KeyEvent event) {
		if (event.code.equals(KeyCode.ENTER)) {
			var searchFields = searchText.text.split(":")
			var splittedId = searchFields.get(0).split("-").get(1)
			var id = Long.valueOf(splittedId)
			var tickets = ticketService.findById(id)
			tickets.get.showTicket
			searchText.text = ""
		}
	}

	def showTicket(Ticket ticket) {
//		currentPaneLabel.text = "Document Details"
//		dmsDetailPane.toFront
//		AnimationUtils.createTransition(lastPanel, FADE_OUT_DOWN).play
//		lastPanel = dmsDetailPane
//		dmsDetailController.details = ticket
//		AnimationUtils.createTransition(dmsDetailPane, FADE_IN_LEFT).play
//		dmsDetailPane.toFront

		dmsDetailController.details = ticket
		changePanel("Document Details", dmsDetailPane)
	}

	override onSwitchToHelp(MouseEvent event) {
		currentPaneLabel.text = "Help"
	}

	override onSwitchToReport(MouseEvent event) {
		currentPaneLabel.text = "Report"
	}
	
	override protected onSwitchToAbout(MouseEvent event) {
		changePanel("About", aboutPane)
	}
	
	def changePanel(String text, Pane parent){
		currentPaneLabel.text = text
		parent.toFront
		AnimationUtils.createTransition(lastPanel, FADE_OUT_DOWN).play
		lastPanel = parent
		AnimationUtils.createTransition(parent, FADE_IN_LEFT).play
	}
	
}
