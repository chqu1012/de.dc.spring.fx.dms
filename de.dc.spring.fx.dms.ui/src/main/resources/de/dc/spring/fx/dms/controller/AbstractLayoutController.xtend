package de.dc.spring.fx.dms.controller

import javafx.scene.Node

import static extension javafx.scene.layout.AnchorPane.setBottomAnchor
import static extension javafx.scene.layout.AnchorPane.setTopAnchor
import static extension javafx.scene.layout.AnchorPane.setLeftAnchor
import static extension javafx.scene.layout.AnchorPane.setRightAnchor

abstract class AbstractLayoutController{
	
	def protected fullAnchor(Node pane) {
		pane.fullAnchor(40d, 40d, 40d, 40d)
	}

	def protected fullAnchorWithoutMargin(Node pane) {
		pane.fullAnchor(0d, 0d, 0d, 0d)
	}

	def protected fullAnchor(Node pane, double top, double bottom, double right, double left) {
		pane.bottomAnchor = bottom
		pane.topAnchor = top
		pane.leftAnchor = left
		pane.rightAnchor = right
	}
}
