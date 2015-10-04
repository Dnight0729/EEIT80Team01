package item.rest;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
@ApplicationPath("/rest")
public class RESTServiceConfig extends ResourceConfig {
	public RESTServiceConfig(){
		packages("item.rest","items.model","items.model.dao");
	}
}
