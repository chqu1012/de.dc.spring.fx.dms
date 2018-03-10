package de.dc.spring.fx.dms.control.cell;

import java.io.File;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeView;
import javafx.util.Callback;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class FileTreeCellFactory implements Callback<TreeView<File>, TreeCell<File>> {
  @Override
  public TreeCell<File> call(final TreeView<File> param) {
    return new TreeCell<File>() {
      @Override
      public void updateItem(final File item, final boolean isEmpty) {
        super.updateItem(item, isEmpty);
        String text = "";
        boolean _isEmpty = this.isEmpty();
        if (_isEmpty) {
          this.setGraphic(null);
        }
        if ((!isEmpty)) {
          text = item.getName();
          boolean _isDirectory = item.isDirectory();
          if (_isDirectory) {
            StringConcatenation _builder = new StringConcatenation();
            _builder.append(text);
            _builder.append(" (");
            int _length = item.listFiles().length;
            _builder.append(_length);
            _builder.append(")");
            text = _builder.toString();
          }
        }
        this.setText(text);
      }
    };
  }
}
