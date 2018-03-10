package de.dc.spring.fx.dms.controller

import java.time.LocalDate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import de.dc.spring.fx.dms.model.Category
import de.dc.spring.fx.dms.model.Ticket
import de.dc.spring.fx.dms.repository.CategoryRepository
import de.dc.spring.fx.dms.service.TicketService
import de.dc.spring.fx.dms.util.FolderUtil
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.event.ActionEvent
import javafx.scene.control.TextInputDialog

@Controller 
class AddDocumentController extends BaseAddDocumentController {
	
	@Autowired ViewDocumentsController viewDocumentController
	@Autowired DMSMainController dmsMainController
	@Autowired CategoryRepository categoryRepository
	@Autowired TicketService ticketService
	@Autowired FolderUtil folderUtil
	ObservableList<Category> categoryData = FXCollections.observableArrayList

	def initialize() {
		categoryData+=categoryRepository.findAll
		categoryComboView.items=categoryData
		categoryComboView.selectionModel.select(0)
		createdOnDatePicker.value = LocalDate.now
	}

	override onCancel(ActionEvent event) {
		root.parent.toBack
	}

	override onCreate(ActionEvent event) {
		var ticket = new Ticket(nameText.text, descriptionTextArea.text,
			categoryComboView.selectionModel.selectedIndex, 0, createdOnDatePicker.value)
		ticket.updatedOn = LocalDate.now
		ticketService.create = ticket
		viewDocumentController.ticketData+=ticket
		folderUtil.createFolder=ticket
		dmsMainController.showTicket = ticket
		clearFields
	}

	def clearFields() {
		nameText.text = ''
		descriptionTextArea.text = ''
		createdOnDatePicker.value = LocalDate.now
	}

	override onAddButton(ActionEvent event) {
		var dialog = new TextInputDialog => [
			title = "Create new Category"
			headerText = "Category Name" 
			contentText = "Please enter a new name for the category:"
		]
		dialog.showAndWait.ifPresent[name |
			var c = categoryRepository.save(new Category(name, LocalDate.now))
			categoryData += c
			categoryComboView.selectionModel.select(c)
		]
	}
}
