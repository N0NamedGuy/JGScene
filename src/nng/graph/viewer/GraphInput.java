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


import java.awt.event.*;

import nng.graph.INode;

public class GraphInput implements KeyListener {

	protected INode rootNode;
	
	protected int xRotation;
	protected int yRotation;
	protected double proximity;
	
	public GraphInput(INode rootNode) {
		this.rootNode = rootNode;
		xRotation = 0;
		yRotation = 0;
		proximity = 0;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_D:
			yRotation++;
			break;
		case KeyEvent.VK_A:
			yRotation--;
			break;
		case KeyEvent.VK_W:
			xRotation++;
			break;
		case KeyEvent.VK_S:
			xRotation--;
			break;
			
		}
		
		rootNode.setRotation(xRotation, yRotation, 0);			
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
