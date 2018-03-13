package de.dc.spring.fx.dms.controller;

import de.dc.spring.fx.dms.controller.AbstractLayoutController;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

@SuppressWarnings("all")
public abstract class BaseCalendarPaneController extends AbstractLayoutController {
  @FXML
  protected AnchorPane calendarPane;
}
