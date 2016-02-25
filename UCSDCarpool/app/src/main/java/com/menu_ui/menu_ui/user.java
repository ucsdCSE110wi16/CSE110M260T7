package com.menu_ui.menu_ui;

/**
 * Created by yucheng on 2/25/16.
 */
public class user {

    private String user_name;
    private String user_email;
    private String user_password;

    public user(String name, String email, String password)
    {
        this.user_name = name;
        this.user_email = email;
        this.user_password = password;
    }


    public String getUser_name() {
        return user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public String getUser_password() {
        return user_password;
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

        if(user_password == "")
        {
            return false;
        }

        return true;
    }
}
