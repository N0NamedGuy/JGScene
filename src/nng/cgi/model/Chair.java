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
package nng.cgi.model;

import nng.graph.Node;
import nng.graph.models.Box;

public class Chair extends Node {
	public Node seatNBack;
	public Node support;
	public Node back;
	public Chair() {
		setName("chair");
		
		seatNBack = new Node();
		seatNBack.setName("seatNBack");
		
			Node seat = new Box();
			seat.setName("seat");
			seat.setScale(30.0, 5.0, 30.0);
			seatNBack.addNode(seat);
			
			back = new Node();
			back.setName("back");
			
				Node realBack = new Box();
				realBack.setName("realBack");
				realBack.setScale(30.0, 5.0, 30.0);
				realBack.setTranslation(0.0, 15.0, 2.5);
				realBack.setRotation(90.0,0.0,0.0);
				
			back.addNode(realBack);
			back.setTranslation(0.0, 2.5, 15.0);
			seatNBack.addNode(back);
				
			Node smallSupport = new Box();
			smallSupport.setName("smallSupport");
			smallSupport.setScale(2.5, 12.5, 2.5);
			smallSupport.setTranslation(0.0, -5.0, 0.0);
			seatNBack.addNode(smallSupport);
		
		seatNBack.setFillColor(1.0, 0.0, 0.0, 1.0);
		this.addNode(seatNBack);
		
		support = new Node();
		support.setName("support");
			Node bigSupport = new Box();
			bigSupport.setName("bigSupport");
			bigSupport.setScale(5.0, 20.0, 5.0);
			support.addNode(bigSupport);
			
			Node feet = new Node();
			feet.setName("feet");
				Node foot1 = new Box();
				foot1.setName("foot1");
				foot1.setScale(30.0, 2.5, 2.5);
				feet.addNode(foot1);
				
				Node foot2 = new Box();
				foot2.setName("foot2");
				foot2.setScale(2.5, 2.5, 30.0);
				feet.addNode(foot2);
			
			feet.setTranslation(0.0, -10.5, 0.0);
			support.addNode(feet);

		support.setTranslation(0, -20.0, 0);
		this.addNode(support);
		
		setLineColor(0.0, 0.0, 1.0, 1.0);
	}
}
