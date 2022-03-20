import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class DrawPanel extends JPanel {
	private ShapeBasic[] shapes;
	private int shapeCount;

	private ShapeBasic currentShape;
	private Color currentColor;
	private boolean filledShape;
	private String shapeType;
	private JLabel statusLabel;
	private int strokeWidth;

	
	public DrawPanel(JLabel statusLabel) {
		shapes = new ShapeBasic[100];
		shapeCount = 0;

		setDrawingColor(Color.BLACK);
		setFilledShape(false);

		setBackground(Color.WHITE);

		MouseHandler mouseHandler = new MouseHandler();
		addMouseListener(mouseHandler);
		addMouseMotionListener(mouseHandler);

		this.statusLabel = statusLabel;

	}
    //畫圖還有把將圖形存到陣列裡!!
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (int i = 0; i < shapeCount; i++)
			shapes[i].draw(g);

		if (currentShape != null)
			currentShape.draw(g);
	}

	public void setDrawingColor(Color c) {
		currentColor = c;
	}
	

	//上一步
	public void clearLastShape() {
		if (shapeCount > 0) {
			shapeCount--;
			repaint();
		}
	}
	
	public void clearDrawing() {
		shapeCount = 0;
		repaint();
	}
	
	public void setFilledShape(boolean isFilled) {
		filledShape = isFilled;
	}

	public void setShapeType(String shapeType) {
		this.shapeType = shapeType;
	}
	
	public void setStrokeWidth(int strokeWidth) {
		this.strokeWidth = strokeWidth;
	}
	
	private class MouseHandler extends MouseAdapter implements MouseMotionListener {
		
		public void mousePressed(MouseEvent e) {
			if (currentShape != null)
				return;
			
			currentShape = new ShapeBasic(e.getX(), e.getY(), e.getX(), e.getY(), currentColor, filledShape, shapeType);//建立圖形
			currentShape.setStrokeWidth(strokeWidth);

			repaint();
		}
		
		public void mouseReleased(MouseEvent e) {
			if (currentShape == null)
				return;

			currentShape.setX2(e.getX());
			currentShape.setY2(e.getY());

			if (shapeCount < shapes.length) {
				shapes[shapeCount] = currentShape;
				shapeCount++;
			}
			currentShape = null;
			repaint();
		}
		
		public void mouseDragged(MouseEvent e) {
			if (currentShape != null) {
				currentShape.setX2(e.getX());
				currentShape.setY2(e.getY());
				
				currentShape.addPoint(e.getPoint());
				repaint();
			}

			mouseMoved(e);
		}

		public void mouseMoved(MouseEvent e) {
			statusLabel.setText(String.format("指標位置: " + "(%d,%d)", e.getX(), e.getY()));
		}

	}
}
