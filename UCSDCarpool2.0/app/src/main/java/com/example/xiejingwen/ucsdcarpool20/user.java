package com.example.xiejingwen.ucsdcarpool20;

/**
 * Created by Yukana on 16/2/25.
 */

public class user {

    private String user_name;
    private String user_email;

    public user(String name, String email)
    {
        this.user_name = name;
        this.user_email = email;
    }


    public String getUser_name() {
        return user_name;
    }

    public String getUser_email() {
        return user_email;
    }


    public boolean validate()
    {
        if(user_name == "")
        {
            return false;
        }

        if(user_email == "")
        {
            return false;
        }

        return true;
    }
}
