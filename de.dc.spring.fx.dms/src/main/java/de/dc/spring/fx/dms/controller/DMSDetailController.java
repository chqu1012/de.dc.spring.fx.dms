package de.dc.spring.fx.dms.controller;

import java.io.IOException;
import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import de.dc.spring.fx.dms.control.controller.FileViewController;
import de.dc.spring.fx.dms.model.Ticket;
import de.dc.spring.fx.dms.util.FolderUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;

@Controller
public class DMSDetailController extends BaseDMSDetailController {

	@Autowired FolderUtil folderUtil;
	
	public static final DecimalFormat format = new DecimalFormat("00000");

	private FileViewController fileViewController;
//	private DirectoryTreeView directoryTreeView;

	public void initialize() throws IOException {
//		directoryTreeView = new DirectoryTreeView();
//		directoryTreeView.setIconSize(IconSize.SMALL);
//		
//		DirectoryView v = new DirectoryView();
//		v.setIconSize(IconSize.SMALL);
//
//		directoryTreeView.getSelectedItems().addListener((Observable o) -> {
//			if (!directoryTreeView.getSelectedItems().isEmpty()) {
//				v.setDir(directoryTreeView.getSelectedItems().get(0));
//			} else {
//				v.setDir(null);
//			}
//		});
//
//		ResourcePreview prev = new ResourcePreview();
//		v.getSelectedItems().addListener((Observable o) -> {
//			if (v.getSelectedItems().size() == 1) {
//				prev.setItem(v.getSelectedItems().get(0));
//			} else {
//				prev.setItem(null);
//			}
//		});
//		
//		SplitPane p = new SplitPane(directoryTreeView,v, prev);
//	    p.setDividerPositions(0.2,0.5);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/de/dc/spring/fx/dms/control/FileView.fxml"));
		SplitPane p = loader.load();
		fileViewController = loader.getController();
	    AnchorPane.setBottomAnchor(p, 0.0d);
	    AnchorPane.setTopAnchor(p, 0.0d);
	    AnchorPane.setLeftAnchor(p, 0.0d);
	    AnchorPane.setRightAnchor(p, 0.0d);
	    splitPaneRoot.getChildren().add(p);
	}
	
	public void setDetails(Ticket ticket) {
		documentIdLabel.setText("DMS-" + format.format(ticket.getId()));
		descriptionLabel.setText(ticket.getName());
		descriptionText.setText(ticket.getDescription() == null ? "" : ticket.getDescription());
		createdOnLabel.setText(ticket.getCreatedOn().toString());
		lastUpdate÷abel.setText(ticket.getCreatedOn().toString());

		fileViewController.setInput(folderUtil.getFolderByTicket(ticket).getAbsolutePath());
	}

//	private void updateFileView(String path) {
//		RootDirItem rootDirItem = ResourceItem.createObservedPath(Paths.get(path));
//		directoryTreeView.setRootDirectories(
//			      FXCollections.observableArrayList(rootDirItem));
//	}

	@Override
	protected void onClipboardButton(ActionEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onCloneButton(ActionEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onCommentButton(ActionEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onExplorerButton(ActionEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onExportButton(ActionEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onFilesButton(ActionEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onLinkButton(ActionEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onSaveButton(ActionEvent event) {
		// TODO Auto-generated method stub

	}

}
