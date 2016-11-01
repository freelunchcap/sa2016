package com.beijunyi.sa2016.api;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import com.beijunyi.sa2016.assets.repositories.CharacterRepo;

import static com.beijunyi.sa2016.models.CharacterView.view;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.ok;

@Path("/api/characters")
public class CharactersApi {

  private final CharacterRepo repo;

  @Inject
  public CharactersApi(CharacterRepo repo) {
    this.repo = repo;
  }

  @GET
  @Path("list")
  @Nonnull
  @Produces(APPLICATION_JSON)
  public Response list(@Nullable @QueryParam("start") String start,
                        @Nullable @QueryParam("dir") String dir,
                        @Nullable @QueryParam("max") Integer max) {
    return ok(view(repo.list(start, dir, max))).build();
  }

  @GET
  @Path("animations/{id}")
  @Nonnull
  @Produces(APPLICATION_JSON)
  public Response animation(@PathParam("id") String id) {
    return ok(view(repo.get(id))).build();
  }


}
