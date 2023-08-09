package com.inikitagricenko.endpoint;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.inikitagricenko.demosam.DemosamApplication;

public class PersonHandler implements RequestHandler<AwsProxyRequest, AwsProxyResponse> {
	private static SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;
		static {
			try {
				handler = SpringBootLambdaContainerHandler.getAwsProxyHandler(DemosamApplication.class);
			} catch (ContainerInitializationException exception) {
				throw new RuntimeException("Unable to load spring boot application", exception);
			}
		}

	@Override
	public AwsProxyResponse handleRequest(AwsProxyRequest request, Context context) {
		return handler.proxy(request, context);
	}

}
