package LogIn;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

/**
 * Created by yucheng on 2/21/16.
 */
public class LogIn {

    private Firebase mRef = new Firebase("https://ucsdcarpool.firebaseio.com/web/data/user_info");;
    private  String email;
    private  String pwd;

    public LogIn(String email, String pwd)
    {
         this.email = email;
        this.pwd = pwd;
    }

    public void logIn()
    {
        mRef.authWithPassword(this.email, this.pwd, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                //System.out.println("User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {

            }
        });
    }
}
