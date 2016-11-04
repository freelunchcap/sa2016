package com.beijunyi.sa2016.api;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import com.beijunyi.sa2016.models.AnimationViewFactory;

import static com.beijunyi.sa2016.models.CharacterView.view;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.ok;

@Path("/api/animations")
public class AnimationsApi {

  private final AnimationViewFactory factory;

  @Inject
  public AnimationsApi(AnimationViewFactory factory) {
    this.factory = factory;
  }

  @GET
  @Path("{id}.json")
  @Nonnull
  @Produces(APPLICATION_JSON)
  public Response animation(@PathParam("id") String id) {
    return ok(factory.animationView(id)).build();
  }


}
