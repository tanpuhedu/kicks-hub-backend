package com.tanpuh.kickshubservice.service.auth;

import com.nimbusds.jose.JOSEException;
import com.tanpuh.kickshubservice.dto.request.AuthenticationRequest;
import com.tanpuh.kickshubservice.dto.request.IntrospectRequest;
import com.tanpuh.kickshubservice.dto.request.LogoutRequest;
import com.tanpuh.kickshubservice.dto.request.RefreshRequest;
import com.tanpuh.kickshubservice.dto.response.AuthenticationResponse;
import com.tanpuh.kickshubservice.dto.response.IntrospectResponse;

import java.text.ParseException;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest request);
    IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException;
    void logout(LogoutRequest request) throws ParseException, JOSEException;
    AuthenticationResponse refreshToken(RefreshRequest request) throws ParseException, JOSEException;
}
