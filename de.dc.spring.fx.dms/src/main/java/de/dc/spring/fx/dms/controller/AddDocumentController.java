package de.dc.spring.fx.dms.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import de.dc.spring.fx.dms.model.Category;
import de.dc.spring.fx.dms.repository.CategoryRepository;
import de.dc.spring.fx.dms.service.TicketService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TextInputDialog;

@Controller
public class AddDocumentController extends BaseAddDocumentController {

	@Autowired CategoryRepository categoryRepository;
	@Autowired TicketService ticketService;

	ObservableList<Category> categoryData = FXCollections.observableArrayList();
	
	public void initialize() {
		categoryData.addAll(categoryRepository.findAll());
		categoryComboView.setItems(categoryData);
		categoryComboView.getSelectionModel().select(0);
		createdOnDatePicker.setValue(LocalDate.now());
	}
	
	@Override
	protected void onCancel(ActionEvent event) {
		root.getParent().toBack();
	}

	@Override
	protected void onCreate(ActionEvent event) {
		root.getParent().toBack();
	}

	@Override
	protected void onAddButton(ActionEvent event) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Create new Category");
		dialog.setHeaderText("Category Name");
		dialog.setContentText("Please enter a new name for the category:");
		dialog.showAndWait().ifPresent(name ->{ 
			Category c = categoryRepository.save(new Category(name, LocalDate.now()));
			categoryData.add(c);
			categoryComboView.getSelectionModel().select(c);
		});
	}

}
