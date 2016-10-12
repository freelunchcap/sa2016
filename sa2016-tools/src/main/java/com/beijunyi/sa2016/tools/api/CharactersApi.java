package com.beijunyi.sa2016.tools.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/api/characters")
public class CharactersApi {

  public CharactersApi() {
    System.currentTimeMillis();
  }

  @GET
  @Path("list")
  public String list() {
    return "Hello world";
  }

}
