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
package nng.graph.models;

import java.nio.DoubleBuffer;

import javax.media.opengl.GL;

import nng.graph.Node;

import com.sun.opengl.util.GLUT;


public class Sphere extends Node {

	int slices;
	int stacks;


	public Sphere() {
		super();
		slices = stacks = 10;
	}
	
	public Sphere(int slices, int stacks) {
		super();
		this.slices = slices;
		this.stacks = stacks;
	}

	@Override
	public void render(GL gl) {
		super.render(gl);
		
		GLUT glut = new GLUT();
		
		if (!wireframe) {
			gl.glColor4dv(DoubleBuffer.wrap(fillColor));
			glut.glutSolidSphere(1.0f, slices, stacks);
		}
		
		gl.glColor4dv(DoubleBuffer.wrap(lineColor));
		glut.glutWireSphere(1.0f, slices, stacks);
	}

	public String getGraph(String tab) {
		return tab + getShapeName() + "[" + getName() + "] (" + translation[0] + ", " + translation[1] + ", " + translation[2] + ") "
			+ "(" + scale[0] + "x, " + scale[1] + "x, " + scale[2] + "x) "
			+ "(" + rotation[0] + "º, " + rotation[1] + "º, " + rotation[2] + "º) ";
	}
	
	public String getShapeName() {
		return "Sphere";
	}
}
