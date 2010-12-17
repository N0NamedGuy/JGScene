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

