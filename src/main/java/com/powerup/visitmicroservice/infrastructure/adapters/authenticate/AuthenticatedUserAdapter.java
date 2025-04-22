package com.powerup.visitmicroservice.infrastructure.adapters.authenticate;

import com.powerup.visitmicroservice.domain.ports.out.AuthenticatedUserPort;
import com.powerup.visitmicroservice.infrastructure.exceptions.UserAuthenticationErrorException;
import com.powerup.visitmicroservice.infrastructure.security.jwt.DecodedJwtHolder;
import com.powerup.visitmicroservice.infrastructure.utils.constants.InfrastructureConstants;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticatedUserAdapter implements AuthenticatedUserPort {

    private DecodedJwtHolder getDecodedJwtHolder() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof DecodedJwtHolder decodedJwtHolder) {
            return decodedJwtHolder;
        }
        return null;
    }

    @Override
    public Long getCurrentUserId() {
        DecodedJwtHolder decodedJwtHolder = getDecodedJwtHolder();
        if (decodedJwtHolder != null) {
            return decodedJwtHolder.getUserId();
        }
        throw new UserAuthenticationErrorException(
                InfrastructureConstants.COULD_NOT_GET_AUTHENTICATED_USER_ID
        );
    }

    @Override
    public String getCurrentUsername() {
        DecodedJwtHolder decodedJwtHolder = getDecodedJwtHolder();
        if (decodedJwtHolder != null) {
            return decodedJwtHolder.getUsername();
        }
        throw new UserAuthenticationErrorException(
                InfrastructureConstants.COULD_NOT_GET_AUTHENTICATED_USERNAME
        );
    }
}