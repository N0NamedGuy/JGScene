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
package nng.pong;

import nng.graph.viewer.FrameViewer;

public class PongViewer {
	public static final boolean SINGLE_PLAYER = true;
	public static void main(String[] args) {
		PongField rootNode = new PongField();
		PongInput input = new PongInput(rootNode, SINGLE_PLAYER);
		
		FrameViewer frame = new FrameViewer(rootNode);
		frame.canvas.graphCubeSize = 125.0;
		frame.canvas.addKeyListener(input);
		frame.canvas.addMouseMotionListener(input);
		
		
		PongRunner runner = new PongRunner(rootNode, input);
		
		runner.start();
	}

}

