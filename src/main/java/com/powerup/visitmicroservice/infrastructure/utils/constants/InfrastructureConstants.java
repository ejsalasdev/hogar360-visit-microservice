package com.powerup.visitmicroservice.infrastructure.utils.constants;

public final class InfrastructureConstants {

    public static final String COULD_NOT_GET_AUTHENTICATED_USER_ID = "Could not retrieve the ID of the authenticated user.";
    public static final String COULD_NOT_GET_AUTHENTICATED_USERNAME = "Could not retrieve the username of the authenticated user.";
    public static final String INVALID_TOKEN_RESPONSE = "Invalid Token, not authorized";

    private InfrastructureConstants() {
        throw new IllegalStateException("Utility Class");
    }
}
