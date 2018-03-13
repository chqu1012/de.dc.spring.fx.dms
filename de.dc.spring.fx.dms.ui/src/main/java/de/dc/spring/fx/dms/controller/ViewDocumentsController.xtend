package de.dc.spring.fx.dms.controller

import de.dc.spring.fx.dms.shared.model.Ticket
import de.dc.spring.fx.dms.util.FolderUtil
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.collections.transformation.FilteredList
import javafx.event.ActionEvent
import javafx.scene.control.Alert
import javafx.scene.control.Alert.AlertType
import javafx.scene.control.ButtonType
import javafx.scene.control.cell.PropertyValueFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller

@Controller 
class ViewDocumentsController extends BaseViewDocumentsController {
	
	@Autowired DMSMainController dmsMainController
	@Autowired FolderUtil folderUtil
	
	public ObservableList<Ticket> ticketData = FXCollections::observableArrayList

	def initialize() {
		idColumn.cellValueFactory=new PropertyValueFactory("id")
		nameColumn.cellValueFactory=new PropertyValueFactory("name")
		createdOnColumn.cellValueFactory=new PropertyValueFactory("createdOn")
		val filteredData = new FilteredList(ticketData, [p|true])
		searchText.textProperty.addListener[obs, oldValue, newValue|
			filteredData.predicate=[ticket|
				if (newValue === null || newValue.isEmpty) {
					return true
				}
				var lowerCaseFilter = newValue.toLowerCase
				if (ticket.id.toString.toLowerCase.contains(lowerCaseFilter)) {
					return true
				} else if (ticket.name.toLowerCase.contains(lowerCaseFilter)) {
					return true
				} else if (ticket.description.toLowerCase.contains(lowerCaseFilter)) {
					return true
				}
				return false // Does not match.
			]
		]
//		ticketData+=ticketRepository.findAll
		ticketDocument.items = filteredData
		ticketDocument.setOnMouseClicked[e |
			var ticket = ticketDocument.selectionModel.selectedItem
			descriptionText.text = if(ticket.description === null) "" else ticket.description
			countOfTicketsLabel.text = '''«ticketData.size»'''
//			var path = Paths::get(folderUtil.getFolderByTicket(ticket).absolutePath)
//			var long countOfFiles = Files::walk(path).parallel().filter([p|!p.toFile().isDirectory()]).count()
//			countOfAttachmentsLabel.text = '''«countOfFiles»'''
		]
		root.fullAnchor
	}

	override onDeleteButton(ActionEvent event) {
		val alert = new Alert(AlertType.CONFIRMATION)=>[
			title="Confirmation Dialog"
			headerText="Do you really want to delete this ticket"
			contentText="Delete?"
		]
		val result = alert.showAndWait
		if(result.get==ButtonType::OK){
			var selectedItem = ticketDocument.selectionModel.selectedItem
			ticketData-=selectedItem
//			ticketRepository.delete(selectedItem)
//			folderUtil.deleteFolderWithContent(selectedItem)
		}
	}

	override onNewButton(ActionEvent event) {
		dmsMainController.onSwitchToAddDocument(null)
	}

	override onOpenButton(ActionEvent event) {
		dmsMainController.showTicket = ticketDocument.selectionModel.selectedItem
	}

	override onAddTicketButton(ActionEvent event) {
		onNewButton(event)
	}

	override onDeleteTicketButton(ActionEvent event) {
		onDeleteButton(event)
	}

	override onOpenTicketButton(ActionEvent event) {
		onOpenButton(event)
	}
}
