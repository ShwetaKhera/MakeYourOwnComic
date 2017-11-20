package com.example.shweta.makeyourowncomic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.IOException;


public class MainActivity extends AppCompatActivity  implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener{
    private SignInButton signInButton;

    static GoogleSignInAccount acct;
    //Signing Options
    private GoogleSignInOptions gso;

    //google api client
    private GoogleApiClient mGoogleApiClient;

    //Signin constant to check the activity result
    private int RC_SIGN_IN = 100;

    //Image Loader
  //  NetworkImageView profilePhoto;
  //  private ImageView imageViewRound;

    static ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing Views

   //     profilePhoto = (NetworkImageView) findViewById(R.id.profileImage);
   //     imageViewRound=(ImageView)findViewById(R.id.imageView_round);

        //Initializing google signin option
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        //Initializing signinbutton
        signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_WIDE);
        signInButton.setScopes(gso.getScopeArray());

        //Initializing google api client
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


        //Setting onclick listener to signing button
        signInButton.setOnClickListener(this);
    }


    //This function will option signing intent
    private void signIn() {
        //Creating an intent
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);

        //Starting intent for result
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode != RESULT_CANCELED && data != null) {
            //If signin
            if (requestCode == RC_SIGN_IN) {

                GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                //Calling a new function to handle signin
                try {
                    handleSignInResult(result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    //After the signing we are calling this function
    private void handleSignInResult(GoogleSignInResult result) throws IOException {
        //If the login succeed
        if (result.isSuccess()) {
            //Getting google account
            acct = result.getSignInAccount();

            //Displaying name and email

            //Initializing image loader
            imageLoader = CustomVolleyRequest.getInstance(this.getApplicationContext()).getImageLoader();

          /*  imageLoader.get(acct.getPhotoUrl().toString(),
                    new ImageLoader.ImageListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }

                        @Override
                        public void onResponse(ImageLoader.ImageContainer response, boolean arg1) {
                            if (response.getBitmap() != null) {
                                // load image into imageview
                                imageViewRound.setImageBitmap(response.getBitmap());

                            }
                        }
                    });
            //Loading image
            profilePhoto.setImageUrl(acct.getPhotoUrl().toString(), imageLoader);*/

            Intent i = new Intent(getApplicationContext(),Options.class);
            startActivity(i);

        } else {
            //If login fails
            Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {
        if (v == signInButton) {
            //Calling signin
            signIn();
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

}

