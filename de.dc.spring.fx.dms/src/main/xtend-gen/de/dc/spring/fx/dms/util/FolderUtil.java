package de.dc.spring.fx.dms.util;

import de.dc.spring.fx.dms.controller.DMSDetailController;
import de.dc.spring.fx.dms.model.Ticket;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("all")
public class FolderUtil {
  public final static File rootFolder = new File("");
  
  public final static File contentFolder = new File((FolderUtil.rootFolder.getAbsoluteFile() + "/content"));
  
  public boolean createIfNotExist() {
    return this.existFolder(FolderUtil.contentFolder);
  }
  
  public File[] getFolderContentByTicket(final Ticket ticket) {
    return this.getFolderByTicket(ticket).listFiles();
  }
  
  public File getFolderByTicket(final Ticket ticket) {
    File _xblockexpression = null;
    {
      StringConcatenation _builder = new StringConcatenation();
      File _absoluteFile = FolderUtil.contentFolder.getAbsoluteFile();
      _builder.append(_absoluteFile);
      _builder.append("/");
      CharSequence _nameByTicket = this.getNameByTicket(ticket);
      _builder.append(_nameByTicket);
      final File folder = new File(_builder.toString());
      boolean _exists = folder.exists();
      boolean _not = (!_exists);
      if (_not) {
        folder.mkdirs();
      }
      _xblockexpression = folder;
    }
    return _xblockexpression;
  }
  
  public CharSequence getNameByTicket(final Ticket ticket) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("DMS-");
    String _format = DMSDetailController.format.format(ticket.getId());
    _builder.append(_format);
    return _builder;
  }
  
  public File createFolder(final Ticket ticket) {
    File _xblockexpression = null;
    {
      StringConcatenation _builder = new StringConcatenation();
      File _absoluteFile = FolderUtil.contentFolder.getAbsoluteFile();
      _builder.append(_absoluteFile);
      _builder.append("/");
      CharSequence _nameByTicket = this.getNameByTicket(ticket);
      _builder.append(_nameByTicket);
      File folder = new File(_builder.toString());
      folder.mkdirs();
      _xblockexpression = folder;
    }
    return _xblockexpression;
  }
  
  public boolean existFolder(final File folder) {
    boolean _xifexpression = false;
    boolean _exists = folder.exists();
    boolean _not = (!_exists);
    if (_not) {
      _xifexpression = folder.mkdirs();
    }
    return _xifexpression;
  }
  
  public void copyFileTo(final File sourceFile, final File destinationFile) {
    try {
      FileUtils.copyFile(sourceFile, destinationFile);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public void deleteFolderWithContent(final Ticket ticket) {
    try {
      FileUtils.deleteDirectory(this.getFolderByTicket(ticket));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
