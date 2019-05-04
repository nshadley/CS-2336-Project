/* 		CS 2336 Group Project
 * 		This class was created by ___(Creator's name here)___
 */

import java.util.ArrayList;

public class Vertex
{
	private static int radius = 20;
	protected int xLocation;
	protected int yLocation;
	protected int num;
	
	protected ArrayList<Edge> edges = new ArrayList<Edge>();
	
	public Vertex()
	{
		xLocation = 0;
		yLocation = 0;
		num = 0;
	}
	
	public Vertex(int x, int y, int n)
	{
		xLocation = x;
		yLocation = y;
		num = n;
	}
}
