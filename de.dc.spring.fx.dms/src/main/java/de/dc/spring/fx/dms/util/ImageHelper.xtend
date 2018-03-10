package de.dc.spring.fx.dms.util

import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.GraphicsConfiguration
import java.awt.GraphicsDevice
import java.awt.GraphicsEnvironment
import java.awt.Toolkit
import java.awt.datatransfer.DataFlavor
import java.awt.datatransfer.Transferable
import java.awt.image.BufferedImage
import java.awt.image.RenderedImage
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.sql.Blob
import java.sql.SQLException
import javax.imageio.ImageIO
import javax.sql.rowset.serial.SerialBlob
import javax.swing.Icon
import javax.swing.ImageIcon
import org.apache.log4j.Level
import org.apache.log4j.Logger
import javafx.embed.swing.SwingFXUtils
import javafx.scene.image.Image

class ImageHelper {
	
	static Logger log = Logger.getLogger(ImageHelper)

	def static saveToFile(String filePatn, Image image) {
		var outputFile = new File(filePatn)
		var bImage = SwingFXUtils.fromFXImage(image, null)
		ImageIO.write(bImage, "png", outputFile)
	}

	def static getImageFX() {
		var awtImage = getImageFromClipboard()
		try {
			return awtImageToFX(awtImage)
		} catch (Exception e) {
			log.log(Level.ERROR, e.getMessage())
		}
	}

	def static getImageFromClipboard() {
		var transferable = Toolkit.defaultToolkit.systemClipboard.getContents(null)
		if (transferable !== null && transferable.isDataFlavorSupported(DataFlavor.imageFlavor)) {
			try {
				return (transferable.getTransferData(DataFlavor.imageFlavor) as java.awt.Image)
			} catch (Exception e) {
				log.log(Level.ERROR, e.getMessage())
			}
		}
	}

	def static awtImageToFX(java.awt.Image image_finalParam_){
		var image = image_finalParam_
		if (!(image instanceof RenderedImage)) {
			var bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null),BufferedImage.TYPE_INT_ARGB)
			var g = bufferedImage.createGraphics
			g.drawImage(image, 0, 0, null)
			g.dispose
			image = bufferedImage
		}
		var out = new ByteArrayOutputStream
		ImageIO.write((image as RenderedImage), "png", out)
		out.flush
		var in = new ByteArrayInputStream(out.toByteArray)
		return new javafx.scene.image.Image(in)
	}

	def static Image blobToImage(Blob blob) {
		try {
			var InputStream binaryStream = blob.getBinaryStream()
			return new Image(binaryStream)
		} catch (SQLException e) {
			log.log(Level.ERROR, e.getMessage())
		}

		return null
	}

	def static Image iconToImageFX(Icon icon) {
		var java.awt.Image awtImage = iconToImage(icon)
		try {
			return awtImageToFX(awtImage)
		} catch (Exception e) {
			log.log(Level.ERROR, e.getMessage())
		}

		return null
	}

	def static java.awt.Image iconToImage(Icon icon) {
		if (icon instanceof ImageIcon) {
			return ((icon as ImageIcon)).getImage()
		} else {
			var int w = icon.getIconWidth()
			var int h = icon.getIconHeight()
			var GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment()
			var GraphicsDevice gd = ge.getDefaultScreenDevice()
			var GraphicsConfiguration gc = gd.getDefaultConfiguration()
			var BufferedImage image = gc.createCompatibleImage(w, h)
			var Graphics2D g = image.createGraphics()
			icon.paintIcon(null, g, 0, 0)
			g.dispose()
			return image
		}
	}

	def static byte[] getByteFromImage(Image image) {
		var BufferedImage bImage = SwingFXUtils.fromFXImage(image, null)
		var ByteArrayOutputStream s = new ByteArrayOutputStream()
		try {
			ImageIO.write(bImage, "png", s)
			var byte[] res = s.toByteArray()
			s.close()
			// especially if you are using a different output stream.		
			return res
		} catch (IOException e) {
			log.log(Level.ERROR, e.getMessage())
		}

		return null
	}

	def static SerialBlob toBlob(Image image) {
		var byte[] byteArray = getByteFromImage(image)
		try {
			return new javax.sql.rowset.serial.SerialBlob(byteArray)
		} catch (SQLException e) {
			log.log(Level.ERROR, e.getMessage())
		}

		return null
	}

	def static Image getImage(File file) {
		return new Image(file.toURI().toString())
	}
}
