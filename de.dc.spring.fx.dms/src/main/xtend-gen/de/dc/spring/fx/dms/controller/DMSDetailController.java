package de.dc.spring.fx.dms.controller;

import de.dc.spring.fx.dms.control.controller.FileViewController;
import de.dc.spring.fx.dms.controller.BaseDMSDetailController;
import de.dc.spring.fx.dms.controller.DMSMainController;
import de.dc.spring.fx.dms.shared.model.Ticket;
import de.dc.spring.fx.dms.util.FolderUtil;
import java.net.URL;
import java.text.DecimalFormat;
import javafx.application.HostServices;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@SuppressWarnings("all")
public class DMSDetailController extends BaseDMSDetailController {
  @Autowired
  private FolderUtil folderUtil;
  
  @Autowired
  private HostServices hostServices;
  
  @Autowired
  private DMSMainController dmsMainController;
  
  public final static DecimalFormat format = new DecimalFormat("00000");
  
  private FileViewController fileViewController;
  
  public boolean initialize() {
    try {
      boolean _xblockexpression = false;
      {
        URL _resource = this.getClass().getResource("/de/dc/spring/fx/dms/control/FileView.fxml");
        FXMLLoader loader = new FXMLLoader(_resource);
        Node fileView = loader.<Node>load();
        this.fileViewController = loader.<FileViewController>getController();
        this.fileViewController.setHostServices(this.hostServices);
        this.fullAnchor(this.root, 0, 0, 0, 0);
        this.fullAnchor(fileView, 0, 0, 0, 0);
        ObservableList<Node> _children = this.splitPaneRoot.getChildren();
        _xblockexpression = _children.add(fileView);
      }
      return _xblockexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public void setDetails(final Ticket ticket) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("DMS-");
    String _format = DMSDetailController.format.format(ticket.getId());
    _builder.append(_format);
    this.documentIdLabel.setText(_builder.toString());
    this.descriptionLabel.setText(ticket.getName());
    String _xifexpression = null;
    String _description = ticket.getDescription();
    boolean _tripleEquals = (_description == null);
    if (_tripleEquals) {
      _xifexpression = "";
    } else {
      _xifexpression = ticket.getDescription();
    }
    this.descriptionText.setText(_xifexpression);
    this.createdOnLabel.setText(ticket.getCreatedOn().toString());
    this.lastUpdate÷abel.setText(ticket.getCreatedOn().toString());
    this.fileViewController.setInput(this.folderUtil.getFolderByTicket(ticket).getAbsolutePath());
  }
  
  @Override
  public void onClipboardButton(final ActionEvent event) {
  }
  
  @Override
  public void onCloneButton(final ActionEvent event) {
  }
  
  @Override
  public void onCommentButton(final ActionEvent event) {
  }
  
  @Override
  public void onExplorerButton(final ActionEvent event) {
  }
  
  @Override
  public void onExportButton(final ActionEvent event) {
  }
  
  @Override
  public void onFilesButton(final ActionEvent event) {
  }
  
  @Override
  public void onLinkButton(final ActionEvent event) {
  }
  
  @Override
  public void onSaveButton(final ActionEvent event) {
  }
  
  @Override
  public void onCloseButton(final ActionEvent event) {
    this.dmsMainController.onSwitchToViewDocuments(null);
  }
}
