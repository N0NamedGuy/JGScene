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

