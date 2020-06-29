package com.homekeep.rooms.items.functions;

import java.util.*;
import java.util.logging.Level;

import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;
import org.springframework.cloud.function.adapter.azure.AzureSpringBootRequestHandler;

/**
 * Azure Functions with HTTP Trigger.
 */
public class DeleteRoomItemFunction extends AzureSpringBootRequestHandler<Long, Boolean> {
    @FunctionName("delete-room-item")
    public HttpResponseMessage run(
            @HttpTrigger(name = "req", route = "room-items/{id:int}", methods = {HttpMethod.DELETE}, authLevel = AuthorizationLevel.FUNCTION) HttpRequestMessage<Optional<String>> request,
            @BindingName("id") int id,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");
        try {
            return request.createResponseBuilder(HttpStatus.OK).body(String.valueOf(handleRequest((long) id, context))).build();
        } catch (Exception e) {
            context.getLogger().log(Level.ALL, e.toString());
            return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString()).build();
        }
    }
}
