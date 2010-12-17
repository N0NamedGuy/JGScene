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
package nng.cgi;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import nng.graph.Node;
import nng.graph.viewer.GraphInput;
import nng.graph.viewer.GraphViewer;

public class DebugInput extends GraphInput implements KeyListener {
	private double proximity;
	private GraphViewer gv;
	
	public DebugInput(GraphViewer gv, Node rootNode) {
		super(rootNode);
		this.gv = gv;
		proximity = 0;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	
	}

	@Override
	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);
		switch (e.getKeyCode()) {
		case KeyEvent.VK_L:
			proximity -= 0.1;
			break;
		case KeyEvent.VK_O:
			proximity += 0.1;
			break;
		}
		
		gv.graphCubeSize = proximity;
		System.out.println(proximity);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
