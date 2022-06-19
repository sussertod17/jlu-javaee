package bs6;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;



@Path("/getname")
@Stateless
public class getName {

	@EJB
	nameEJB nameEJB;
	
	
	@GET
	@Path("/get")
	@Produces("text/plain") 
	public String get(@QueryParam("Num") String num) {		
		
		return nameEJB.getName(num);
		//return num;
	}
	
	
	
}
