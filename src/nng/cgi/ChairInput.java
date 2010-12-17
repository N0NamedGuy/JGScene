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


import java.awt.event.*;

import nng.cgi.model.Chair;
import nng.graph.viewer.GraphInput;


public class ChairInput extends GraphInput {
	
	protected double seatH;
	protected double reclination;
	protected double rotation;
	
	public ChairInput(Chair rootNode) {
		super(rootNode);
		seatH = 0.0;
		reclination = 0.0;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);
		
		Chair c = (Chair)rootNode;
		
		switch (e.getKeyCode()) {
		case KeyEvent.VK_T:
			seatH += 0.25;
			if (seatH > 1.25) seatH = 1.25; 
			break;
		case KeyEvent.VK_G:
			seatH -= 0.25;
			if (seatH < -7.5) seatH = -7.5; 
			break;
			
		case KeyEvent.VK_Y:
			reclination += 1.0;
			if (reclination > 70.0) reclination = 70.0;
			break;
		case KeyEvent.VK_U:
			reclination -= 1.0;
			if (reclination < -5.0) reclination = -5.0;
			break;
		
		case KeyEvent.VK_H:
			rotation += 0.5;
			seatH -= 0.02;
			if (seatH < -7.5) seatH = -7.5;
			break;
		case KeyEvent.VK_J:
			rotation -= 0.5;
			seatH += 0.02;
			if (seatH > 1.25) seatH = 1.25;
			break;
		}
		c.seatNBack.setTranslation(0.0, seatH, 0.0);
		c.seatNBack.setRotation(0.0, rotation, 0.0);
		c.back.setRotation(reclination, 0.0, 0.0);
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}

}
