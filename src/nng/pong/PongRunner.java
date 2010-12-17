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
package nng.pong;

public class PongRunner extends Thread {
	
	private static final long FPMS = 30;
	protected PongField pongField;
	protected double ballX;
	protected double ballY;
	
	protected double velX;
	protected double velY;
	
	protected double p1Pad;
	protected double p2Pad;
	protected double p1Vel;
	protected double p2Vel;
	
	protected boolean started;
	protected PongInput input;
	
	protected double rot = 0;
	
	public PongRunner(PongField pongField, PongInput input) {
		this.pongField = pongField;
		this.input = input;
		
		p1Pad = 0;
		p2Pad = 0;

		p1Vel = 0;
		p2Vel = 0;
		
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
			if (input.isGoingUp1()) p1Vel += PongCommon.PAD_ACCEL; 
			else if (input.isGoingDown1()) p1Vel -= PongCommon.PAD_ACCEL;
			else p1Vel = 0.0;

		if (!(input.isGoingDown2() && input.isGoingUp2()) || input.singlePlayer)

			if ((!input.singlePlayer && input.isGoingUp2())
					|| (input.singlePlayer && ballY > p2Pad)) 
				p2Vel += PongCommon.PAD_ACCEL;
			
			else if ((!input.singlePlayer && input.isGoingUp2())
					|| (input.singlePlayer && ballY < p2Pad)) 
				p2Vel -= PongCommon.PAD_ACCEL;
			else p2Vel = 0.0;
			
		if (p1Vel > PongCommon.PAD_MAX_VEL) p1Vel = 4.0;
		if (p1Vel < -PongCommon.PAD_MAX_VEL) p1Vel = -4.0;
		
		if (p2Vel > PongCommon.PAD_MAX_VEL) p2Vel = PongCommon.PAD_MAX_VEL;
		if (p2Vel < -PongCommon.PAD_MAX_VEL) p2Vel = -PongCommon.PAD_MAX_VEL;
		
	}

	private void doStart() {
		started = true;
		ballX = 0;
		ballY = 0;
		
		velX = (Math.random() - 0.5) * PongCommon.PAD_MAX_VEL;
		velY = (Math.random() - 0.5) * PongCommon.PAD_MAX_VEL;
	}

	private void doFlip() {
		pongField.setRotation(((90.0 * (100.0 + ballY)) / 200.0) - 45.0, ((90.0 * (100.0 - ballX)) / 200.0) - 45.0 ,0.0);
				
		pongField.ball.setTranslation(ballX, ballY, 0.0);
		pongField.player1.setTranslation(-100.0, p1Pad, 0.0);
		pongField.player2.setTranslation( 100.0, p2Pad, 0.0);
	}

	private void doScoring() {
		if (ballX < -PongCommon.FIELD_HALF_SIZE + PongCommon.BALL_RADIUS) {
			velX = -velX;
								
			if (p1Pad - PongCommon.PAD_HALF_SIZE > ballY 
					|| p1Pad + PongCommon.PAD_HALF_SIZE < ballY) {
				System.out.println("Player 2 scores!");
				started = false;
			} else {
				if (velX < 0) {
					velX -= PongCommon.BALL_HIT_ACCEL;
				} else {
					velX += PongCommon.BALL_HIT_ACCEL;
				}
				velY += p1Vel;
			}
		}
		
		if (ballX > PongCommon.FIELD_HALF_SIZE - PongCommon.BALL_RADIUS) {
			velX = -velX;
								
			if (p2Pad - PongCommon.PAD_HALF_SIZE > ballY || p2Pad + PongCommon.PAD_HALF_SIZE < ballY) {
				System.out.println("Player 1 scores!");
				started = false;
			} else {
				if (velX < 0) {
					velX -= PongCommon.BALL_HIT_ACCEL;
				} else {
					velX += PongCommon.BALL_HIT_ACCEL;
				}
				velY += p2Vel;
			}
		}
	}

	private void doPhysics() {
		ballX += velX;
		ballY += velY;
		
		p1Pad += p1Vel;
		p2Pad += p2Vel;
				
		if (ballY < -PongCommon.FIELD_HALF_SIZE) {
			ballY = -PongCommon.FIELD_HALF_SIZE;
			velY = -velY;
		}
		
		if (ballY > PongCommon.FIELD_HALF_SIZE) {
			ballY = PongCommon.FIELD_HALF_SIZE;
			velY = -velY;
		}
		
		if (p1Pad > PongCommon.FIELD_HALF_SIZE - PongCommon.PAD_HALF_SIZE)
			p1Pad = PongCommon.FIELD_HALF_SIZE - PongCommon.PAD_HALF_SIZE;
		if (p2Pad > PongCommon.FIELD_HALF_SIZE - PongCommon.PAD_HALF_SIZE)
			p2Pad = PongCommon.FIELD_HALF_SIZE - PongCommon.PAD_HALF_SIZE;
		
		if (p1Pad < -PongCommon.FIELD_HALF_SIZE + PongCommon.PAD_HALF_SIZE)
			p1Pad = -PongCommon.FIELD_HALF_SIZE + PongCommon.PAD_HALF_SIZE;
		if (p2Pad < -PongCommon.FIELD_HALF_SIZE + PongCommon.PAD_HALF_SIZE)
			p2Pad = -PongCommon.FIELD_HALF_SIZE + PongCommon.PAD_HALF_SIZE;
		
	}

	/**
	 * @return the ballX
	 */
	public double getBallX() {
		return ballX;
	}

	/**
	 * @return the ballY
	 */
	public double getBallY() {
		return ballY;
	}
	
}
