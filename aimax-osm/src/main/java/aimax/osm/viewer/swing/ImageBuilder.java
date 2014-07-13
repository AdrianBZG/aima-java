package aimax.osm.viewer.swing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

import aimax.osm.viewer.UnifiedColor;
import aimax.osm.viewer.UnifiedImageBuilder;

public class ImageBuilder implements UnifiedImageBuilder {

	private Image result;
	private Graphics2D g2;
	boolean areaFillMode;

	public void initImage(Image image) {
		result = image;
		g2 = (Graphics2D) image.getGraphics();
	}

	/** Returns the width of the image under construction. */
	@Override
	public int getWidth() {
		return result.getWidth(null);
	}

	/** Returns the height of the image under construction. */
	@Override
	public int getHeight() {
		return result.getHeight(null);
	}

	@Override
	public void drawLine(int x1, int y1, int x2, int y2) {
		g2.drawLine(x1, y1, x2, y2);
	}

	@Override
	public void drawRect(int x, int y, int width, int height) {
		if (areaFillMode)
			g2.fillRect(x, y, width, height);
		else
			g2.drawRect(x, y, width, height);

	}

	@Override
	public void drawOval(int x, int y, int width, int height) {
		if (areaFillMode)
			g2.fillOval(x, y, width, height);
		else
			g2.drawOval(x, y, width, height);

	}

	@Override
	public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints) {
		g2.drawPolyline(xPoints, yPoints, nPoints);

	}

	@Override
	public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {
		if (areaFillMode)
			g2.fillPolygon(xPoints, yPoints, nPoints);
		else
			g2.drawPolygon(xPoints, yPoints, nPoints);

	}

	@Override
	public void drawString(String text, int x, int y) {
		g2.drawString(text, x, y);

	}

	@Override
	public Image getResult() {
		return result;
	}

	@Override
	public void setColor(UnifiedColor color) {
		g2.setColor(new Color(color.getRed(), color.getGreen(),
				color.getBlue(), color.getAlpha()));

	}

	@Override
	public void setLineStyle(boolean dashed, float width) {
		float dash[] = null;
		if (dashed) {
			dash = new float[] { width * 2f };
		}
		g2.setStroke(new BasicStroke(width, BasicStroke.CAP_BUTT,
				BasicStroke.JOIN_ROUND, 10.0f, dash, 0.0f));
	}

	@Override
	public void setAreaFilled(boolean value) {
		areaFillMode = value;

	}

	@Override
	public void setFontSize(float size) {
		g2.setFont(g2.getFont().deriveFont(size));
	}

	@Override
	public float getFontSize() {
		return g2.getFont().getSize();
	}

}