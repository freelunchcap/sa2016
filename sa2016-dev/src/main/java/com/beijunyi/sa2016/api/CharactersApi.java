package com.beijunyi.sa2016.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/api/characters")
public class CharactersApi {

  @GET
  @Path("list")
  public String list() {
    return "Hello world";
  }

}
