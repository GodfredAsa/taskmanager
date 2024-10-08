package io.taskmanager.constant;

public class SecurityConstant {
    public static final long EXPIRATION_TIME = 432_000_000; // 5 days expressed in milliseconds
    public static final String TOKEN_CANNOT_BE_VERIFIED = "Token cannot be verified";
    public static final String GET_ARRAYS_LLC = "Get Arrays, LLC";
    public static final String GET_ARRAYS_ADMINISTRATION = "User Management Portal";
    public static final String AUTHORITIES = "authorities";
    public static final String FORBIDDEN_MESSAGE = "You need to log in to access this page";
    public static final String ACCESS_DENIED_MESSAGE = "You do not have permission to access this page";

    public static final String OPTIONS_HTTP_METHOD = "OPTIONS";

    public static final long TOKEN_EXPIRATION_Ms = 60 * 60 * 1000 * 24; // 1 day
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final int JWT_TOKEN_EXTRACTION = 7;
    public static final String AUTHORIZATION_HEADER = "Authorization";
}
