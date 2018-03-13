package de.dc.spring.fx.dms.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

@SuppressWarnings("all")
public abstract class BaseDMSMainController {
  @FXML
  protected AnchorPane aboutPane;
  
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
  protected Label currentPaneLabel;
  
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
  protected abstract void onSwitchToAddDocument(final MouseEvent event);
  
  @FXML
  protected abstract void onSwitchToExit(final MouseEvent event);
  
  @FXML
  protected abstract void onSwitchToHome(final MouseEvent event);
  
  @FXML
  protected abstract void onSwitchToLogOut(final MouseEvent event);
  
  @FXML
  protected abstract void onSwitchToPreferences(final MouseEvent event);
  
  @FXML
  protected abstract void onSwitchToViewDocuments(final MouseEvent event);
  
  @FXML
  protected abstract void onSwitchToCalendar(final MouseEvent event);
  
  @FXML
  protected abstract void onSwitchToHelp(final MouseEvent event);
  
  @FXML
  protected abstract void onSwitchToReport(final MouseEvent event);
  
  @FXML
  protected abstract void onSwitchToAbout(final MouseEvent event);
}
