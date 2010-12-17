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

public class PongState {
	public double[] ballPos = {0.0, 0.0, 0.0}; 
	public double[] ballVel = {0.0, 0.0, 0.0};
	
	public double[] p1Pos = {0.0,0.0};
	public double[] p1Vel= {0.0,0.0};
	public double[] p2Pos = {0.0,0.0};
	public double[] p2Vel = {0.0,0.0};
}
