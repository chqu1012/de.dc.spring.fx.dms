package de.dc.spring.fx.dms.util

import de.dc.spring.fx.dms.controller.DMSDetailController
import de.dc.spring.fx.dms.model.Ticket
import java.io.File
import org.apache.commons.io.FileUtils
import org.springframework.stereotype.Component

@Component class FolderUtil {
	public static final File rootFolder = new File("")
	public static final File contentFolder = new File(rootFolder.absoluteFile + "/content")

	def createIfNotExist() {
		existFolder(contentFolder)
	}

	def getFolderContentByTicket(Ticket ticket) {
		getFolderByTicket(ticket).listFiles
	}

	def getFolderByTicket(Ticket ticket) {
		val folder = new File('''«contentFolder.absoluteFile»/«getNameByTicket(ticket)»''')
		if(!folder.exists){
			folder.mkdirs
		}
		folder
	}

	def getNameByTicket(Ticket ticket) {
		'''DMS-«DMSDetailController.format.format(ticket.id)»'''
	}

	def createFolder(Ticket ticket) {
		var folder = new File('''«contentFolder.absoluteFile»/«getNameByTicket(ticket)»''')
		folder.mkdirs
		folder
	}

	def existFolder(File folder) {
		if (!folder.exists) {
			folder.mkdirs
		}
	}

	def copyFileTo(File sourceFile, File destinationFile) {
		FileUtils.copyFile(sourceFile, destinationFile)
	}
	
	def deleteFolderWithContent(Ticket ticket) {
		FileUtils.deleteDirectory(ticket.getFolderByTicket)
	}
	
}
