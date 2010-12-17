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
package nng.pong3d;

import nng.graph.Node;
import nng.graph.models.Box;
import nng.graph.models.Sphere;


public class PongField extends Node {
	public Box player1;
	public Box player2;
	public Sphere ball;
	public Node wall;
	
	public PongField() {
		player1 = new Box();
		player1.setTranslation(-(PongCommon.FIELD_SIZE / 2.0),0.0,0.0);
		player1.setScale(1.0, PongCommon.PAD_SIZE, PongCommon.PAD_SIZE);
		player1.setColor(1.0,0.0,0.0,1.0);
		addNode(player1);
		
		player2 = new Box();
		player2.setTranslation((PongCommon.FIELD_SIZE / 2.0),0.0,0.0);
		player2.setScale(1.0, PongCommon.PAD_SIZE, PongCommon.PAD_SIZE);
		player2.setColor(0.0,0.0,1.0,1.0);
		addNode(player2);
		
		ball = new Sphere();
		ball.setScale(PongCommon.BALL_RADIUS, PongCommon.BALL_RADIUS, PongCommon.BALL_RADIUS);
		addNode(ball);
		
		wall = new Node();
		
			Box wallWire = new Box();
			wallWire.setWireframe(true);
			wall.addNode(wallWire);
			
			Box wallBack = new Box();
			wallBack.setTranslation(0.0,0.0,0.5);
			wallBack.setScale(1.0,1.0,0.01);
			wallBack.setColor(0.5,0.5,0.0,0.5);
			wall.addNode(wallBack);
			
			Box wallLeft = new Box();
			wallLeft.setTranslation(-0.5,0.0,0.0);
			wallLeft.setScale(0.01,1.0,1.0);
			wallLeft.setColor(0.5,0.0,0.0,0.5);
			wall.addNode(wallLeft);
			
			Box wallRight = new Box();
			wallRight.setTranslation(0.5,0.0,0.0);
			wallRight.setScale(0.01,1.0,1.0);
			wallRight.setColor(0.5,0.0,0.0,0.5);
			wall.addNode(wallRight);
			
			Box wallTop = new Box();
			wallTop.setTranslation(0.0,0.5,0.0);
			wallTop.setScale(1.0,0.01,1.0);
			wallTop.setColor(0.0,0.5,0.0,0.5);
			wall.addNode(wallTop);
			
			Box wallBottom = new Box();
			wallBottom.setTranslation(0.0,-0.5,0.0);
			wallBottom.setScale(1.0,0.01,1.0);
			wallBottom.setColor(0.0,0.5,0.0,0.5);
			wall.addNode(wallBottom);
			
		wall.setScale(PongCommon.FIELD_SIZE + PongCommon.FIELD_MARGIN, PongCommon.FIELD_SIZE + PongCommon.FIELD_MARGIN, PongCommon.FIELD_SIZE);
		wall.setLineColor(1.0,1.0,0.0,1.0);
		
		addNode(wall);
		
	}
}
