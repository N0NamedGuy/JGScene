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

public class PongRunner extends Thread {
	
	private static final long FPMS = 30;
	protected PongField pongField;
	
	protected PongState gs;
	
	protected boolean started;
	protected PongInput input;
	
	protected double rot = 0;
	
	public PongRunner(PongField pongField, PongInput input, PongState gs) {
		this.pongField = pongField;
		this.input = input;
		
		this.gs = gs;
		
		doStart();
		
		started = false;
	}
	
	public void run() {
		
		long oldTime = System.currentTimeMillis();
		long delta;
		
		for (;;) {
			
			if (!started) {
				doStart();
			}
			
			doInput();
			doPhysics();
			doScoring();
				
			doFlip();		
			
			delta = System.currentTimeMillis() - oldTime;
			if (delta < FPMS) 
			try {
				sleep( FPMS - delta);
			} catch (InterruptedException e) {
				break;
			}
			oldTime = System.currentTimeMillis();
			
		}
	}

	private void doInput() {
		if (!(input.isGoingDown1() && input.isGoingUp1()))
			if (input.isGoingUp1()) gs.p1Vel[0] += PongCommon.PAD_ACCEL; 
			else if (input.isGoingDown1()) gs.p1Vel[0] -= PongCommon.PAD_ACCEL;
			else gs.p1Vel[0] = 0.0;
		
		if (!(input.isGoingLeft1() && input.isGoingRight1()))
			if (input.isGoingLeft1()) gs.p1Vel[1] += PongCommon.PAD_ACCEL; 
			else if (input.isGoingRight1()) gs.p1Vel[1] -= PongCommon.PAD_ACCEL;
			else gs.p1Vel[1] = 0.0;
			
		
		for (int i = 0; i < 2; i++) {
			if (gs.p1Vel[i] > PongCommon.PAD_MAX_VEL) gs.p1Vel[i] = PongCommon.PAD_MAX_VEL;
			if (gs.p1Vel[i] < -PongCommon.PAD_MAX_VEL) gs.p1Vel[i] = -PongCommon.PAD_MAX_VEL;
		
			if (gs.p2Vel[i] > PongCommon.PAD_MAX_VEL) gs.p2Vel[i] = PongCommon.PAD_MAX_VEL;
			if (gs.p2Vel[i] < -PongCommon.PAD_MAX_VEL) gs.p2Vel[i] = -PongCommon.PAD_MAX_VEL;
		
		}
		
		
	}

	private void doStart() {
		started = true;
		gs.ballPos[0] = gs.ballPos[1] = gs.ballPos[2] = 0.0;
		
		gs.ballVel[0] = (Math.random() - 0.5) * PongCommon.PAD_MAX_VEL;
		gs.ballVel[1] = (Math.random() - 0.5) * PongCommon.PAD_MAX_VEL;
		gs.ballVel[2] = (Math.random() - 0.5) * PongCommon.PAD_MAX_VEL;
	}

	private void doFlip() {
		pongField.setRotation(0.0, 45, 0.0);
		
		pongField.ball.setTranslation(gs.ballPos[0], gs.ballPos[1], gs.ballPos[2]);
		pongField.player1.setTranslation(-100.0, gs.p1Pos[0], gs.p1Pos[1]);
		pongField.player2.setTranslation( 100.0, gs.p2Pos[0], gs.p2Pos[1]);
	}

	private void doScoring() {
		
	}

	private void doPhysics() {
		
		for (int i = 0; i < 2; i++) {
			gs.p1Pos[i] += gs.p1Vel[i];
			gs.p2Pos[i] += gs.p2Vel[i];
		}
		
		for (int i = 0; i < 3; i++) {
			gs.ballPos[i] += gs.ballVel[i];
			
			if (gs.ballPos[i] < -PongCommon.FIELD_HALF_SIZE) {
				gs.ballPos[i] = -PongCommon.FIELD_HALF_SIZE;
				gs.ballVel[i] = -gs.ballVel[i];
			} else if (gs.ballPos[i] > PongCommon.FIELD_HALF_SIZE) {
				gs.ballPos[i] = PongCommon.FIELD_HALF_SIZE;
				gs.ballVel[i] = -gs.ballVel[i];
			}
		}
		
	}
	
}
