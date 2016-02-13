package com.cse110m260t7.yukana.ucsd_carpool_m260t7;

/**
 * Created by panzhengren on 16/2/13.
 */

import com.firebase.client.Firebase;

public class LoginSystem {

    public void loginWithEmail(String email, String password){
        Firebase ref = new Firebase("https://<YOUR-FIREBASE-APP>.firebaseio.com");
        ref.createUser("bobtony@firebase.com", "correcthorsebatterystaple", new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
                System.out.println("Successfully created user account with uid: " + result.get("uid"));
            }
            @Override
            public void onError(FirebaseError firebaseError) {
                // there was an error
            }
        });
    }

    public void createUser(String email, String password){

    }
}
