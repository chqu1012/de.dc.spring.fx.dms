package de.dc.spring.fx.dms.util;

import java.sql.SQLException;

import org.h2.tools.Server;

public class DBAdmin{

	public static void main(String[] args) {
		initialize();
	}
	
	public static void initialize() {
		Server server;
		try {
			server = Server.createWebServer(new String[] { "-webPort", "10800" });
			server = server.start();
			String url = server.getURL();
			System.out.println(url);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
