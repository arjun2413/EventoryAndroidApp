package com.spuds.eventapp.Firebase;

/**
 * Created by Arjun on 5/5/16.
 */

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.spuds.eventapp.ChangePassword.ChangePasswordForm;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Arjun on 5/5/16.
 */
public class AccountFirebase {
    private int threadCheck = 0;
    //private Query queryRef;
    String token;

    public AccountFirebase() {}

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
                        map.put("name", name);
                        map.put("email", email);
                        map.put("description", "");
                        map.put("notification_toggle", "true");
                        map.put("number_following", "0");
                        map.put("number_hosting", "0");
                        map.put("picture", "");
                        map.put("registration_id", token);


                        ref.child("users").child(String.valueOf(result.get("uid"))).setValue(map);

                        UserFirebase.uId = String.valueOf(result.get("uid"));
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
    public void logIn(final String email, String password) {
        status = 0;
        final Firebase ref = new Firebase("https://eventory.firebaseio.com");

        ref.authWithPassword(email, password, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {

                UserFirebase.uId = authData.getUid();

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
        threadCheck = 0;
        Log.v("email", form.getEmail());
        Firebase ref = new Firebase("https://eventory.firebaseio.com");
        ref.changePassword(form.getEmail(), form.getCurrent(), form.getNext(), new Firebase.ResultHandler() {
            @Override
            public void onSuccess() {
                // password changed
                threadCheck = 1;
                System.out.println("Password Changed");
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                // error encountered
                threadCheck = 2;
                Log.v("AccountFirebase:CP:", firebaseError.getMessage());
            }
        });
    }
    public void resetPass(String email) {
        Firebase ref = new Firebase("https://eventory.firebaseio.com");
        ref.resetPassword(email, new Firebase.ResultHandler() {
            @Override
            public void onSuccess() {
                // password reset email sent
                System.out.println("Successfully sent email");
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                // error encountered
                Log.v("AccountFirebase", "ERROR Resetting Password");
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
    public void checkEmail(final String email, final TextView[] tArray) {
        threadCheck = 0;
        Firebase ref = new Firebase("https://eventory.firebaseio.com/users");
        Query queryRef = ref.orderByChild("Email").startAt(email).endAt(email);
        System.out.println(queryRef);
//        Firebase ref = new Firebase("https://dinosaur-facts.firebaseio.com/dinosaurs");
//        Query queryRef = ref.orderByKey();

        //https://www.firebase.com/docs/android/guide/retrieving-data.html
        queryRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    threadCheck = 1;
                    System.out.println("FOUND");
                }
                else {
                    threadCheck = 2;
                    if (tArray[0] != null) {
                        tArray[0].setText("That email is not signed up");
                    }
                    System.out.println("NOTFOUND");
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }


            // ....
        });


        //System.out.println(status + " | " + threadCheck);
        //return threadCheck;
    }
    public int getThreadCheck() {
        return threadCheck;
    }

    public void pushRegistrationId(String token) {
        final Firebase ref = new Firebase("https://eventory.firebaseio.com/users");


        Map<String, Object> map = new HashMap<String, Object>();
        map.put("registration_id", token);

        //Query queryRef = ref.orderByChild("email").equalTo(email);
        ref.child(UserFirebase.uId).updateChildren(map);

    }

}
