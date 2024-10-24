package adri.suivi_courrier.service;

import adri.suivi_courrier.request.SigninRequest;
import adri.suivi_courrier.request.SignupRequest;
import adri.suivi_courrier.response.JwtAuthentificationResponse;

public interface AuthenticationService {
    JwtAuthentificationResponse signup(SignupRequest request);

    JwtAuthentificationResponse signin(SigninRequest request);
    
    JwtAuthentificationResponse  signinadmin(SigninRequest request);
}