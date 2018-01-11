package org.example.route;

import org.apache.camel.builder.RouteBuilder;

public class soapDemoRB extends RouteBuilder {
	    
	
	public void configure() {
	    	
	    	from("cxf:bean:soapServiceEndPoint")
			.routeId("SoapServiceRoute")
			.doTry()
			.choice()
				.when(simple("${in.header.operationName} == 'order'"))
					.log("Endpoint  found")
					.to("seda:incomingOrders")
					.transform().constant("Order Received").endChoice()
			.end();
	    	
	    	
	    	from("seda:incomingOrders")
	    	.to("mock:end").end();
	      
	    }

}

