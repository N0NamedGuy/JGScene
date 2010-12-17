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

import nng.cgi.model.Chair;
import nng.graph.viewer.FrameViewer;
import nng.graph.x3d.X3D;

public class ChairViewer {
	public static void main(String[] args) {
		Chair rootNode = new Chair();
		System.out.println(rootNode.getGraph());
		
		FrameViewer frame = new FrameViewer(rootNode);
		frame.canvas.graphCubeSize = 60.0;
//		frame.canvas.addKeyListener(new ChairInput(rootNode));
		frame.canvas.addKeyListener(new DebugInput(frame.canvas, rootNode));
		
		System.out.println(X3D.toX3DStr(rootNode));
	}

}

