/* 		CS 2336 Group Project
 * 		This class was created by ___(Creator's name here)___
 */

import java.lang.Math;

public class Edge 
{
	protected double length;
	protected Vertex vertex1;
	protected Vertex vertex2;
	
	
	public Edge(Vertex v1, Vertex v2)
	{
		vertex1 = v1;
		vertex2 = v2;
		length = length(v1, v2);
	}
	
	public double length(Vertex v1, Vertex v2)
	{
		double xLength = v1.xLocation - v2.xLocation;
		double yLength = v1.yLocation - v2.yLocation;
		
		return Math.hypot(xLength, yLength); 
	}
	
	public double getLength()
	{
		return length;
	}
}

