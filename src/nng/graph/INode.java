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

import java.util.List;

import javax.media.opengl.GL;

public interface INode {

	public abstract void addNode(Node n);
	public abstract void deleteNode(INode n);
	public abstract void deleteNode(int i);
	public abstract void setTranslation(double x, double y, double z);
	public abstract void addTranslation(double x, double y, double z);
	public abstract void setScale(double sx, double sy, double sz);
	public abstract void setRotation(double rx, double ry, double rz);
	public abstract void setFillColor(double red, double green, double blue, double alpha);
	public abstract void setLineColor(double red, double green, double blue, double alpha);
	public abstract void setColor(double red, double green, double blue, double alpha);
	public abstract double[] getTranslation();
	public abstract void setTranslation(double[] translation);
	public abstract double[] getScale();
	public abstract void setScale(double[] scale);
	public abstract double[] getRotation();
	public abstract void setRotation(double[] rotation);
	public abstract double[] getFillColor();
	public abstract void setFillColor(double[] fillColor);
	public abstract double[] getLineColor();
	public abstract void setLineColor(double[] lineColor);
	public abstract void render(GL gl);
	public abstract String getGraph();
	public abstract String getGraph(String tab);
	public abstract List<INode> getChildren();
	public abstract String getShapeName();
	public abstract boolean isWireframe();
	public abstract void setWireframe(boolean wireframe);
	public abstract String getName();
	public abstract void setName(String name);
	
}