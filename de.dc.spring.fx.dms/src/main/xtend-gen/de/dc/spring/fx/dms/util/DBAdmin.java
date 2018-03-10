package de.dc.spring.fx.dms.util;

import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.h2.tools.Server;

@SuppressWarnings("all")
public class DBAdmin {
  public static void main(final String[] args) {
    DBAdmin.initialize();
  }
  
  public static String initialize() {
    try {
      String _xblockexpression = null;
      {
        Server server = Server.createWebServer(new String[] { "-webPort", "10800" });
        server = server.start();
        String url = server.getURL();
        _xblockexpression = InputOutput.<String>println(url);
      }
      return _xblockexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
