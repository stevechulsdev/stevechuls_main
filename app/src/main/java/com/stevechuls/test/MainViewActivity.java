package com.stevechuls.test;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.stevechuls.test.fragment.FragmentAdapter;

public class MainViewActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private FragmentAdapter fragmentAdapter;
    private ViewPager mViewPager;
    private FragmentManager manager;
    private NavigationView navigationView;

    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 9001;
    private FirebaseAuth mFirebaseAuth;
    private Button mLoginBtn;
    private Button mLogoutBtn;
    private TextView mAccountTextView;
    private ImageView mAccountImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navi_view);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        manager = getSupportFragmentManager();

        fragmentAdapter = new FragmentAdapter(manager);

        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(fragmentAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.navi_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);

        mAccountTextView = headerView.findViewById(R.id.account_text);
        mAccountImageView = headerView.findViewById(R.id.account_image);

        mLoginBtn = headerView.findViewById(R.id.google_login_btn);
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(intent, RC_SIGN_IN);
            }
        });

        mLogoutBtn = headerView.findViewById(R.id.google_logout_btn);
        mLogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFirebaseAuth.signOut();
                mGoogleSignInClient.signOut().addOnCompleteListener(MainViewActivity.this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        Toast.makeText(MainViewActivity.this, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();

                        mAccountImageView.setImageResource(R.mipmap.ic_launcher_round);
                        mAccountTextView.setText("로그인 해주세요.");

                        mLogoutBtn.setVisibility(View.GONE);
                        mLoginBtn.setVisibility(View.VISIBLE);
                    }
                });
            }
        });

        GoogleSignInOptions mGoogleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("941220560711-i7a918lkfp10mjf06ibi86c32f8h65kj.apps.googleusercontent.com")
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, mGoogleSignInOptions);

        mFirebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
//            setContentView(R.layout.navigation_view);
//            navigationView.setVisibility(View.VISIBLE);
            return true;
        }
//        else if(id == android.R.id.home)
//        {
//            finish();
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Log.e("ksc", "adsddsdssdsfsdfsdfsdfsdf========================= : " + item.getItemId());
//        if(item)
//        {
//
//        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.END);

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RC_SIGN_IN)
        {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try
            {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
                Toast.makeText(this, "account_getDisplayName : " + account.getDisplayName(), Toast.LENGTH_SHORT).show();
            }
            catch (ApiException e)
            {
                e.printStackTrace();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct)
    {
        Log.d("ksc", "firebaseAuthWithGoogle : " + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mFirebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(MainViewActivity.this, "로그인 되었습니다.", Toast.LENGTH_SHORT).show();

                            Log.d("ksc", "signInWithCredential:success");
                            FirebaseUser user = mFirebaseAuth.getCurrentUser();

                            SharedPreferences preferences = getSharedPreferences("userinfo", MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("name", user.getDisplayName());
                            editor.putString("email", user.getEmail());
                            editor.commit();

                            mAccountImageView.setImageURI(user.getPhotoUrl());
                            mAccountTextView.setText(user.getDisplayName()+"\n"+user.getEmail());

                            mLoginBtn.setVisibility(View.GONE);
                            mLogoutBtn.setVisibility(View.VISIBLE);
                        }
                        else
                        {
                            Log.d("ksc", "signInWithCredential:failure", task.getException());
                            Toast.makeText(MainViewActivity.this, "Authentication failed. ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser mCurrentUser = mFirebaseAuth.getCurrentUser();
        updateUI(mCurrentUser);
    }

    private void updateUI(FirebaseUser mCurrentUser)
    {
        if(mCurrentUser == null)
        {
            mLoginBtn.setVisibility(View.VISIBLE);
            mLogoutBtn.setVisibility(View.GONE);
            mAccountTextView.setText("로그인 해주세요.");
            mAccountImageView.setImageResource(R.mipmap.ic_launcher_round);
        }
        else
        {
            SharedPreferences preferences = getSharedPreferences("userinfo", MODE_PRIVATE);
            String name = preferences.getString("name", "");
            String email = preferences.getString("email", "");
            mLoginBtn.setVisibility(View.GONE);
            mLogoutBtn.setVisibility(View.VISIBLE);

            mAccountTextView.setText(name+"\n"+email);
        }
    }
}
