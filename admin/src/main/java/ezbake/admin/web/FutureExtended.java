package ezbake.admin.web;

import java.io.Serializable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.google.common.base.Strings;
import org.slf4j.Logger;

public class FutureExtended<T extends Serializable> {
	final Future<T> future;
	String serviceId;
	String errors;
	
	public FutureExtended(final Future<T> aFuture) {
		this.future = aFuture;
		this.errors = getErrors();
	}
	
	public FutureExtended(final Future<T> aFuture, final String serviceId) {
		this(aFuture);
		setServiceId(serviceId);
	}
	
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	
	public String getServiceId() {
		return serviceId;
	}
	
	public boolean isError() {
		return !Strings.isNullOrEmpty(errors);
	}
	
	public String getErrors() {
		return errors;
	}
	
	public Future<T> getFuture() {
		return future;
	}
	
	/**
	 * Run this function and the end of task to set any errors
	 */
	public void setErrors(Logger logger) {
		try {
			if(future.isDone()) { // since get is blocking, we need to make sure task completes first
				future.get();
			} else {
				errors = "";
			}
		} catch (InterruptedException | ExecutionException e) {
			errors = String.format("Errors:%s", e.getMessage());
            logger.error("Async deploy of " + serviceId + " failed", e);
		}
	}
	
}
