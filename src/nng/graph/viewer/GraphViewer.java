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
package nng.graph.viewer;

import java.nio.DoubleBuffer;


import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

import nng.graph.INode;

public class GraphViewer extends GLCanvas implements GLEventListener {
	private static final long serialVersionUID = 208223725369631054L;

	private static final double[] PERSP_MATRIX = {
		1.0, 0.0, 0.0, 0.0,
		0.0, 1.0, 0.0, 0.0,
		0.0, 0.0, 1.0, 0.001,
		0.0, 0.0, 0.0, 1.0
	};
	public enum ProjectionType {
		ORTHO_PROJ,
		PERSPECTIVE_PROJ
	}
	public double graphCubeSize = 2.0;
	
	GLAutoDrawable glDraw;
	INode rootNode = null;
	ProjectionType projection;	
	
	public GraphViewer() {
		addGLEventListener(this);
		projection = ProjectionType.PERSPECTIVE_PROJ;
	}
	
	@Override
	public void display(GLAutoDrawable glDrawable) {
		GL gl = glDrawable.getGL();
		
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		
		gl.glMatrixMode(GL.GL_MODELVIEW);
		gl.glLoadIdentity();
		
		if (rootNode != null) rootNode.render(gl);
	}

	@Override
	public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {}

	@Override
	public void init(GLAutoDrawable glDrawable) {
    	glDraw = glDrawable;
    	GL gl = glDrawable.getGL();
    	gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

    	gl.glEnable(GL.GL_DEPTH_TEST);
    	gl.glDepthFunc(GL.GL_LEQUAL);
    	gl.glHint(GL.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);
    	gl.glCullFace(GL.GL_BACK);
    	gl.glEnable(GL.GL_BLEND);
    	gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
    	  
    	gl.glEnable(GL.GL_LINE_SMOOTH);
    	gl.glHint(GL.GL_LINE_SMOOTH_HINT, GL.GL_DONT_CARE);
    	gl.glLineWidth(1.5f);

	}

	@Override
	public void reshape(GLAutoDrawable glDrawable, int x, int y, int width, int height) {
    	GL gl = glDrawable.getGL();
	
    	height = (height <= 0) ? 1 : height;
        double aspect = (double)width / (double)height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
    	if (aspect > 1.0f)
    		gl.glOrtho(-graphCubeSize * aspect, graphCubeSize * aspect, -graphCubeSize, graphCubeSize, graphCubeSize * 2, -graphCubeSize * 2); // manter a altura da janela
    	else
    		gl.glOrtho(-graphCubeSize, graphCubeSize, -graphCubeSize / aspect, graphCubeSize / graphCubeSize, graphCubeSize * 2, -graphCubeSize * 2); // manter a largura da janela  			
  		
    	gl.glMultMatrixd(DoubleBuffer.wrap(PERSP_MATRIX));
	}
	
	
	public void setRootNode(INode n) {
		this.rootNode = n;
	}
}
