package com.beijunyi.sa2016.api;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import com.beijunyi.sa2016.assets.Image;
import com.beijunyi.sa2016.assets.repositories.ImageRepo;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.status;

@Path("/api/images")
@Singleton
public class ImageApi {

  private final ImageRepo repo;

  @Inject
  public ImageApi(ImageRepo repo) {
    this.repo = repo;
  }

  @GET
  @Path("{id}.{format}")
  @Nonnull
  @Produces("image/*")
  public Response get(@PathParam("id") String id, @PathParam("format") String format) {
    Image image = repo.get(id);
    if(image == null || !format.equalsIgnoreCase(image.getFormat()))
      return status(NOT_FOUND).build();
    return ok(image.getData()).build();
  }

  @GET
  @Path("list")
  @Nonnull
  @Produces(APPLICATION_JSON)
  public Response list(@Nullable @QueryParam("start") String start,
                          @Nullable @QueryParam("dir") String dir,
                          @Nullable @QueryParam("max") Integer max) {
    return ok(repo.list(start, dir, max)).build();
  }

}
