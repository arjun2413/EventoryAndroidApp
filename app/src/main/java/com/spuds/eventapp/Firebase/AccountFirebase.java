package com.spuds.eventapp.Firebase;

/**
 * Created by Arjun on 5/5/16.
 */

import android.util.Log;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.spuds.eventapp.ChangePassword.ChangePasswordForm;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Arjun on 5/5/16.
 */
public class AccountFirebase {
    public void createAccount(final String email, String password, final String name) {


        final Firebase ref = new Firebase("https://eventory.firebaseio.com");
        ref.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>()

                {
                    @Override
                    public void onSuccess (Map < String, Object > result){
                        System.out.println("Successfully created user account with uid: " + result.get("uid"));
                        // Authentication just completed successfully smile emoticon
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("provider", ref.getAuth().getProvider());
                        map.put("Name", name);
                        map.put("Email", email);
                        ref.child("users").child(ref.getAuth().getUid()).setValue(map);
                    }

                    @Override
                    public void onError(FirebaseError firebaseError) {
                        // error encountered
                        Log.v("AccountFirebase:CA:", firebaseError.getMessage());
                    }

                }


        );
    }
    public int status = 0;
    public void logIn(String email, String password) {

        final Firebase ref = new Firebase("https://eventory.firebaseio.com");

        ref.authWithPassword(email, password, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                status = 1;
                // Authentication just completed successfully smile emoticon

            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                Log.v("AccountFirebase", "ERROR Logging In");
                status = 2;
            }

        });
    }

    public void changePass(ChangePasswordForm form) {
        Log.v("email", form.getEmail());
        Firebase ref = new Firebase("https://eventory.firebaseio.com");
        ref.changePassword(form.getEmail(), form.getCurrent(), form.getNext(), new Firebase.ResultHandler() {
            @Override
            public void onSuccess() {
                // password changed
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                // error encountered
                Log.v("AccountFirebase:CP:", firebaseError.getMessage());
            }
        });
    }
    void resetPass() {
        Firebase ref = new Firebase("https://eventory.firebaseio.com");
        ref.resetPassword("bobtony@firebase.com", new Firebase.ResultHandler() {
            @Override
            public void onSuccess() {
                // password reset email sent
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                // error encountered
            }
        });
    }
    public void removingAccount() {
        Firebase ref = new Firebase("https://eventory.firebaseio.com");
        ref.removeUser("bobtony@firebase.com", "correcthorsebatterystaple", new Firebase.ResultHandler() {
            @Override
            public void onSuccess() {
                // user removed
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                // error encountered
            }
        });
    }

    public String getUserEmail(){
        Firebase ref = new Firebase("https://eventory.firebaseio.com");
        String data = (String) ref.getAuth().getProviderData().get("email");
        return data;
    }
}