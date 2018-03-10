package de.dc.spring.fx.dms.controller;

import de.dc.spring.fx.dms.controller.BaseHomeTileController;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Scope(value = "prototype")
@Controller
@SuppressWarnings("all")
public class HomeTileController extends BaseHomeTileController {
  public void setImageBackground(final String hexColor) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("-fx-background-color: ");
    _builder.append(hexColor);
    _builder.append(";-fx-border-radius: 3 3 3 3;-fx-background-radius: 3 3 3 3;");
    String style = _builder.toString();
    this.homeTileImagePanel.setStyle(style);
  }
}
