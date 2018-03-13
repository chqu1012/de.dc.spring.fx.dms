package de.dc.spring.fx.dms.controller

import javafx.event.ActionEvent
import org.springframework.stereotype.Controller

@Controller
class AboutController extends BaseAboutController{
	
	def initialize(){
		root.fullAnchor(100,100,150,150)
	}
	
	override onEmailButton(ActionEvent event) {
	}
	
	override onFacebookButton(ActionEvent event) {
	}
	
	override onGPlusButton(ActionEvent event) {
	}
	
	override onGithubButton(ActionEvent event) {
	}
	
	override onLinkedInButton(ActionEvent event) {
	}
	
	override onOkButton(ActionEvent event) {
		root.parent.toBack
	}
	
	override onTwitterButton(ActionEvent event) {
	}
	
}