

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import curves.ConeCurve;
import curves.Curve;

/**
 * Application to create a text STL file for a Cone curve
 * @author      Howard Rosenblum rosenbh@algonquincollege.com
 * @version     1.0              
 */
public class CreateStlCurve {
	private List<List<Point3d>>list ;

	/**
	 * Create a matrix of points
	 */
	public void createMatrix(Curve c1)  // Curve curve
	{
	//	Curve c1 = new HalfSphere(20.0);
		list = new ArrayList<>(); 
		
		double x = c1.getMinX();
		double y = c1.getMinY();
		
		double a = ( (c1.getMaxX() - c1.getMinX() ) / c1.getStepSize() + 1) ;
		double b = ( (c1.getMaxY() - c1.getMinY() ) / c1.getStepSize() + 1) ;
	
			 
		for (int i = 0 ; i < (int) a ; i++)
		{
			 y = c1.getMinY();
			ArrayList<Point3d> row = new ArrayList<>();
			
			for (int j=0 ; j<(int)b ; j++)
			{
						
				Point3d point1 = new Point3d( y+10, x+10, c1.getZ( x, y));				
				row.add(point1);			
				y = y + c1.getStepSize();
			}
			list.add(row);
			x = x + c1.getStepSize();
		}	
	}
	
	/**
	 * Create Solid based on values in matrix
	 * @param name Name of the solid
	 * @return Generated solid
	 */
	public Solid createSolid(String name)
	{
		Solid solid = new Solid(name);
		
		int width=list.size()-1;
		int height=list.get(0).size()-1;

		/**
		 * Create the curve
		 */
		for(int h=0; h<height; h++)
		{
			for(int w=0; w<width; w++)
			{
				/**
				 * how it is viewed from the above 
				 */
				solid.addFacet(
						list.get(w).get(h),    // bottom left
						list.get(w+1).get(h),  // bottom right
						list.get(w+1).get(h+1),// top right
						list.get(w).get(h+1)); // top left
			}
		}
		
		
		/**
		 * Creates the bottom edge
		 */	
		for(int x=0;x<list.get(0).size() - 1;x++)
		{
			solid.addFacet(
					new Point3d(list.get(0).get(x).getX(),0,0),    // bottom left
					new Point3d(list.get(0).get(x+1).getX(),0,0),  // bottom right
					list.get(0).get(x+1),// top right
					list.get(0).get(x)); // top left
			
		}
		
		/**
		 * Creates the top edge
		 */
		Curve c1 = new ConeCurve();
		int maxY =(int) (c1.getMaxY() - c1.getMinY() );
		
		for(int x=0;x<list.get(maxY).size() - 1;x++)
		{
			solid.addFacet(
					new Point3d(list.get(maxY).get(x).getX(),maxY,0),    // bottom left
					new Point3d(list.get(maxY).get(x+1).getX(),maxY,0),  // bottom right
					list.get(maxY).get(x+1),// top right
					list.get(maxY).get(x)); // top left
			
		}
		
		
		/**
		 * Creates the left edge
		 */
		for(int y=0;y<list.size() - 1;y++)
		{
			solid.addFacet(
					new Point3d(0,list.get(y).get(0).getY(),0),    // bottom left
					new Point3d(0,list.get(y+1).get(0).getY(),0),  // bottom right
					list.get(y+1).get(0),// top right
					list.get(y).get(0)); // top left
			
		}
		/**
		 * Creates the right edge
		 */

		int maxX =(int) (c1.getMaxX() - c1.getMinX() ); 
		for(int y=0;y<list.size() - 1;y++)
		{
			solid.addFacet(
					new Point3d(maxX,list.get(y).get(maxX).getY(),0),    // bottom left
					new Point3d(maxX,list.get(y+1).get(maxX).getY(),0),  // bottom right
					list.get(y+1).get(maxX),// top right
					list.get(y).get(maxX)); // top left
			
		}
	
		

		/**
		 * Creates the base
		 */
		
		Point3d b1 = new Point3d(0,0,0);
		Point3d b2 = new Point3d(0,maxY,0);
		Point3d b3 = new Point3d(maxX,maxY,0);
		Point3d b4 = new Point3d(maxX,0,0);
		solid.addFacet(b1,b2,b3,b4);
		return solid;
	}


	/**
	 * Print the points to the console
	 */
	public void printMatrix()
	{
		for(List<Point3d> row : list)
		{
			for(Point3d value : row)
				System.out.print("("+value+") ");
			System.out.println();
		}
	}
	
	

}
