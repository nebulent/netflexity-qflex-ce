package com.netflexity.qflex.agent;

import com.netflexity.software.service.wmq._1_0.WMQFaultMessage;

/**
 * @author mfedorov
 *
 */
public interface CallProcessor<T> {

	/**
	 * @param request
	 * @return
	 * @throws WMQFaultMessage
	 */
	public T processCall(T request);
}
