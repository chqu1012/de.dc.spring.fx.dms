package de.dc.spring.fx.dms.controller;

import de.dc.spring.fx.dms.controller.BaseAboutController;
import javafx.event.ActionEvent;
import org.springframework.stereotype.Controller;

@Controller
@SuppressWarnings("all")
public class AboutController extends BaseAboutController {
  public void initialize() {
    this.fullAnchor(this.root, 100, 100, 150, 150);
  }
  
  @Override
  public void onEmailButton(final ActionEvent event) {
  }
  
  @Override
  public void onFacebookButton(final ActionEvent event) {
  }
  
  @Override
  public void onGPlusButton(final ActionEvent event) {
  }
  
  @Override
  public void onGithubButton(final ActionEvent event) {
  }
  
  @Override
  public void onLinkedInButton(final ActionEvent event) {
  }
  
  @Override
  public void onOkButton(final ActionEvent event) {
    this.root.getParent().toBack();
  }
  
  @Override
  public void onTwitterButton(final ActionEvent event) {
  }
}
