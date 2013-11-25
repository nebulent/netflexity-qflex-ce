package com.netflexity.ws.qflex;


import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

import com.netflexity.qflex.business.service.QflexServiceException;

/**
 * @author MAX
 *
 */
@SoapFault (faultCode=FaultCode.SERVER)
public class QflexWebServiceException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * @param msg
     * @param t
     */
    public QflexWebServiceException(String msg, Throwable t) {
        super(msg, t);
    }

    /**
     * @param msg
     */
    public QflexWebServiceException(String msg) {
        super(msg);
    }
    
    /**
     * @param msg
     */
    public QflexWebServiceException(QflexServiceException qse) {
        super(qse);
    }
}
