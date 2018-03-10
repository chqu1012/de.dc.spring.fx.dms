package de.dc.spring.fx.dms.controller;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

@SuppressWarnings("all")
public abstract class AbstractLayoutController {
  protected void fullAnchor(final Node pane) {
    this.fullAnchor(pane, 40d, 40d, 40d, 40d);
  }
  
  protected void fullAnchorWithoutMargin(final Node pane) {
    this.fullAnchor(pane, 0d, 0d, 0d, 0d);
  }
  
  protected void fullAnchor(final Node pane, final double top, final double bottom, final double right, final double left) {
    AnchorPane.setBottomAnchor(pane, Double.valueOf(bottom));
    AnchorPane.setTopAnchor(pane, Double.valueOf(top));
    AnchorPane.setLeftAnchor(pane, Double.valueOf(left));
    AnchorPane.setRightAnchor(pane, Double.valueOf(right));
  }
}
