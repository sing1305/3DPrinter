

public class Facet {
	
		private Point3d normal;
		private Point3d v1;
		private Point3d v2;
		private Point3d v3;

		Facet(Point3d v1, Point3d v2, Point3d v3) {
			this.v1 = v1;
			this.v2 = v2;
			this.v3 = v3;
			this.normal = Point3d.calcNormal(v1, v2, v3);
		}

		public String toString() {

			return ("\nfacet normal " + normal + "\n outer loop\n" + "   vertex  " + v1 + "\n" + "   vertex  " +v2 + "\n" + "   vertex  " + v3
					+ "\n  endloop\nendfacet");
		}
		 
	}
