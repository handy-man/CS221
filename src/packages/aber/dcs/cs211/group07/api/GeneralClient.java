package aber.dcs.cs211.group07.api;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class GeneralClient {

	protected WebResource webResource;
	protected Client client;
	// See the Server Directory Client Example
	protected ServerDirectoryClient serverDirectoryClient;

	public GeneralClient() {
		ClientConfig config = new DefaultClientConfig();
		client = Client.create(config);
		// Set up some timeouts so the application wont hang if the network
		// is down or if the other server is unreachable
		client.setConnectTimeout(5000);
		client.setReadTimeout(10000);
		serverDirectoryClient = new ServerDirectoryClient();
	}
	
	/**
	 * Returns the service url of a specified server
	 * 
	 * @param serverNumber - the number of the server
	 * @return service url
	 * @throws UniformInterfaceException
	 */
	private WebResource getServerResource(Integer serverNumber)
			throws UniformInterfaceException {
		return client.resource(serverDirectoryClient.getServiceUrlRoot(serverNumber));
	}
}
