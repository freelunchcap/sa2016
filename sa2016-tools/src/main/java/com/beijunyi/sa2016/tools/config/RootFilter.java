package com.beijunyi.sa2016.tools.config;

import javax.servlet.annotation.WebFilter;

import com.google.inject.servlet.GuiceFilter;

@WebFilter(urlPatterns = "/*")
public class RootFilter extends GuiceFilter {
}
