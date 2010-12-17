package nng.pong;


import java.awt.event.*;

import nng.graph.Node;

public class PongInput implements KeyListener, MouseMotionListener  {
	
	public boolean singlePlayer;
	protected boolean goingUp1 = false;
	protected boolean goingDown1 = false;
	protected boolean goingUp2 = false;
	protected boolean goingDown2 = false;

	protected int oldY = 0;
		
	protected Node rootNode;
	
	public PongInput(Node rootNode, boolean singlePlayer) {
		this.singlePlayer = singlePlayer;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_T:
			goingUp1 = true;
			break;
		case KeyEvent.VK_G:
			goingDown1 = true;
			break;
		}
		
		if (!singlePlayer) switch (e.getKeyCode()) {		
		case KeyEvent.VK_O:
			goingUp2 = true;
			break;
		case KeyEvent.VK_L:
			goingDown2 = true;
			break;
			
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_T:
			goingUp1 = false;
			break;
		case KeyEvent.VK_G:
			goingDown1 = false;
			break;
		}
		
		if (!singlePlayer) switch (e.getKeyCode()) {		
		case KeyEvent.VK_O:
			goingUp2 = false;
			break;
		case KeyEvent.VK_L:
			goingDown2 = false;
			break;
			
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	public boolean isSinglePlayer() {
		return singlePlayer;
	}

	public boolean isGoingUp1() {
		return goingUp1;
	}

	public boolean isGoingDown1() {
		return goingDown1;
	}

	public boolean isGoingUp2() {
		return goingUp2;
	}

	public boolean isGoingDown2() {
		return goingDown2;
	}

	public Node getRootNode() {
		return rootNode;
	}

	@Override
	public void mouseDragged(MouseEvent e) {}

	@Override
	public void mouseMoved(MouseEvent e) {
	
	}

}
