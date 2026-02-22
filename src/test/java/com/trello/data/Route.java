package com.trello.data;

/**
 * Holds the base URI and all API endpoint path segments.
 * Used by all API classes to build request URLs.
 */
public class Route {

    public static String PROD_URI    = "https://api.trello.com/1/";
    public static String BOARDS      = "boards";
    public static String LISTS       = "lists";
    public static String CARDS       = "cards";
    public static String ATTACHMENTS = "attachments";
    public static String MEMBERS     = "members";
}
