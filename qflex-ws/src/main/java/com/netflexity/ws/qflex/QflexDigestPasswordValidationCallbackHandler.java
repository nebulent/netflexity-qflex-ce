/*
 *  2008 Netflexity, Ltd. All Rights Reserved.
 *
 * CONFIDENTIAL BUSINESS INFORMATION
 *
 * THIS PROGRAM IS PROPRIETARY INFORMATION OF NETFLEXITY, LTD. AND
 * IS NOT TO BE COPIED, REPRODUCED, LENT, OR DISPOSED OF, NOR USED FOR ANY
 * OTHER PURPOSE, UNLESS THE WRITTEN PERMISSION FROM THE STATED ABOVE CORPORATION
 * IS GIVEN.
 */
package com.netflexity.ws.qflex;

import java.io.IOException;
import java.util.List;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.ws.security.WSPasswordCallback;
import org.springframework.ws.soap.security.callback.AbstractCallbackHandler;

import com.netflexity.qflex.business.service.QflexServiceImpl;
import com.netflexity.qflex.domain.Principals;
import com.sun.xml.wss.impl.callback.PasswordValidationCallback;

/**
 * @author MAX
 *
 */
public class QflexDigestPasswordValidationCallbackHandler extends AbstractCallbackHandler {

    private final QflexServiceImpl qflexService;
    
    /**
     * @param pflexService
     */
    public QflexDigestPasswordValidationCallbackHandler(final QflexServiceImpl qflexService) {
        this.qflexService = qflexService;
    }
    
	/* (non-Javadoc)
     * @see org.springframework.ws.soap.security.xwss.callback.AbstractCallbackHandler#handleInternal(javax.security.auth.callback.Callback)
     */
    protected void handleInternal(Callback callback) throws IOException, UnsupportedCallbackException {
        if (callback instanceof PasswordValidationCallback) {
            PasswordValidationCallback passwordCallback = (PasswordValidationCallback) callback;
            if (passwordCallback.getRequest() instanceof PasswordValidationCallback.DigestPasswordRequest) {
                PasswordValidationCallback.DigestPasswordRequest digestPasswordRequest =
                        (PasswordValidationCallback.DigestPasswordRequest) passwordCallback.getRequest();
                String username = digestPasswordRequest.getUsername();
                List<Principals> principals = qflexService.getPrincipalDao().findPrincipalBy(username);
                for (Principals principal : principals) {
                	digestPasswordRequest.setPassword(principal.getPassword());
				}
                passwordCallback.setValidator(new PasswordValidationCallback.DigestPasswordValidator());
            }
        }
        else if(callback instanceof WSPasswordCallback){
        	WSPasswordCallback passwordCallback = (WSPasswordCallback)callback;
        	String username = passwordCallback.getIdentifer();
            List<Principals> principals = qflexService.getPrincipalDao().findPrincipalBy(username);
            for (Principals principal : principals) {
            	passwordCallback.setPassword(principal.getPassword());
			}
        }
        else {
            throw new UnsupportedCallbackException(callback);
        }
    }
}
