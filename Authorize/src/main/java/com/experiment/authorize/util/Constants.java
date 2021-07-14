package com.experiment.authorize.util;

import org.springframework.beans.factory.annotation.Value;

public class Constants {

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

    @Value("${jwt.signing-key:JQTFbBIUE}")
    public static String JWT_SIGNING_KEY ="JQTFbBIUE";
}
