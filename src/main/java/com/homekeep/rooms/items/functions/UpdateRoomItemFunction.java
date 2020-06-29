package com.homekeep.rooms.items.functions;

import java.util.logging.Level;

import com.homekeep.rooms.items.dtos.RoomItemDto;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;
import org.springframework.cloud.function.adapter.azure.AzureSpringBootRequestHandler;

/**
 * Azure Functions with HTTP Trigger.
 */
public class UpdateRoomItemFunction extends AzureSpringBootRequestHandler<RoomItemDto, RoomItemDto> {
    @FunctionName("update-room-item")
    public HttpResponseMessage run(
            @HttpTrigger(name = "req", route = "room-items", methods = {HttpMethod.PUT}, authLevel = AuthorizationLevel.FUNCTION) HttpRequestMessage<RoomItemDto> request,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");
        try {
            return request.createResponseBuilder(HttpStatus.OK).body(handleRequest(request.getBody(), context)).build();
        } catch (Exception e) {
            context.getLogger().log(Level.ALL, e.toString());
            return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString()).build();
        }
    }
}
