package de.dc.spring.fx.dms.controller

import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.image.ImageView
import javafx.scene.layout.AnchorPane

abstract class BaseHomeTileController {
	@FXML protected Label titleLabel
	@FXML protected Label counterLabel
	@FXML protected Label desciptionLabel
	@FXML protected ImageView imageView
	@FXML protected AnchorPane homeTileImagePanel
}
