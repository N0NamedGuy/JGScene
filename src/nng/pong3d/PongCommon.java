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

public class PongCommon {
	public static final double FIELD_SIZE = 200.0;
	public static final double FIELD_HALF_SIZE = FIELD_SIZE / 2.0;
	public static final double FIELD_MARGIN = 10.0;

	public static final double BALL_RADIUS = 1.0;
	public static final double BALL_MAX_VEL = BALL_RADIUS * 2.0;
	public static final double BALL_HIT_ACCEL = 0.5;
	
	public static final double PAD_SIZE = 25.0;
	public static final double PAD_HALF_SIZE = PAD_SIZE / 2.0;
	public static final double PAD_ACCEL = PAD_SIZE / (FIELD_SIZE / 2.0);
	public static final double PAD_MAX_VEL = BALL_MAX_VEL * 2.0;
	
	
}
