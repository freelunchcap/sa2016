package com.beijunyi.sa2016.api;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.beijunyi.sa2016.assets.repositories.ImageRepo;

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
  public Response list(@Nullable @QueryParam("previous") String previous, @Nullable @QueryParam("limit") Integer limit) {
    return Response.ok(repo.list(previous, limit)).build();
  }

}
