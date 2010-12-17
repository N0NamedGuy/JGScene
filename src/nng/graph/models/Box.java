package nng.graph.models;

import java.nio.DoubleBuffer;

import javax.media.opengl.GL;

import nng.graph.Node;

import com.sun.opengl.util.GLUT;


public class Box extends Node {

	public Box() {
		super();
	}

	@Override
	public void render(GL gl) {
		super.render(gl);
		
		GLUT glut = new GLUT();
		
		if (!wireframe) {
			gl.glColor4dv(DoubleBuffer.wrap(fillColor));
			glut.glutSolidCube(1.0f);
		}
		
		gl.glColor4dv(DoubleBuffer.wrap(lineColor));
		glut.glutWireCube(1.0f);
	}
	
	public String toJava() {
		return toJava("");
	}
	
	public String toJava(String tab) {
		return super.toJava(tab) + tab + "GLUT glut = new GLUT().glutWireCube(1.0f);\n";
	}
	
	public String getGraph(String tab) {
		return tab + "B [" + getName() + "] (" + translation[0] + ", " + translation[1] + ", " + translation[2] + ") "
			+ "(" + scale[0] + "x, " + scale[1] + "x, " + scale[2] + "x) "
			+ "(" + rotation[0] + "º, " + rotation[1] + "º, " + rotation[2] + "º) ";
	}
	
	public String getShapeName() {
		return "Box";
	}
}
