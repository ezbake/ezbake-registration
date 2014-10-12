package ezbake.admin.web;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.thrift.TException;
import org.apache.thrift.TServiceClient;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ezbake.base.thrift.EzSecurityToken;
import ezbake.base.thrift.EzSecurityTokenException;
import ezbake.ins.thrift.gen.InternalNameService;
import ezbake.ins.thrift.gen.InternalNameServiceConstants;
import ezbake.security.client.EzbakeSecurityClient;
import ezbake.security.thrift.EzSecurityRegistration;
import ezbake.security.thrift.EzSecurityRegistrationConstants;
import ezbake.services.deploy.thrift.EzBakeServiceDeployer;
import ezbake.services.deploy.thrift.EzDeployServiceConstants;

public class AdminResourceBase {
    protected static Logger logger = LoggerFactory.getLogger(AdminResourceBase.class);

    protected EzSecurityToken getSecurityToken(ServiceClient resource) {
    	return getSecurityToken("", resource);
    }
    
    protected EzSecurityToken getSecurityToken(String securityId, ServiceClient resource)  {
        EzbakeSecurityClient securityClient = getSecurityClient(resource);
        
		EzSecurityToken token;
		try {
			token = (securityId == null || securityId.isEmpty()) ?
                    securityClient.fetchTokenForProxiedUser() : securityClient.fetchTokenForProxiedUser(securityId);
		} catch (EzSecurityTokenException e) {
			logger.error("General Security Token Exception", e);
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		
		if (!EzbakeSecurityClient.isEzAdmin(token)) {
			throw new WebApplicationException(Response.Status.FORBIDDEN);
		}
		
		return token;
					 
    }

    protected EzSecurityToken getRegistrationSecurityToken(ServiceClient resource) {
 		 return getSecurityToken(resource.getClientPool().getSecurityId(
                  EzSecurityRegistrationConstants.SERVICE_NAME), resource);
    }
    
    protected EzSecurityToken getDeployerSecurityToken(ServiceClient resource) {
         return getSecurityToken(resource.getClientPool().getSecurityId(
                 EzDeployServiceConstants.SERVICE_NAME), resource);
    }
    
    protected EzBakeServiceDeployer.Client getEzDeployerServiceClient(ServiceClient resource) throws TException {
        return resource.getClientPool().getClient(EzDeployServiceConstants.SERVICE_NAME,
                EzBakeServiceDeployer.Client.class);
    }
    
    protected EzSecurityRegistration.Client getEzSecurityRegistrationServiceClient(ServiceClient resource) throws TException {
        return resource.getClientPool().getClient(EzSecurityRegistrationConstants.SERVICE_NAME,
        		EzSecurityRegistration.Client.class);
    }
    
    protected InternalNameService.Client getINSServiceClient(ServiceClient resource) throws TException {
    	return resource.getClientPool().getClient(InternalNameServiceConstants.SERVICE_NAME,
                InternalNameService.Client.class);
    }
    
    protected void freeServiceClient(TServiceClient client, ServiceClient resource) {
        resource.getClientPool().returnToPool(client);
    }

    protected <T extends TServiceClient> T invalidateServiceClient(T client, ServiceClient resource, Exception ex) {
        if (ex.getClass().getName().equals(TTransportException.class.getName())) {
            return invalidateServiceClient(client, resource);
        } else {
            return client;
        }
    }

    protected <T extends TServiceClient> T invalidateServiceClient(T client, ServiceClient resource)  {
        resource.getClientPool().returnBrokenToPool(client);
        return null;
    }

    protected EzbakeSecurityClient getSecurityClient(ServiceClient resource) {
    	return resource.getSecurityClient();
    }
}