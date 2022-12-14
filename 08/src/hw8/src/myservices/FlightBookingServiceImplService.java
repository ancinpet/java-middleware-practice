
package myservices;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "FlightBookingServiceImplService", targetNamespace = "http://myservices/", wsdlLocation = "http://127.0.1.1:7001/hw8/FlightBookingServiceImplService?WSDL")
public class FlightBookingServiceImplService
    extends Service
{

    private final static URL FLIGHTBOOKINGSERVICEIMPLSERVICE_WSDL_LOCATION;
    private final static WebServiceException FLIGHTBOOKINGSERVICEIMPLSERVICE_EXCEPTION;
    private final static QName FLIGHTBOOKINGSERVICEIMPLSERVICE_QNAME = new QName("http://myservices/", "FlightBookingServiceImplService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://127.0.1.1:7001/hw8/FlightBookingServiceImplService?WSDL");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        FLIGHTBOOKINGSERVICEIMPLSERVICE_WSDL_LOCATION = url;
        FLIGHTBOOKINGSERVICEIMPLSERVICE_EXCEPTION = e;
    }

    public FlightBookingServiceImplService() {
        super(__getWsdlLocation(), FLIGHTBOOKINGSERVICEIMPLSERVICE_QNAME);
    }

    public FlightBookingServiceImplService(WebServiceFeature... features) {
        super(__getWsdlLocation(), FLIGHTBOOKINGSERVICEIMPLSERVICE_QNAME, features);
    }

    public FlightBookingServiceImplService(URL wsdlLocation) {
        super(wsdlLocation, FLIGHTBOOKINGSERVICEIMPLSERVICE_QNAME);
    }

    public FlightBookingServiceImplService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, FLIGHTBOOKINGSERVICEIMPLSERVICE_QNAME, features);
    }

    public FlightBookingServiceImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public FlightBookingServiceImplService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns FlightBookingService
     */
    @WebEndpoint(name = "FlightBookingServiceImplPort")
    public FlightBookingService getFlightBookingServiceImplPort() {
        return super.getPort(new QName("http://myservices/", "FlightBookingServiceImplPort"), FlightBookingService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns FlightBookingService
     */
    @WebEndpoint(name = "FlightBookingServiceImplPort")
    public FlightBookingService getFlightBookingServiceImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://myservices/", "FlightBookingServiceImplPort"), FlightBookingService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (FLIGHTBOOKINGSERVICEIMPLSERVICE_EXCEPTION!= null) {
            throw FLIGHTBOOKINGSERVICEIMPLSERVICE_EXCEPTION;
        }
        return FLIGHTBOOKINGSERVICEIMPLSERVICE_WSDL_LOCATION;
    }

}
