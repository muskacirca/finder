package org.muskacirca.finder.rs;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

/**
 * Created by muskacirca on 25/11/2014.
 */
@ApplicationPath("rest")
public class FinderRestApplication extends ResourceConfig {

    public FinderRestApplication() {
        packages("org.muskacirca.finder.rs");
    }
}
