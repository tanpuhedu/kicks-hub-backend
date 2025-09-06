package com.tanpuh.kickshubservice.config;

import com.nimbusds.jose.JOSEException;
import com.tanpuh.kickshubservice.dto.request.IntrospectRequest;
import com.tanpuh.kickshubservice.dto.response.IntrospectResponse;
import com.tanpuh.kickshubservice.service.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.text.ParseException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CustomJwtDecoder implements JwtDecoder {
    @Value("${jwt.signer-key}")
    private String SIGNER_KEY;

    private final AuthenticationService authenticationService;
    private NimbusJwtDecoder nimbusJwtDecoder = null;

    @Override
    public Jwt decode(String token) throws JwtException {
        try {
            IntrospectResponse response = authenticationService.introspect(new IntrospectRequest(token));

            if (!response.isValid())
                throw new JwtException("Invalid token");
        } catch (JOSEException | ParseException e) {
            throw new JwtException(e.getMessage());
        }

        if (Objects.isNull(nimbusJwtDecoder)) {
            nimbusJwtDecoder = NimbusJwtDecoder
                    .withSecretKey(new SecretKeySpec(SIGNER_KEY.getBytes(), "HS512"))
                    .macAlgorithm(MacAlgorithm.HS512)
                    .build();
        }

        return nimbusJwtDecoder.decode(token);
    }
}
