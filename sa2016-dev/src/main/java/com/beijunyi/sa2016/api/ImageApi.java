package com.beijunyi.sa2016.api;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import com.beijunyi.sa2016.assets.repositories.ImageRepo;

@Path("/api/image")
@Singleton
public class ImageApi {

  private final ImageRepo repo;

  @Inject
  public ImageApi(ImageRepo repo) {
    this.repo = repo;
  }

  @GET
  @Path("list")
  public String list(@QueryParam("start") Integer start, @QueryParam("limit") Integer limit) {
    return "Hello world";
  }

}
