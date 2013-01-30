package aber.dcs.cs211.group07.api;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

/**
 * This simple HTTP client accesses the Server Directory to obtain the
 * service root URL's for servers.
 * @author James Bowcott
 */
public class ServerDirectoryClient {
    protected Client client;
    protected WebResource resource;
    private Servers server;
 
    public ServerDirectoryClient() {
    	System.out.println("here");
	ClientConfig config = new DefaultClientConfig();
	client = Client.create(config);
	// Set up some timeouts so the application wont hang if the network
	// is down or if the server is unreachable
	client.setConnectTimeout(5000);
	client.setReadTimeout(10000);
	resource = client.resource("http://monstermash.digitdex.com/directory");
    }
 
    public ArrayList<Servers> getAllServers() throws JSONException {
    	
    	ArrayList<Servers> serverList = new ArrayList<Servers>();
    	
    	String body = resource.path("all").get(String.class);
    	JSONArray jsonArray = new JSONArray(body);
    	
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			int serverID = jsonObject.getInt("serverNumber");
			String httpRoot = jsonObject.getString("httpRoot");
			String serviceRoot = jsonObject.getString("serviceRoot");
			Servers server = new Servers(serverID,httpRoot,serviceRoot);
			serverList.add(server);
		}
    	
    	return serverList;
    	
    }
    
    public String getServiceUriRoot(Integer serverNumber)
	    throws UniformInterfaceException {
	// For example, this will be http://monstermash.digitdex.com/directory/3/service for server 3
	return resource.path(serverNumber.toString()).path("service").get(String.class);
    }
 
    public void close() {
	client.destroy();
    }
 
}
