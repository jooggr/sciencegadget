package com.sciencegadgets.client.equationtree;

import java.util.HashMap;
import java.util.List;

import org.vaadin.gwtgraphics.client.DrawingArea;
import org.vaadin.gwtgraphics.client.Group;
import org.vaadin.gwtgraphics.client.Line;
import org.vaadin.gwtgraphics.client.VectorObject;
import org.vaadin.gwtgraphics.client.shape.Ellipse;
import org.vaadin.gwtgraphics.client.shape.Path;
import org.vaadin.gwtgraphics.client.shape.Rectangle;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.HTML;
import com.sciencegadgets.client.Log;
import com.sciencegadgets.client.algebramanipulation.MLElementWrapper;
import com.sciencegadgets.client.equationtree.JohnTree.JohnNode;
import com.sciencegadgets.client.equationtree.JohnTree.Type;

public class TreeCanvas extends DrawingArea {

	private JohnTree johnTree;
	private int childSpace;
	private int rowHeight;
	private AbsolutePanel panel;
	private int sideLengthLeft;
	private int sideLengthRight;
	private HashMap<JohnTree.Type, String> palette;
	private int pad = 5;
	private int topPad = 10;
	// private String nodePicUrl =
	// "http://www.lanxuan.org/download/openclipart/calloutscloud.svg.SVG";

	// The number of members in each row
	private byte[] leftLayerCounts;
	private byte[] rightLayerCounts;

	// These counters aid in placing each member down
	private byte[] leftCounters;
	private byte[] rightCounters;

	/**
	 * Constructor of the canvas that automatically adds it to the given panel
	 * 
	 * @param panel
	 *            - panel to paint on
	 * @param jTree
	 *            - tree to paint
	 */
	public TreeCanvas(AbsolutePanel panel, JohnTree jTree) {
		this(panel.getOffsetWidth(), panel.getOffsetHeight(), jTree);
		this.panel = panel;
		this.johnTree = jTree;
		this.setStyleName("sky");

		// Image backgroundImg = new Image(0, 0, panel.getOffsetWidth(),
		// panel.getOffsetHeight(),
		// "http://ecoartfilm.files.wordpress.com/2012/05/tree.jpg");
		// backgroundImg.setRotation(180);
		// panel.add(backgroundImg);

		draw(jTree);
	}

	private TreeCanvas(int width, int height, JohnTree jTree) {
		super(width, height);
	}

	public void reDraw() {
		this.clear();
		panel.clear();
		draw(johnTree);
	}

	private void createPalette() {
		palette = new HashMap<JohnTree.Type, String>();
		palette.put(JohnTree.Type.Term, "#7FFFD4");
		palette.put(JohnTree.Type.Series, "#87CEFA");
		palette.put(JohnTree.Type.Function, "#FFD700");
		palette.put(JohnTree.Type.Exponent, "#E6E6FA");
		palette.put(JohnTree.Type.Fraction, "#FAEBD7");
		palette.put(JohnTree.Type.Variable, "#F0F8FF");
		palette.put(JohnTree.Type.Number, "#F0FFF0");
	}

	public void draw(JohnTree jTree) {

		createPalette();

		leftLayerCounts = new byte[20];
		rightLayerCounts = new byte[20];
		leftCounters = new byte[20];
		rightCounters = new byte[20];

		// The counting in each layer will allow for maximum spacing, index 0 is
		// each side
		leftLayerCounts[0]++;
		rightLayerCounts[0]++;
		getNextLayerCounts(jTree.getLeftSide(), (byte) 1, true);
		getNextLayerCounts(jTree.getRightSide(), (byte) 1, false);

		// Resize side lengths depending on the ratio of side member density.
		// This keeps the tree from looking lopsided when there are many more
		// variables on one side
		int leftMemberCount = 0;
		int rightMemberCount = 0;

		for (int i = 0; i < leftLayerCounts.length; i++) {
			// Count all the members in this side, giving more weight to the
			// higher layers by dividing by i. (*100 to aleviate truncation,
			// only the ratio matters anyway)
			leftMemberCount += (leftLayerCounts[i] * 100 / (i + 1));
		}

		for (int i = 0; i < rightLayerCounts.length; i++) {
			rightMemberCount += rightLayerCounts[i] * 100 / (i + 1);
		}

		sideLengthLeft = (this.getWidth() * leftMemberCount)
				/ (rightMemberCount + leftMemberCount);
		sideLengthRight = this.getWidth() - sideLengthLeft;

		rowHeight = this.getHeight() / 4;

		panel.add(this);

		splitSidesInMiddle();

		// Add HTML widgets of top level of each side
		int[] topLayerHeights = addFirstLayer(jTree);

		// Recursively create the rest of the tree
		if (jTree.getLeftSide().getChildCount() > 1)
			drawChildren(jTree.getLeftSide(), (sideLengthLeft / 2),
					topLayerHeights[0], (byte) 1, true);
		if (jTree.getRightSide().getChildCount() > 1)
			drawChildren(jTree.getRightSide(),
					(sideLengthLeft + sideLengthRight / 2), topLayerHeights[1],
					(byte) 1, false);

	}

	private void drawChildren(JohnNode pNode, int parentX, int parentY,
			byte layer, Boolean isLeft) {

		List<JohnNode> children = pNode.getChildren();
		int layerHeight = rowHeight * (layer);

		for (JohnNode child : children) {

			// Don't show certain nodes meant only for MathML display
			if (child.isHidden()) {
				Log.info("Skip drawing isHidden: " + child);
				continue;
			}

			// Find the maximum width any child can have in the layer
			if (isLeft) {
				childSpace = sideLengthLeft / leftLayerCounts[layer];
			} else {
				childSpace = (sideLengthRight) / rightLayerCounts[layer];
			}
			HTML childHTML = child.toMathML();

			int childWidth = childHTML.getOffsetWidth();
			// int childHeight = childHTML.getOffsetHeight();

			int placement;
			if (isLeft) {
				placement = childSpace * (leftCounters[layer]);
				leftCounters[layer]++;

			} else {
				placement = (childSpace * rightCounters[layer]);
				rightCounters[layer]++;
				// Shift to right side
				placement += sideLengthLeft;
			}
			// TODO comment/uncomment for gravity
			placement = (placement + parentX) / 2; // Add gravity towards
			// parent node
			placement += childSpace / 4;// padding
			if (childWidth >= childSpace) {
				// If the child is too big and going to overflow pull it back
				placement -= childSpace / 4;
			}
			panel.add(childHTML, placement, layerHeight);

			int childLeft = childHTML.getAbsoluteLeft()
					- panel.getAbsoluteLeft();
			int childTop = childHTML.getAbsoluteTop() - panel.getAbsoluteTop();

			int lineX = childLeft + childWidth / 2 + pad;
			int lineY = childTop;

			int boxLeft = childLeft - pad;
			int boxTop = childTop;
			int boxWidth = childHTML.getOffsetWidth() + 2 * pad;
			int boxHeight = childHTML.getOffsetHeight() * 4 / 3;

			VectorObject nodeShape = createNodeShape(child.getType(), boxLeft,
					boxTop, boxWidth, boxHeight);
			this.add(nodeShape);

			Line connectingLine = new Line(parentX, parentY, lineX,
					(nodeShape.getAbsoluteTop() - panel.getAbsoluteTop()));
			this.add(connectingLine);

			// Image nodePic = new Image(boxLeft, boxTop-boxHeight, boxWidth,
			// boxHeight*2, nodePicUrl);
			// this.add(nodePic);

			if (child.getWrapper() != null) {
				MLElementWrapper wrap = child.getWrapper().getJoinedWrapper();
				wrap.setHeight(boxHeight + "px");
				wrap.setWidth(boxWidth + "px");
				panel.add(wrap, childLeft - pad, childTop);
				panel.add(wrap.getDropDescriptor(), childLeft - pad, childTop + boxHeight);
			}

			if (child.getChildCount() > 0) {
				drawChildren(child, lineX, childTop + boxHeight,
						(byte) (layer + 1), isLeft);
			}
		}

	}

	/**
	 * Prepares the canvas spacing by recursively looking through the tree and
	 * counting the number of members in each layer
	 * 
	 * @param pNode
	 * @param layer
	 * @param isLeft
	 */
	private void getNextLayerCounts(JohnNode pNode, byte layer, Boolean isLeft) {
		List<JohnNode> children = pNode.getChildren();

		for (JohnNode child : children) {

			// Don't show certain nodes meant only for MathML display
			if (child.isHidden()) {
				continue;
			}

			if (isLeft) {
				leftLayerCounts[layer]++;
			} else {
				rightLayerCounts[layer]++;
			}
			if (child.getChildCount() > 0) {
				getNextLayerCounts(child, (byte) (layer + 1), isLeft);
			}
		}
	}

	private int[] addFirstLayer(JohnTree jTree) {

		HTML lHTML = jTree.getLeftSide().toMathML();
		HTML rHTML = jTree.getRightSide().toMathML();

		panel.add(lHTML, sideLengthLeft / 2, topPad);
		// panel.add(new HTML("="), sideLengthLeft, topPad);
		panel.add(rHTML, (sideLengthLeft + sideLengthRight / 2), topPad);

		int lHeight = lHTML.getOffsetHeight();
		int lWidth = lHTML.getOffsetWidth();
		int lLeft = sideLengthLeft / 2;
		int rHeight = rHTML.getOffsetHeight();
		int rWidth = rHTML.getOffsetWidth();
		int rLeft = sideLengthLeft + sideLengthRight / 2;

		// Left - Add top level wrappers
		int lboxLeft = lLeft - pad;
		int lboxTop = topPad;
		int lboxWidth = lWidth + 2 * pad;
		int lboxHeight = lHeight * 4 / 3;

		VectorObject lNodeShape = createNodeShape(
				jTree.getLeftSide().getType(), lboxLeft, lboxTop, lboxWidth,
				lboxHeight);
		this.add(lNodeShape);

		if (jTree.getLeftSide().getWrapper() != null) {
			MLElementWrapper lWrap = jTree.getLeftSide().getWrapper()
					.getJoinedWrapper();
			lWrap.setHeight(lboxHeight + "px");
			lWrap.setWidth(lboxWidth + "px");
			panel.add(lWrap, lLeft - pad, topPad);
			panel.add(lWrap.getDropDescriptor(), lLeft - pad, topPad + lboxHeight);
		}

		// Right - Add top level wrappers
		int rboxLeft = rLeft - pad;
		int rboxTop = topPad;
		int rboxWidth = rWidth + 2 * pad;
		int rboxHeight = rHeight * 4 / 3;

		VectorObject rNodeShape = createNodeShape(jTree.getRightSide()
				.getType(), rboxLeft, rboxTop, rboxWidth, rboxHeight);
		this.add(rNodeShape);

		if (jTree.getRightSide().getWrapper() != null) {
			MLElementWrapper rWrap = jTree.getRightSide().getWrapper()
					.getJoinedWrapper();
			rWrap.setHeight(rboxHeight + "px");
			rWrap.setWidth(rboxWidth + "px");
			panel.add(rWrap, rLeft - pad, topPad);
			panel.add(rWrap.getDropDescriptor(), rLeft - pad, topPad + rboxHeight);
		}

		int[] a = { lboxHeight + topPad, rboxHeight + topPad };
		return a;
	}

	private void splitSidesInMiddle() {

		Rectangle equalsTop = new Rectangle(sideLengthLeft - topPad, topPad,
				topPad * 2, topPad / 2);
		Rectangle equalsBottom = new Rectangle(equalsTop.getX(),
				equalsTop.getY() + topPad, equalsTop.getWidth(),
				equalsTop.getHeight());

		equalsTop.setFillColor("black");
		equalsBottom.setFillColor("black");

		Line verticalLine = new Line(sideLengthLeft,
				equalsBottom.getAbsoluteTop(), sideLengthLeft, this.getHeight());

		Group split = new Group();
		split.add(equalsTop);
		split.add(equalsBottom);
		// split.add(verticalLine);

		this.add(split);

	}

	private Group createNodeShape(Type type, int x, int y, int width, int height) {
		Group shape = new Group();

		switch (type) {
		case Term:

			int skew = height / 2;
			int spline = height * 5 / 16;// NURB length for Bézier curves

			Path front = new Path(x, y);// Box Front, starts at top left
			front.curveRelativelyTo(-spline, 0, -spline, height, 0, height);// down
			front.lineRelativelyTo(width, 0);// right
			front.curveRelativelyTo(spline, 0, spline, -height, 0, -height);// up
			front.close();
			front.setFillColor("orange");

			Path top = new Path(x, y);// Box Top, starts bottom left corner
			top.lineRelativelyTo(skew, -skew);// up to the right
			top.lineRelativelyTo(width, 0);// right
			top.lineRelativelyTo(-skew, skew);// down left
			top.close();
			top.setFillColor("blue");

			Path side = new Path(x + width, y + height);// Side, starts lowest
			side.lineRelativelyTo(skew, -skew);// up, right
			side.curveRelativelyTo(spline, -spline, spline, -height, 0, -height);// up
			side.lineRelativelyTo(-skew, skew);// down,left
			side.close();
			side.setFillColor("red");

			shape.add(side);
			shape.add(front);// front added after side to overlap curve
			shape.add(top);
			break;

		case Series:
			Group ruler = new Group();

			Rectangle rectangle = new Rectangle(x, y - pad, width,
					height);
			rectangle.setFillColor("yellow");
			ruler.add(rectangle);

			int tickCount = rectangle.getWidth() / pad;
			for (int i = 0; i < tickCount; i++) {
				int xPos = x + (pad * i);
				int tickHeight;
				if (i % 8 == 0) {
					tickHeight = pad * 3 / 2;
				} else if (i % 4 == 0) {
					tickHeight = pad;
				} else {
					tickHeight = pad * 2 / 3;
				}
				Line tick = new Line(xPos, y - pad, xPos, y - pad + tickHeight);
				ruler.add(tick);
			}
			shape.add(ruler);
			break;

		case Number:

			Ellipse ellipseNum = new Ellipse(x + width / 2, y + height / 2,
					width*2/3, height*2/3);
			ellipseNum.setFillColor("lime");

			shape.add(ellipseNum);
			break;

		case Variable:

			// Starts at top left inner corner
			Path star = new Path(x, y);// ( 0, 50),
			star.lineRelativelyTo(10, -30);
			star.lineRelativelyTo(10, 30);// ( 10, 20),
			star.lineRelativelyTo(30, 0);// ( 40, 20),
			star.lineRelativelyTo(-20, 20);// ( 20, 0),
			star.lineRelativelyTo(10, 30);// ( 30, -30),
			star.lineRelativelyTo(-30, -20);// ( 0, -10),
			star.lineRelativelyTo(-30, 20);// (-30, -30),
			star.lineRelativelyTo(10, -30);// (-20, 0),
			star.lineRelativelyTo(-20, -20);// (-40, 20),
			// star.lineRelativelyTo(30, 0);// (-10, 20),
			star.close();

			star.setFillColor("red");

			shape.add(star);
			break;

		case Fraction:

			// TODO
			Rectangle frac = new Rectangle(x + width / 2, y + height / 2, width,
					height);
			shape.add(frac);
			break;

		case Exponent:

			// TODO
			Rectangle exp = new Rectangle(x + width / 2, y + height / 2, width,
					height);
			shape.add(exp);
			break;

		case Function:
			// TODO
			Rectangle fun = new Rectangle(x + width / 2, y + height / 2, width,
					height);
			shape.add(fun);
			break;

		}

		return shape;
	}
}
