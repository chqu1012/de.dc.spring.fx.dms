package de.dc.spring.fx.dms.controller;

import java.nio.file.Paths;
import java.text.DecimalFormat;

import org.eclipse.fx.ui.controls.filesystem.DirectoryTreeView;
import org.eclipse.fx.ui.controls.filesystem.DirectoryView;
import org.eclipse.fx.ui.controls.filesystem.IconSize;
import org.eclipse.fx.ui.controls.filesystem.ResourceItem;
import org.eclipse.fx.ui.controls.filesystem.ResourcePreview;
import org.eclipse.fx.ui.controls.filesystem.RootDirItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import de.dc.spring.fx.dms.model.Ticket;
import de.dc.spring.fx.dms.util.FolderUtil;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;

@Controller
public class DMSDetailController extends BaseDMSDetailController {

	@Autowired FolderUtil folderUtil;
	
	public static final DecimalFormat format = new DecimalFormat("00000");
	private DirectoryTreeView directoryTreeView;

	public void initialize() {
		directoryTreeView = new DirectoryTreeView();
		directoryTreeView.setIconSize(IconSize.SMALL);
		DirectoryView v = new DirectoryView();
		v.setIconSize(IconSize.SMALL);

		directoryTreeView.getSelectedItems().addListener((Observable o) -> {
			if (!directoryTreeView.getSelectedItems().isEmpty()) {
				v.setDir(directoryTreeView.getSelectedItems().get(0));
			} else {
				v.setDir(null);
			}
		});

		ResourcePreview prev = new ResourcePreview();
		v.getSelectedItems().addListener((Observable o) -> {
			if (v.getSelectedItems().size() == 1) {
				prev.setItem(v.getSelectedItems().get(0));
			} else {
				prev.setItem(null);
			}
		});
		
		SplitPane p = new SplitPane(directoryTreeView,v, prev);
	    p.setDividerPositions(0.2,0.5);
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

		updateFileView(folderUtil.getFolderByTicket(ticket).getAbsolutePath());
	}

	private void updateFileView(String path) {
		RootDirItem rootDirItem = ResourceItem.createObservedPath(Paths.get(path));
		directoryTreeView.setRootDirectories(
			      FXCollections.observableArrayList(rootDirItem));
	}

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
