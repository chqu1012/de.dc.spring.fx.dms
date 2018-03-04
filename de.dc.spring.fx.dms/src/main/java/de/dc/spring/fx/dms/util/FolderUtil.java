package de.dc.spring.fx.dms.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import de.dc.spring.fx.dms.controller.DMSDetailController;
import de.dc.spring.fx.dms.model.Ticket;

@Component
public class FolderUtil {

	public static final File rootFolder = new File("");
	public static final File contentFolder = new File(rootFolder.getAbsolutePath()+"/content");
	
	public void createIfNotExist() {
		existFolder(contentFolder);
	}
	
	public File[] getFolderContentByTicket(Ticket ticket) {
		return getFolderByTicket(ticket).listFiles();
	}
	
	public File getFolderByTicket(Ticket ticket) {
		return new File(contentFolder.getAbsoluteFile()+"/"+getNameByTicket(ticket));
	}
	
	public String getNameByTicket(Ticket ticket) {
		return "DMS-"+DMSDetailController.format.format(ticket.getId());
	}
	
	public File createFolder(Ticket ticket) {
		File folder = new File(contentFolder.getAbsoluteFile()+"/"+getNameByTicket(ticket));
		folder.mkdirs();
		return folder;
	}
	
	private void existFolder(File folder) {
		if (!folder.exists()) {
			folder.mkdirs();
		}
	}
	
	public void copyFileTo(File sourceFile, File destinationFile) {
		try {
			FileUtils.copyFile(sourceFile, destinationFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
