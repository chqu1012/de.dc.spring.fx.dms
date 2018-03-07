package de.dc.spring.fx.dms.controller;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public abstract class BaseDMSMainController {

	@FXML 
	protected AnchorPane calendarPane;
	
	@FXML 
	protected AnchorPane dmsDetailPane;
	
	@FXML
	protected AnchorPane invoiceAnchorPane;
	
	@FXML
	protected AnchorPane howtoAnchorPane;
	
	@FXML
	protected AnchorPane formularAnchorPane;
	
	@FXML
	protected AnchorPane receiptAnchorPane;

    @FXML
    protected AnchorPane viewDocumentsPanel;
	
    @FXML
    protected VBox homePanel;
	
    @FXML
    protected AnchorPane addDocumentPanel;
	
    @FXML
    protected Label titleLabel;

    @FXML
    protected Label home÷abel;

    @FXML
    protected Label addDocumentLabel;

    @FXML
    protected Label viewDocumentLabel;

    @FXML
    protected Label preferencesLabel;

    @FXML
    protected Label calendarLabel;

    @FXML
    protected Label logOutLabel;

    @FXML
    protected Label exitLabel;

    @FXML
    protected TextField searchText;

    @FXML
    protected ImageView profilImage;

    @FXML
    protected StackPane contentStackPane;
    
    @FXML
    protected abstract void onSwitchToAddDocument(MouseEvent event);

    @FXML
    protected abstract void onSwitchToExit(MouseEvent event);

    @FXML
    protected abstract void onSwitchToHome(MouseEvent event);

    @FXML
    protected abstract void onSwitchToLogOut(MouseEvent event);

    @FXML
    protected abstract void onSwitchToPreferences(MouseEvent event);

    @FXML
    protected abstract void onSwitchToViewDocuments(MouseEvent event);

    @FXML
    protected abstract void onSwitchToCalendar(MouseEvent event);

}
