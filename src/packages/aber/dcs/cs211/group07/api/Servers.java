package aber.dcs.cs211.group07.api;

/**
 * Simple class to hold servers
 * 
 * @author Dan Cornwell
 *
 */

public class Servers {

	public int serverNumber;
	public String httpRoot;
	public String serviceRoot;
	
	public Servers(int serverNumber,String httpRoot,String serviceRoot) {
		this.serverNumber=serverNumber;
		this.httpRoot=httpRoot;
		this.serviceRoot=serviceRoot;
	}
	
}
