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

import nng.graph.INode;
import nng.graph.Node;
import nng.graph.models.Box;

public class FrameViewerTest {
	public static void main(String[] args) {
		INode rootNode = makeGraphScene();
		
		FrameViewer frame = new FrameViewer(rootNode);
		frame.canvas.addKeyListener(new GraphInput(rootNode));
	}

  	private static INode makeGraphScene() {
  	    INode rootNode = new Node();
       
        Box c = new Box();
        rootNode.addNode(c);
       
        c = new Box();
        c.setTranslation(0.5, 0.5, 0.5);
        c.setFillColor(1.0, 0.0, 0.0, 1.0);
        rootNode.addNode(c);
       
  		Node child = new Node();
        child.setTranslation(-2.0, -2.0, 1.0);
        
        	c = new Box();
        	c.setFillColor(1.0, 0.0, 1.0, 1.0);
        	child.addNode(c);
        
        	c = new Box();
        	c.setFillColor(0.0, 1.0, 1.0, 1.0);
        	c.setTranslation(0.5, 0.5, 0.5);
        	
        	c = new Box();
        	c.setScale(1.0,2.0,2.0);
        	c.setTranslation(2.0,0.0,0.0);
        	c.setRotation(30.0,30.0,0.0);
        	child.addNode(c);
        	
        	child.addNode(c);
        
        rootNode.addNode(child);
        System.out.println(rootNode.toJava());
        
        return rootNode;
    }

}

