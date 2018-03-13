package de.dc.spring.fx.dms.controller

import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Controller

@Scope(value="prototype") 
@Controller 
class HomeTileController extends BaseHomeTileController {

	def setImageBackground(String hexColor) {
		var style = '''-fx-background-color: «hexColor»;-fx-border-radius: 3 3 3 3;-fx-background-radius: 3 3 3 3;'''
		homeTileImagePanel.style = style
	}
}
