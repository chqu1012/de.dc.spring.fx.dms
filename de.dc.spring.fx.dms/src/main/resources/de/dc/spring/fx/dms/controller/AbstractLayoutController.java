package de.dc.spring.fx.dms.controller;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public abstract class AbstractLayoutController {

	protected void fullAnchor(Node pane) {
		fullAnchor(pane,0d,0d,0d,0d);
	}

	protected void fullAnchor(Node pane, double top, double bottom, double right, double left) {
		AnchorPane.setBottomAnchor(pane, bottom);
		AnchorPane.setTopAnchor(pane, top);
		AnchorPane.setLeftAnchor(pane, left);
		AnchorPane.setRightAnchor(pane, right);
	}
}
