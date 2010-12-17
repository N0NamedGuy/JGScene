/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details. 
 */
package nng.graph;
import java.util.ArrayList;
import java.util.List;

import javax.media.opengl.GL;


public class Node implements INode {
	
	protected List<INode> children;
	
	protected double translation[] = {0.0, 0.0, 0.0};
	protected double scale[] = {1.0, 1.0, 1.0};
	protected double rotation[] = {0.0, 0.0, 0.0};
	protected double fillColor[] = {1.0, 1.0, 1.0, 1.0};
	protected double lineColor[] = fillColor.clone();
	protected boolean wireframe = false;
	protected String name = null;
	
	public Node() {
		children = new ArrayList<INode>();
	}
	
	/* (non-Javadoc)
	 * @see graph.INode#addNode(graph.Node)
	 */
	@Override
	public void addNode(Node n) {
		children.add(n);
	}
	
	/* (non-Javadoc)
	 * @see graph.INode#deleteNode(graph.INode)
	 */
	@Override
	public void deleteNode(INode n) {
		children.remove(n);
	}
	
	/* (non-Javadoc)
	 * @see graph.INode#deleteNode(int)
	 */
	@Override
	public void deleteNode(int i) {
		children.remove(i);
	}
	
	/* (non-Javadoc)
	 * @see graph.INode#setTranslation(double, double, double)
	 */
	@Override
	public void setTranslation(double x, double y, double z) {
		translation[0] = x;
		translation[1] = y;
		translation[2] = z;
	}
	
	/* (non-Javadoc)
	 * @see graph.INode#addTranslation(double, double, double)
	 */
	@Override
	public void addTranslation(double x, double y, double z) {
		translation[0] += x;
		translation[1] += y;
		translation[2] += z;
	}
	
	/* (non-Javadoc)
	 * @see graph.INode#setScale(double, double, double)
	 */
	@Override
	public void setScale(double sx, double sy, double sz) {
		scale[0] = sx;
		scale[1] = sy;
		scale[2] = sz;
	}
		
	/* (non-Javadoc)
	 * @see graph.INode#setRotation(double, double, double)
	 */
	@Override
	public void setRotation(double rx, double ry, double rz) {
		rotation[0] = rx;
		rotation[1] = ry;
		rotation[2] = rz;
	}
	
	/* (non-Javadoc)
	 * @see graph.INode#setFillColor(double, double, double)
	 */
	@Override
	public void setFillColor(double red, double green, double blue, double alpha) {
		fillColor[0] = red;
		fillColor[1] = green;
		fillColor[2] = blue;
		fillColor[3] = alpha;
		
		
		if (children != null) for (INode n: children) {
			n.setFillColor(red, green, blue, alpha);
		}
	}
	
	/* (non-Javadoc)
	 * @see graph.INode#setLineColor(double, double, double)
	 */
	@Override
	public void setLineColor(double red, double green, double blue, double alpha) {
		lineColor[0] = red;
		lineColor[1] = green;
		lineColor[2] = blue;
		lineColor[3] = alpha;
		
		
		if (children != null) for (INode n: children) {
			n.setLineColor(red, green, blue, alpha);
		}
	}
	
	/* (non-Javadoc)
	 * @see graph.INode#setColor(double, double, double)
	 */
	@Override
	public void setColor(double red, double green, double blue, double alpha) {
		setFillColor(red, green, blue, alpha);
		setLineColor(red, green, blue, alpha);
	}
	
	
	
	/* (non-Javadoc)
	 * @see graph.INode#getTranslation()
	 */
	@Override
	public double[] getTranslation() {
		return translation;
	}

	/* (non-Javadoc)
	 * @see graph.INode#setTranslation(double[])
	 */
	@Override
	public void setTranslation(double[] translation) {
		this.translation = translation;
	}

	/* (non-Javadoc)
	 * @see graph.INode#getScale()
	 */
	@Override
	public double[] getScale() {
		return scale;
	}

	/* (non-Javadoc)
	 * @see graph.INode#setScale(double[])
	 */
	@Override
	public void setScale(double[] scale) {
		this.scale = scale;
	}

	/* (non-Javadoc)
	 * @see graph.INode#getRotation()
	 */
	@Override
	public double[] getRotation() {
		return rotation;
	}

	/* (non-Javadoc)
	 * @see graph.INode#setRotation(double[])
	 */
	@Override
	public void setRotation(double[] rotation) {
		this.rotation = rotation;
	}

	/* (non-Javadoc)
	 * @see graph.INode#getFillColor()
	 */
	@Override
	public double[] getFillColor() {
		return fillColor;
	}

	/* (non-Javadoc)
	 * @see graph.INode#setFillColor(double[])
	 */
	@Override
	public void setFillColor(double[] fillColor) {
		this.fillColor = fillColor;
	}

	/* (non-Javadoc)
	 * @see graph.INode#getLineColor()
	 */
	@Override
	public double[] getLineColor() {
		return lineColor;
	}

	/* (non-Javadoc)
	 * @see graph.INode#setLineColor(double[])
	 */
	@Override
	public void setLineColor(double[] lineColor) {
		this.lineColor = lineColor;
	}
	
	/* (non-Javadoc)
	 * @see graph.INode#render(javax.media.opengl.GL)
	 */
	@Override
	public void render(GL gl) {		
		gl.glTranslated(translation[0], translation[1], translation[2]);

		gl.glRotated(rotation[0], 1.0, 0.0, 0.0);
		gl.glRotated(rotation[1], 0.0, 1.0, 0.0);
		gl.glRotated(rotation[2], 0.0, 0.0, 1.0);
		gl.glScaled(scale[0], scale[1], scale[2]);
		
		for (INode n: children) {
			
			gl.glPushMatrix();
			n.render(gl);
			gl.glPopMatrix();
		}
	}
	
	/* (non-Javadoc)
	 * @see graph.INode#toJava()
	 */
	@Override
	public String toJava() {
		return toJava("");
	}
	
	/* (non-Javadoc)
	 * @see graph.INode#toJava(java.lang.String)
	 */
	@Override
	public String toJava(String tab) {
		String ret = "";
		
		ret += tab + "gl.glRotated(" + rotation[0] + ", 1.0, 0.0, 0.0);";
		ret += tab + "gl.glRotated(" + rotation[1] + ", 0.0, 1.0, 0.0);";
		ret += tab + "gl.glRotated(" + rotation[2] + ", 0.0, 0.0, 1.0);";
		
		ret += tab + "gl.glScaled(" + scale[0] + ", " + scale[1] + ", " + scale[2] + ");\n";
		ret += tab + "gl.glTranslated(" + translation[0] + ", " + translation[1] + ", " + translation[2] + ");\n";
	    ret += tab + "gl.glColor3d(" + fillColor[0] + ", " + fillColor[1] + ", " + fillColor[2] + ");\n";
		
		for (INode n: children) {
			ret += tab +  "gl.glPushMatrix();\n";
			ret += tab +  n.toJava(tab + "  ") + ";\n";
			ret += tab +  "gl.glPopMatrix();\n";
		}
		
		return ret;
	}
	
	/* (non-Javadoc)
	 * @see graph.INode#getGraph()
	 */
	@Override
	public String getGraph() {
		return getGraph("");
	}
	
	public String getGraph(String tab) {
		String ret = tab + "N [" + name + "] (" + translation[0] + ", " + translation[1] + ", " + translation[2] + ") "
			+ "(" + scale[0] + "x, " + scale[1] + "x, " + scale[2] + "x) "
			+ "(" + rotation[0] + "º, " + rotation[1] + "º, " + rotation[2] + "º)\n";
		for (INode n: children) {
			ret += tab + n.getGraph(tab + "  ") + "\n";
		}
		
		return ret;
	}

	/* (non-Javadoc)
	 * @see graph.INode#getChildren()
	 */
	@Override
	public List<INode> getChildren() {
		return children;
	}
	
	/* (non-Javadoc)
	 * @see graph.INode#getShapeName()
	 */
	@Override
	public String getShapeName() {
		return "Node";
	}

	@Override
	public boolean isWireframe() {
		return wireframe;
	}

	@Override
	public void setWireframe(boolean wireframe) {
		this.wireframe = wireframe;
		if (children != null) for (INode n: children) {
			n.setWireframe(wireframe);
		}
	}

	/**
	 * @return the name
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	
}
