/**
 * 
 */
package com.netflexity.ws.wmq;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.springframework.core.io.Resource;

/**
 * @author FedorovM
 *
 */
public final class JAXBUtil {
	
	/**
	 * JAXBContext
	 */
	private static JAXBContext jaxbContext = null;
	
	/**
	 * Classes the JAXBContext knows to deal with
	 */
	private static List<Class<? extends Object>> classes = new LinkedList<Class<? extends Object>>();
	
	/**
	 * Obtain a JAXBContext to use
	 * @param clazz class to be processes
	 * @return JAXBContext
	 * @throws JAXBException on error
	 */
	private static synchronized JAXBContext context(Class<? extends Object> clazz) throws JAXBException {
		if(jaxbContext == null){
			jaxbContext = JAXBContext.newInstance(new Class[]{clazz});
			classes.add(clazz);
		}
		else if(!classes.contains(clazz)){
			try{
				classes.add(clazz);
				jaxbContext = JAXBContext.newInstance(classes.toArray(new Class[classes.size()]));
			}
			catch(JAXBException e){
				classes.remove(clazz);
				throw e;
			}
		}
		return jaxbContext;
	}

	/**
	 * @param xml
	 * @param clazz
	 * @return
	 * @throws JAXBException
	 */
	public static Object unmarshall(Source xml, Class<? extends Object> clazz) throws JAXBException{
		//JAXBContext context = JAXBContext.newInstance(new Class[]{clazz});
		JAXBContext context = context(clazz);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		return unmarshaller.unmarshal(xml);
	}
	
    public static Object unmarshall(Resource resource, Class<? extends Object> clazz) throws Exception{
        try {
            JAXBElement jaxbElement = (JAXBElement)unmarshall(new StreamSource(resource.getInputStream()), clazz);
            return jaxbElement.getValue();
        } 
        catch (Throwable e) {
            return unmarshall(new StreamSource(resource.getInputStream()), clazz);
        }
    }
    

}
