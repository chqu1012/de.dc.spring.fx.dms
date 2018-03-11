package de.dc.spring.fx.dms.controller;

import de.dc.fx.animation.other.AnimationType;
import de.dc.fx.animation.other.AnimationUtils;
import de.dc.spring.fx.dms.controller.BaseDMSMainController;
import de.dc.spring.fx.dms.controller.DMSDetailController;
import de.dc.spring.fx.dms.controller.HomeTileController;
import de.dc.spring.fx.dms.model.Ticket;
import de.dc.spring.fx.dms.service.TicketService;
import de.dc.spring.fx.dms.util.FolderUtil;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@SuppressWarnings("all")
public class DMSMainController extends BaseDMSMainController {
  @FXML
  private HomeTileController invoiceAnchorPaneController;
  
  @FXML
  private HomeTileController howtoAnchorPaneController;
  
  @FXML
  private HomeTileController formularAnchorPaneController;
  
  @FXML
  private HomeTileController receiptAnchorPaneController;
  
  private Parent lastPanel;
  
  @Autowired
  private TicketService ticketService;
  
  @Autowired
  private DMSDetailController dmsDetailController;
  
  @Autowired
  private FolderUtil folderUtil;
  
  public AutoCompletionBinding<Ticket> initialize() {
    AutoCompletionBinding<Ticket> _xblockexpression = null;
    {
      this.invoiceAnchorPaneController.setImageBackground("#0F62C6");
      this.howtoAnchorPaneController.setImageBackground("#009AD2");
      this.formularAnchorPaneController.setImageBackground("#00A0A3");
      this.receiptAnchorPaneController.setImageBackground("#FF8350");
      this.lastPanel = this.homePanel;
      this.folderUtil.createIfNotExist();
      _xblockexpression = TextFields.<Ticket>bindAutoCompletion(this.searchText, this.ticketService.getAll());
    }
    return _xblockexpression;
  }
  
  @Override
  public void onSwitchToAddDocument(final MouseEvent event) {
    this.currentPaneLabel.setText("New Document");
    this.addDocumentPanel.toFront();
    AnimationUtils.createTransition(this.lastPanel, AnimationType.FADE_OUT_DOWN).play();
    this.lastPanel = this.addDocumentPanel;
    AnimationUtils.createTransition(this.addDocumentPanel, AnimationType.FADE_IN_LEFT).play();
  }
  
  @Override
  public void onSwitchToExit(final MouseEvent event) {
    this.currentPaneLabel.setText("Exit");
  }
  
  @Override
  public void onSwitchToHome(final MouseEvent event) {
    this.changePanel("Dashboard", this.homePanel);
  }
  
  @Override
  public void onSwitchToLogOut(final MouseEvent event) {
    this.currentPaneLabel.setText("Log Out");
  }
  
  @Override
  public void onSwitchToPreferences(final MouseEvent event) {
    this.currentPaneLabel.setText("Preferences");
  }
  
  @Override
  public void onSwitchToViewDocuments(final MouseEvent event) {
    this.currentPaneLabel.setText("Documents View");
    this.viewDocumentsPanel.toFront();
    AnimationUtils.createTransition(this.lastPanel, AnimationType.FADE_OUT_DOWN).play();
    this.lastPanel = this.viewDocumentsPanel;
    AnimationUtils.createTransition(this.viewDocumentsPanel, AnimationType.FADE_IN_LEFT).play();
  }
  
  @Override
  public void onSwitchToCalendar(final MouseEvent event) {
    this.currentPaneLabel.setText("Calendar");
    this.calendarPane.toFront();
    AnimationUtils.createTransition(this.lastPanel, AnimationType.FADE_OUT_DOWN).play();
    this.lastPanel = this.calendarPane;
    AnimationUtils.createTransition(this.calendarPane, AnimationType.FADE_IN_LEFT).play();
  }
  
  @FXML
  public void onSearchTextKeyReleased(final KeyEvent event) {
    boolean _equals = event.getCode().equals(KeyCode.ENTER);
    if (_equals) {
      String[] searchFields = this.searchText.getText().split(":");
      String splittedId = searchFields[0].split("-")[1];
      Long id = Long.valueOf(splittedId);
      Optional<Ticket> tickets = this.ticketService.findById(id);
      this.showTicket(tickets.get());
      this.searchText.setText("");
    }
  }
  
  public void showTicket(final Ticket ticket) {
    this.currentPaneLabel.setText("Document Details");
    this.dmsDetailPane.toFront();
    AnimationUtils.createTransition(this.lastPanel, AnimationType.FADE_OUT_DOWN).play();
    this.lastPanel = this.dmsDetailPane;
    this.dmsDetailController.setDetails(ticket);
    AnimationUtils.createTransition(this.dmsDetailPane, AnimationType.FADE_IN_LEFT).play();
    this.dmsDetailPane.toFront();
  }
  
  @Override
  public void onSwitchToHelp(final MouseEvent event) {
    this.currentPaneLabel.setText("Help");
  }
  
  @Override
  public void onSwitchToReport(final MouseEvent event) {
    this.currentPaneLabel.setText("Report");
  }
  
  @Override
  protected void onSwitchToAbout(final MouseEvent event) {
    this.changePanel("About", this.aboutPane);
  }
  
  public void changePanel(final String text, final Pane parent) {
    this.currentPaneLabel.setText(text);
    parent.toFront();
    AnimationUtils.createTransition(this.lastPanel, AnimationType.FADE_OUT_DOWN).play();
    this.lastPanel = parent;
    AnimationUtils.createTransition(parent, AnimationType.FADE_IN_LEFT).play();
  }
}
