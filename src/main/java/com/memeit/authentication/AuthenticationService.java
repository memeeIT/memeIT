package com.memeit.authentication;


import com.memeit.user.User;

public interface AuthenticationService {

    User signInAndReturnJWT(User signInRequest);

    boolean isLoggedUserIsAdmin();

    boolean isUserLogged();
}

