/*   Copyright (C) 2013-2014 Computer Sciences Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. */

package ezbake.ins.web;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.Properties;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ContextResolver;

import com.google.common.base.Charsets;
import com.google.common.base.Strings;

import ezbake.base.thrift.EzSecurityToken;
import ezbake.security.client.EzbakeSecurityClient;
import ezbakehelpers.ezconfigurationhelpers.webapplication.WebApplicationConfigurationHelper;

@Path("authenticate")
public class AuthenticateResource extends RegistrationResourceBase {
    @GET
    @Path("login")
    public Response login(@Context ContextResolver<ServiceClient> resource) throws URISyntaxException {
        System.setProperty("http.keepAlive", "false");
        ServiceClient serviceClient = getFromContext(resource);
        EzbakeSecurityClient securityClient = getSecurityClient(serviceClient);
        Properties configuration = serviceClient.getConfiguration();
        String redirectUrl;
        try {
            EzSecurityToken token = securityClient.fetchTokenForProxiedUser();
            String username = token.getTokenPrincipal().getPrincipal();
            String externalUrl = new WebApplicationConfigurationHelper(configuration).getExternalFacingDomain();
            if (!Strings.isNullOrEmpty(externalUrl)) {
                //This is for behind the ezFrontEnd
                redirectUrl = "https://" + externalUrl + "/" +
                        configuration.getProperty("ins.registration.external.url", "registration") +
                        "/index.html?access_token=" +
                        URLEncoder.encode(username, Charsets.UTF_8.name());
            } else {
                //This is for running locally (testing ezSecurity integration)
                redirectUrl = context.getBaseUri().toString().replace("/rest/", "?access_token=" +
                        URLEncoder.encode(username, Charsets.UTF_8.name()));
            }
            return Response.seeOther(new URI(redirectUrl)).build();
        } catch(Exception ex) {
            logger.error("Failed to get security token.", ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("profile")
    @Produces(MediaType.APPLICATION_JSON)
    public UserInfo getProfile(@Context ContextResolver<ServiceClient> resource) {
    	ServiceClient serviceClient = getFromContext(resource);
        return checkAuthToken(serviceClient);
    }
}
