package com.beijunyi.sa2016.api;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import com.beijunyi.sa2016.assets.repositories.ImageRepo;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/api/images")
@Singleton
public class ImageApi {

  private final ImageRepo repo;

  @Inject
  public ImageApi(ImageRepo repo) {
    this.repo = repo;
  }

  @GET
  @Path("total")
  @Nonnull
  public Response total() {
    return Response.ok(repo.count()).build();
  }

  @GET
  @Path("list")
  @Nonnull
  @Produces(APPLICATION_JSON)
  public Response list(@Nullable @QueryParam("start") String start,
                          @Nullable @QueryParam("dir") String dir,
                          @Nullable @QueryParam("limit") Integer max) {
    return Response.ok(repo.list(start, dir, max)).build();
  }

}
