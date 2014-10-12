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

import java.util.Set;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ContextResolver;

import ezbake.ins.thrift.gen.InternalNameService;

@Path("/admin")
public class AdminResource extends RegistrationResourceBase {

    @GET
    @Path("/categories")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<String> getCategories(@Context ContextResolver<ServiceClient> resource) {
    	ServiceClient serviceClient = getFromContext(resource);
        InternalNameService.Client client = null;
        try {
            client = getINSServiceClient(serviceClient);
            return client.getCategories();
        } catch (Exception ex) {
            logger.error("Failed to get categories", ex);
            client = invalidateServiceClient(client, serviceClient);
            throw new RuntimeException("Failed to get categories", ex);
        } finally {
            freeServiceClient(client, serviceClient);
        }
    }

    @POST
    @Path("/categories")
    public Set<String> addCategory(String category,
    							   @Context ContextResolver<ServiceClient> resource) {
        InternalNameService.Client client = null;
        ServiceClient serviceClient = getFromContext(resource);
        checkAdmin(serviceClient);
        try {
            client = getINSServiceClient(serviceClient);
            client.addCategory(category, getSecurityToken(serviceClient));
            return client.getCategories();
        } catch (Exception ex) {
            logger.error("Failed to add category", ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        } finally {
        	freeServiceClient(client, serviceClient);
        }
    }

    @DELETE
    @Path("/categories/{category}")
    public Set<String> deleteCategory(@PathParam("category") String category,
    								  @Context ContextResolver<ServiceClient> resource) {
        InternalNameService.Client client = null;
        ServiceClient serviceClient = getFromContext(resource);
        checkAdmin(serviceClient);
        
        try {
        	client = getINSServiceClient(serviceClient);
            client.removeCategory(category, getSecurityToken(serviceClient));
            return client.getCategories();
        } catch (Exception ex) {
            logger.error("Failed to delete category", ex);
            client = invalidateServiceClient(client, serviceClient);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        } finally {
        	freeServiceClient(client, serviceClient);
        }
    }

    @GET
    @Path("/prefixes")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<String> getPrefixes(@Context ContextResolver<ServiceClient> resource) {
        InternalNameService.Client client = null;
        ServiceClient serviceClient = getFromContext(resource);
        try {
            client = getINSServiceClient(serviceClient);
            return client.getURIPrefixes();
        } catch (Exception ex) {
            logger.error("Failed to get prefixes", ex);
            client = invalidateServiceClient(client, serviceClient);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        } finally {
        	freeServiceClient(client, serviceClient);
        }
    }

    private void checkAdmin(ServiceClient serviceClient) {
        UserInfo user = checkAuthToken(serviceClient);
        if (!user.isAdmin) {
            throw new WebApplicationException(Response.Status.FORBIDDEN);
        }
    }
}
