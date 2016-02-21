package com.example.jem.test;



import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;
import java.util.Objects;

/**
 * Created by yucheng on 2/15/16.
 */
public class LoginSystem {

    private String email;
    private String password;
    private final Firebase mRef = new Firebase("https://ucsdcarpool.firebaseio.com/web/data/user_info");

    // chekc user
    public  LoginSystem(String email, String password)
    {
        this.email = email;
        this.password = password;
    }

    public void loginWithEmail()
    {
        int error = 0;
        try{
            Validate(this.email);
        }catch (Exception Ex)
        {
            error = 1;
        }

        try{
            ValidatePwd(this.password);
        }catch (Exception Ex)
        {
            error = 1;
        }

        if (error == 0)
        {
            mRef.authWithPassword(this.email, this.password, new Firebase.AuthResultHandler() {
                @Override
                public void onAuthenticated(AuthData authData) {
                    //System.out.println("User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
                }

                @Override
                public void onAuthenticationError(FirebaseError firebaseError) {

                }
            });
        }else{

        }

    }

    public void SignUp()
    {
        int error = 0;
        try{
            Validate(this.email);
        }catch (Exception Ex)
        {
            error = 1;
        }

        try{
            ValidatePwd(this.password);
        }catch (Exception Ex)
        {
            error = 1;
        }

        if (error == 0)
        {
            mRef.createUser(this.email, this.password, new Firebase.ValueResultHandler<Map<String, Object>>() {
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
    }


    //helper method to check email
    private void Validate(String input){


    }

    //helper method to check password
    private void ValidatePwd(String input)
    {

    }
}
