

public class Point3d {
	private double x;
	private double y;
	private double z;
	


	Point3d(double x,double y,double z){
		this.x=x;
		this.y=y;
		this.z=z;
		
	}

	public String toString() { 
		return "" +x+" "+y+" "+z;
	}
	
	public static Point3d calcNormal(Point3d v1,Point3d v2,Point3d v3) {  //To calculate normal
		double x1=v1.x, y1=v1.y ,z1=v1.z;
		double x2=v2.x, y2=v2.y ,z2=v2.z;
		double x3=v3.x, y3=v3.y ,z3=v3.z;
		
		double a1=x2-x1;
		double b1=y2-y1;
		double c1=z2-z1;
		double a2=x3-x1;
		double b2=y3-y1;
		double c2=z3-z1;
	
        double a=b1*c2-b2*c1;
        double b=a2*c1-a1*c2;
        double c=a1*b2-b1*a2;
        
        double s=Math.sqrt((a*a)+(b*b)+(c*c)); // To find square root
        
        double i=a/s;
        double j=b/s;
        double k=c/s;
        
        Point3d normal=new Point3d(i,j,k);
        return normal;
	}
	/**
	 * Getter method for X
	 * @return
	 */
	public double getX() {
		return x;
	}

	/**
	 * Getter method for Y
	 * @return
	 */
	public double getY() {
		return y;
	}

	/**
	 * Getter method for Z
	 * @return
	 */
	public double getZ() {
		return z;
	}	
	
}




