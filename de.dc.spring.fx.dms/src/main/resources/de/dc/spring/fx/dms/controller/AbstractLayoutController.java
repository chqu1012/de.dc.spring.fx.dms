package de.dc.spring.fx.dms.controller;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public abstract class AbstractLayoutController {

	protected void fullAnchor(Node pane) {
		AnchorPane.setBottomAnchor(pane, 0d);
		AnchorPane.setTopAnchor(pane, 0d);
		AnchorPane.setLeftAnchor(pane, 0d);
		AnchorPane.setRightAnchor(pane, 0d);
	}
}
