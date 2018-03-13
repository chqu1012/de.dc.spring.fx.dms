package de.dc.spring.fx.dms.controller;

import de.dc.spring.fx.dms.controller.AbstractLayoutController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

@SuppressWarnings("all")
public abstract class BaseAboutController extends AbstractLayoutController {
  @FXML
  protected AnchorPane root;
  
  @FXML
  protected abstract void onEmailButton(final ActionEvent event);
  
  @FXML
  protected abstract void onFacebookButton(final ActionEvent event);
  
  @FXML
  protected abstract void onGPlusButton(final ActionEvent event);
  
  @FXML
  protected abstract void onGithubButton(final ActionEvent event);
  
  @FXML
  protected abstract void onLinkedInButton(final ActionEvent event);
  
  @FXML
  protected abstract void onOkButton(final ActionEvent event);
  
  @FXML
  protected abstract void onTwitterButton(final ActionEvent event);
}
