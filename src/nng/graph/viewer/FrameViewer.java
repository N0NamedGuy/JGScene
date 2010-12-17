package nng.graph.viewer;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import nng.graph.INode;

import com.sun.opengl.util.FPSAnimator;

public class FrameViewer extends JFrame {
	private static final long serialVersionUID = 4731390555918123576L;
	public INode rootNode;
	public GraphViewer canvas = new GraphViewer();
	
	public FrameViewer(INode rootNode) {
		  super("Graph Scene Viewer");
		  
	      canvas.setSize(800, 600);
	      add(canvas);
	      pack();
	      
	      canvas.setRootNode(rootNode);
	 
	      final FPSAnimator animator = new FPSAnimator(canvas, 60);
	      
	      addWindowListener(new WindowAdapter() {
	    	  public void windowClosing(WindowEvent e) {
	              new Thread(new Runnable() {
	                  public void run() {
	                    animator.stop();
	                    System.exit(0);
	                  }
	              }).start();
	          }
	      });
	      setVisible(true);
	      animator.start();
  	}
}

