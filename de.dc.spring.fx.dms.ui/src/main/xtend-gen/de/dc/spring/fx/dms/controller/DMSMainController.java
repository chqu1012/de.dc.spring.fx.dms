package de.dc.spring.fx.dms.controller;

import de.dc.fx.animation.other.AnimationType;
import de.dc.fx.animation.other.AnimationUtils;
import de.dc.spring.fx.dms.controller.BaseDMSMainController;
import de.dc.spring.fx.dms.controller.DMSDetailController;
import de.dc.spring.fx.dms.controller.HomeTileController;
import de.dc.spring.fx.dms.service.TicketDtoService;
import de.dc.spring.fx.dms.shared.model.Ticket;
import de.dc.spring.fx.dms.util.FolderUtil;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import org.eclipse.xtext.xbase.lib.Exceptions;
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
  private DMSDetailController dmsDetailController;
  
  @Autowired
  private FolderUtil folderUtil;
  
  @Autowired
  private TicketDtoService dtoService;
  
  public AutoCompletionBinding<Ticket> initialize() {
    AutoCompletionBinding<Ticket> _xblockexpression = null;
    {
      this.invoiceAnchorPaneController.setImageBackground("#0F62C6");
      this.howtoAnchorPaneController.setImageBackground("#009AD2");
      this.formularAnchorPaneController.setImageBackground("#00A0A3");
      this.receiptAnchorPaneController.setImageBackground("#FF8350");
      this.lastPanel = this.homePanel;
      this.folderUtil.createIfNotExist();
      AutoCompletionBinding<Ticket> _xtrycatchfinallyexpression = null;
      try {
        _xtrycatchfinallyexpression = TextFields.<Ticket>bindAutoCompletion(this.searchText, this.dtoService.getTickets());
      } catch (final Throwable _t) {
        if (_t instanceof Exception) {
          final Exception e = (Exception)_t;
          e.printStackTrace();
        } else {
          throw Exceptions.sneakyThrow(_t);
        }
      }
      _xblockexpression = _xtrycatchfinallyexpression;
    }
    return _xblockexpression;
  }
  
  @Override
  public void onSwitchToAddDocument(final MouseEvent event) {
    this.changePanel("New Document", this.addDocumentPanel);
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
    this.changePanel("Documents View", this.viewDocumentsPanel);
  }
  
  @Override
  public void onSwitchToCalendar(final MouseEvent event) {
    this.changePanel("Calendar", this.calendarPane);
  }
  
  @FXML
  public void onSearchTextKeyReleased(final KeyEvent event) {
    boolean _equals = event.getCode().equals(KeyCode.ENTER);
    if (_equals) {
      String[] searchFields = this.searchText.getText().split(":");
      String splittedId = searchFields[0].split("-")[1];
      Long id = Long.valueOf(splittedId);
      Ticket ticket = this.dtoService.getTicketById((id).longValue());
      this.showTicket(ticket);
      this.searchText.setText("");
    }
  }
  
  public void showTicket(final Ticket ticket) {
    this.dmsDetailController.setDetails(ticket);
    this.changePanel("Document Details", this.dmsDetailPane);
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
