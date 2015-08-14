package sg.com.fbs.core.businfra.businessdelegate;

import sg.com.fbs.core.businfra.facade.ServiceLocator;

/**
 * @author Frank Xu
 *
 */
public class BusinessDelegate {

	public static Object getService(String serviceID){
		return ServiceLocator.getService(serviceID);
	}
}
