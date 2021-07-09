package com.experiment.authorize.util;

import org.springframework.beans.factory.annotation.Value;

public class Constants {

    public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 10*60;
    public static final long REFRESH_TOKEN_VALIDITY_SECONDS = 15*60;
    public static final String SIGNING_KEY = "devglan123r";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
