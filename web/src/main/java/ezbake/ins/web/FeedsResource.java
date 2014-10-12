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

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ContextResolver;

import ezbake.ins.thrift.gen.FeedPipeline;
import ezbake.ins.thrift.gen.InternalNameService;

@Path("feed")
public class FeedsResource extends RegistrationResourceBase {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Set<FeedPipeline> getFeeds(@Context ContextResolver<ServiceClient> resource) {
    	ServiceClient serviceClient = getFromContext(resource);
        InternalNameService.Client client = null;
        try {
            client = getINSServiceClient(serviceClient);
            return client.getPipelineFeeds();
        } catch(Exception ex) {
            logger.error("Failed to get feeds", ex);
            client = invalidateServiceClient(client, serviceClient);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        } finally {
        	freeServiceClient(client, serviceClient);
        }
   }
}
