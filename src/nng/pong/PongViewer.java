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

