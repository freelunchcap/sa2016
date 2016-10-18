package com.beijunyi.sa2016;

import javax.servlet.annotation.WebServlet;

import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;

@WebServlet(urlPatterns = "/api/*")
public class RestApiFilter extends HttpServletDispatcher {
}
