
/* Name -Gagandeep Singh
 * Lab instructor - Raviraj Vaghani
 * Section - CST8132_311
 */
import java.io.FileWriter;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class Solid {
private String name;
private List<Facet> facets=new ArrayList<>();


Solid() {
	
}
Solid(String name){
	this.name=name;
}
public boolean addFacet(Facet f1)
{
  facets.add(f1);
	
	return true;
	
}

public boolean addFacet(Point3d... vertex) {
	if(vertex.length<3) {
		return false;
	}
	for(int i=1;i<vertex.length-1;i++) {
		Facet f=new Facet(vertex[0],vertex[i],vertex[i+1]);
		addFacet(f);
	}
		return true;
	

}

public String toString()
{
	String sum= "";
	for (Facet f:facets)
	{
		sum +=f;
	}
	
	return "solid "+ name+sum+"\nendsolid "+name;
}

public void toTextFile(Path path)throws IOException {
	FileWriter writer = new FileWriter(path.toString()); 
	writer.write(this.toString());
    writer.close();
}
}

