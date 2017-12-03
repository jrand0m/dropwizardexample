package com.jrandom.dropwizard.example.resources;

import com.jrandom.dropwizard.example.models.User;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by mike on 12/3/17.
 */
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserListResource {

    private static LinkedHashMap<Long, User> database = new LinkedHashMap<>();
    private static AtomicLong counter = new AtomicLong(0);

    public UserListResource() {}

    @GET
    public Response getUsers() {
        Response response;
        ArrayList<User> entity = new ArrayList<>(database.values());
        response = Response.ok(entity).build();
        return response;
    }

    @GET
    @Path("/{id}/")
    public Response getUser(@PathParam("id")  Long id){
        Response response;
        if (database.containsKey(id)){
            User user = database.get(id);
            response = Response.ok(user).build();
        } else {
            response = Response.status(Response.Status.NOT_FOUND).build();
        }
        return response;
    }

    @POST
    public Response createUser(@NotNull @Valid User user) {
        long id = counter.addAndGet(1);
        user.setId(id);
        database.put(id, user);
        return Response.ok(user).build();
    }

    @PUT
    @Path("/{id}/")
    public Response updateUser(@PathParam("id") Long id, @NotNull @Valid User user) {
        if(database.containsKey(id)){
            user.setId(id);
            database.put(id, user);
            return Response.ok(user).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}/")
    public Response deleteUser(@PathParam("id") Long id) {
        if(database.containsKey(id)) {
            database.remove(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
