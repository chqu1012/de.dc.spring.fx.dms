package de.dc.spring.fx.dms.controller

import com.jfoenix.validation.RequiredFieldValidator
import de.dc.spring.fx.dms.shared.model.Category
import de.dc.spring.fx.dms.shared.repository.CategoryRepository
import de.dc.spring.fx.dms.service.TicketService
import de.dc.spring.fx.dms.util.FolderUtil
import java.io.File
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.event.ActionEvent
import javafx.scene.control.TextInputDialog
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import de.dc.spring.fx.dms.shared.model.Ticket

@Controller 
class AddDocumentController extends BaseAddDocumentController {
	
	@Autowired ViewDocumentsController viewDocumentController
	@Autowired DMSMainController dmsMainController
	@Autowired CategoryRepository categoryRepository
	@Autowired TicketService ticketService
	@Autowired FolderUtil folderUtil

	ObservableList<Category> categoryData = FXCollections.observableArrayList
	ObservableList<String> folderTemplates = FXCollections.observableArrayList(#['Attachments', 'Images', 'Documents', 'Pdfs', 'Templates'])
	ObservableList<File> createdFolders = FXCollections.observableArrayList
	ObservableList<File> importedFiles = FXCollections.observableArrayList
	
	def initialize() {
		categoryData+=categoryRepository.findAll
		categoryComboView.items=categoryData
		categoryComboView.selectionModel.select(0)
		
		createdOnDatePicker.value = LocalDate.now
		createdTimePicker.value = LocalTime.now
		
		val validator = new RequiredFieldValidator
		validator.setMessage("Name is Required")
		nameText.validators+=validator
		nameText.focusedProperty.addListener[o,oldVal,newVal|
			 if(!newVal) nameText.validate
		]
		nameText.validate
		
		folderComboView.items = folderTemplates
		folderListView.items = createdFolders
		filesListView.items = importedFiles
	}

	override onCancel(ActionEvent event) {
		root.parent.toBack
	}

	override onCreate(ActionEvent event) {
		var currentDateTime = LocalDateTime.of(createdOnDatePicker.value, createdTimePicker.value)
		var ticket = new Ticket(nameText.text, descriptionTextArea.text,categoryComboView.selectionModel.selectedIndex, 0, currentDateTime)
		ticket.updatedOn = currentDateTime
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
	
	override onImportFiles(ActionEvent event) {
	}
	
	override onNewFolder(ActionEvent event) {
	}
	
}
