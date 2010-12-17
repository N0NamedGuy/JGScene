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
package nng.christmas;

import nng.cgi.model.Chair;
import nng.graph.viewer.FrameViewer;
import nng.graph.viewer.GraphInput;
import nng.graph.x3d.X3D;

public class TreeViewer {
	public static void main(String[] args) {
		TreeModel rootNode = new TreeModel();
		
		FrameViewer frame = new FrameViewer(rootNode);
		frame.canvas.graphCubeSize = 6.0;
		frame.canvas.addKeyListener(new GraphInput(rootNode));
		
	}

}

