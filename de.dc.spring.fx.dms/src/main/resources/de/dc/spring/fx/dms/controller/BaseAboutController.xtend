package de.dc.spring.fx.dms.controller

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.layout.AnchorPane

abstract class BaseAboutController extends AbstractLayoutController{
	
	@FXML protected AnchorPane root

	@FXML def protected abstract void onEmailButton(ActionEvent event)
	@FXML def protected abstract void onFacebookButton(ActionEvent event)
	@FXML def protected abstract void onGPlusButton(ActionEvent event)
	@FXML def protected abstract void onGithubButton(ActionEvent event)
	@FXML def protected abstract void onLinkedInButton(ActionEvent event)
	@FXML def protected abstract void onOkButton(ActionEvent event)
	@FXML def protected abstract void onTwitterButton(ActionEvent event)

}
