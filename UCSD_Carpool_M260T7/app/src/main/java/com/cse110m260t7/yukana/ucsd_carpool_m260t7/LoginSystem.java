package com.cse110m260t7.yukana.ucsd_carpool_m260t7;

/**
 * Created by panzhengren on 16/2/13.
 */

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import java.util.*;

public class LoginSystem {

    public void createUser(String email, String password){
        Firebase ref = new Firebase("https://ucsdcarpool.firebaseio.com");
        ref.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
            public void onSuccess(Map<String, Object> result) {
                System.out.println("Successfully created user account with uid: " + result.get("uid"));
            }
            public void onError(FirebaseError firebaseError) {
                System.out.println("Error creating user!");
            }
        });
    }

    public void loginWithEmail(String email, String password){
        Firebase ref = new Firebase("https://ucsdcarpool.firebaseio.com");
        ref.authWithPassword(email, password, new Firebase.AuthResultHandler() {
            public void onAuthenticated(AuthData authData) {
                System.out.println("User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
            }
            public void onAuthenticationError(FirebaseError firebaseError) {
                System.out.println("Error logining in!");
            }
        });
    }

}
