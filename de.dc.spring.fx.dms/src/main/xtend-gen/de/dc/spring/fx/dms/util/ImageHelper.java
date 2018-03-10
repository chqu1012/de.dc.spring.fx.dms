package de.dc.spring.fx.dms.util;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.eclipse.xtext.xbase.lib.Exceptions;

@SuppressWarnings("all")
public class ImageHelper {
  private static Logger log = Logger.getLogger(ImageHelper.class);
  
  public static boolean saveToFile(final String filePatn, final Image image) {
    try {
      boolean _xblockexpression = false;
      {
        File outputFile = new File(filePatn);
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        _xblockexpression = ImageIO.write(bImage, "png", outputFile);
      }
      return _xblockexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public static Image getImageFX() {
    java.awt.Image awtImage = ImageHelper.getImageFromClipboard();
    try {
      return ImageHelper.awtImageToFX(awtImage);
    } catch (final Throwable _t) {
      if (_t instanceof Exception) {
        final Exception e = (Exception)_t;
        ImageHelper.log.log(Level.ERROR, e.getMessage());
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
    return null;
  }
  
  public static java.awt.Image getImageFromClipboard() {
    Transferable transferable = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
    if (((transferable != null) && transferable.isDataFlavorSupported(DataFlavor.imageFlavor))) {
      try {
        Object _transferData = transferable.getTransferData(DataFlavor.imageFlavor);
        return ((java.awt.Image) _transferData);
      } catch (final Throwable _t) {
        if (_t instanceof Exception) {
          final Exception e = (Exception)_t;
          ImageHelper.log.log(Level.ERROR, e.getMessage());
        } else {
          throw Exceptions.sneakyThrow(_t);
        }
      }
    }
    return null;
  }
  
  public static Image awtImageToFX(final java.awt.Image image_finalParam_) {
    try {
      java.awt.Image image = image_finalParam_;
      if ((!(image instanceof RenderedImage))) {
        int _width = image.getWidth(null);
        int _height = image.getHeight(null);
        BufferedImage bufferedImage = new BufferedImage(_width, _height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = bufferedImage.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        image = bufferedImage;
      }
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      ImageIO.write(((RenderedImage) image), "png", out);
      out.flush();
      byte[] _byteArray = out.toByteArray();
      ByteArrayInputStream in = new ByteArrayInputStream(_byteArray);
      return new Image(in);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public static Image blobToImage(final Blob blob) {
    try {
      InputStream binaryStream = blob.getBinaryStream();
      return new Image(binaryStream);
    } catch (final Throwable _t) {
      if (_t instanceof SQLException) {
        final SQLException e = (SQLException)_t;
        ImageHelper.log.log(Level.ERROR, e.getMessage());
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
    return null;
  }
  
  public static Image iconToImageFX(final Icon icon) {
    java.awt.Image awtImage = ImageHelper.iconToImage(icon);
    try {
      return ImageHelper.awtImageToFX(awtImage);
    } catch (final Throwable _t) {
      if (_t instanceof Exception) {
        final Exception e = (Exception)_t;
        ImageHelper.log.log(Level.ERROR, e.getMessage());
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
    return null;
  }
  
  public static java.awt.Image iconToImage(final Icon icon) {
    if ((icon instanceof ImageIcon)) {
      return ((ImageIcon) icon).getImage();
    } else {
      int w = icon.getIconWidth();
      int h = icon.getIconHeight();
      GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
      GraphicsDevice gd = ge.getDefaultScreenDevice();
      GraphicsConfiguration gc = gd.getDefaultConfiguration();
      BufferedImage image = gc.createCompatibleImage(w, h);
      Graphics2D g = image.createGraphics();
      icon.paintIcon(null, g, 0, 0);
      g.dispose();
      return image;
    }
  }
  
  public static byte[] getByteFromImage(final Image image) {
    BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
    ByteArrayOutputStream s = new ByteArrayOutputStream();
    try {
      ImageIO.write(bImage, "png", s);
      byte[] res = s.toByteArray();
      s.close();
      return res;
    } catch (final Throwable _t) {
      if (_t instanceof IOException) {
        final IOException e = (IOException)_t;
        ImageHelper.log.log(Level.ERROR, e.getMessage());
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
    return null;
  }
  
  public static SerialBlob toBlob(final Image image) {
    byte[] byteArray = ImageHelper.getByteFromImage(image);
    try {
      return new SerialBlob(byteArray);
    } catch (final Throwable _t) {
      if (_t instanceof SQLException) {
        final SQLException e = (SQLException)_t;
        ImageHelper.log.log(Level.ERROR, e.getMessage());
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
    return null;
  }
  
  public static Image getImage(final File file) {
    String _string = file.toURI().toString();
    return new Image(_string);
  }
}
