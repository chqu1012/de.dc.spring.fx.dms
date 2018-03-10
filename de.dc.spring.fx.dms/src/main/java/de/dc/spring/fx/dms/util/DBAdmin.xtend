package de.dc.spring.fx.dms.util

import org.h2.tools.Server

class DBAdmin {
	def static void main(String[] args) {
		initialize
	}

	def static initialize() {
		var server = Server.createWebServer(#["-webPort", "10800"])
		server = server.start
		var url = server.URL
		println(url)
	}
}
