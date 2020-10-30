package com.rank.rank;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaCas;
import android.media.MediaSession2;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.resource.bitmap.CenterInside;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;

import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.kakao.auth.ApiResponseCallback;
import com.kakao.auth.AuthType;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.usermgmt.response.model.AgeRange;
import com.kakao.usermgmt.response.model.Gender;
import com.kakao.usermgmt.response.model.Profile;
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.rank.rank.adapter.DrawerAdapter;
import com.rank.rank.adapter.MainAdapter;
import com.rank.rank.databinding.ActivityMainBinding;
import com.rank.rank.listener.LoginListener;
import com.rank.rank.listener.OnItemClick;
import com.rank.rank.model.Partner;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements OnItemClick, LoginListener {
    private ActivityMainBinding binding;
    private MainViewModel mvM;

    private boolean drawerFlag = false;
    DrawerAdapter adapter;

    private String loginPage;

    private TextView[] title;
    private int titleposition;
    private View constraintLayout;
    private DrawerLayout drawerLayout;
    private Context context;

    private SessionCallback s; //kakao
    private CallbackManager callbackManager; //face
    private FLoginCallback fLoginCallback; //face

    private OAuthLogin oAuthLogin; //naver
    private JSONObject naverData;

    private FirebaseAuth mAuth = null;
    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 9001;

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        if (drawerFlag) {
            drawerClose();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getApplicationContext();

        s = new SessionCallback(this, this);
        callbackManager = CallbackManager.Factory.create();
        fLoginCallback = new FLoginCallback(this, this);


//        Toast.makeText(this,"프로젝트 데이터 갯수"+RankSingleTon.getInstance().getProjectModels().getProject().size()
//                +"\n메인 데이터 갯수"+RankSingleTon.getInstance().getMainModel().getData().size(),Toast.LENGTH_SHORT).show();

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);


        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mvM = ViewModelProviders.of(this).get(MainViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setMvM(mvM);
        binding.setActivity(this);


        MainAdapter mainAdapter = new MainAdapter(getSupportFragmentManager());
        binding.viewPager.setAdapter(mainAdapter);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        calendar.set(2000, 10, 27);
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2020, 10, 27);

//
//        int s = calendar.compareTo(calendar1);
//
//        if(s > 0){
//            Toast.makeText(this,"더크다",Toast.LENGTH_SHORT).show();
//        }else if(s<0){
//            Toast.makeText(this,"더작다",Toast.LENGTH_SHORT).show();
//        }else{
//            Toast.makeText(this,"같다",Toast.LENGTH_SHORT).show();
//        }


        title = new TextView[3];
        title[0] = binding.titleRank;
        title[1] = binding.titleProject;
        title[2] = binding.titleCustom;


        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }


            @Override
            public void onPageSelected(int position) {

                title[titleposition].setTextColor(Color.parseColor("#15000000"));
                title[position].setTextColor(Color.parseColor("#000000"));
                titleposition = position;


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        binding.searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!drawerFlag) {
                    binding.drwerlayout.openDrawer(binding.incdraw.drawer);
                    drawerFlag = true;
                }

            }
        });


        binding.drwerlayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
//                adapter.setClear();
//
                drawerFlag = false;
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });



        Session session;
        session = Session.getCurrentSession();
        session.addCallback(s);

        mAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)

                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        binding.incdraw.naverBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPage="naver";
                naverApi();

            }
        });
        binding.incdraw.googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPage="google";
                signIn();
                loginSuccess();
            }
        });

        binding.incdraw.kakaoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "asdfsadf", Toast.LENGTH_LONG).show();
                session.open(AuthType.KAKAO_ACCOUNT, MainActivity.this);


            }
        });

        binding.incdraw.faceBtn.setReadPermissions(Arrays.asList("public_profile", "email"));
        binding.incdraw.faceBtn.registerCallback(callbackManager, fLoginCallback);


        binding.incdraw.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.incdraw.userInfo.setText("");
                binding.incdraw.loginBox.setVisibility(View.VISIBLE);
                binding.incdraw.btnLogout.setVisibility(View.GONE);
                UserManagement.getInstance()
                        .requestLogout(new LogoutResponseCallback() {
                            @Override
                            public void onCompleteLogout() {
                                Toast.makeText(MainActivity.this, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
                            }

                        });
                LoginManager.getInstance().logOut();
                if(loginPage.equals("naver")) {
                    oAuthLogin.logout(context);
                }else if(loginPage.equals("google")){
                    mAuth.signOut();
                }

            }
        });
    }


    public void drawerClose() {
        binding.drwerlayout.closeDrawer(binding.incdraw.drawer);
        drawerFlag = false;

    }

    @Override
    public void onClick() {
        drawerClose();
    }

    @Override
    public void onClick(String filter) {

    }

    public void title(View view) {
        switch (view.getId()) {
            case R.id.title_rank:
                binding.viewPager.setCurrentItem(0);
                break;
            case R.id.title_project:
                binding.viewPager.setCurrentItem(1);
                break;
            case R.id.title_custom:
                binding.viewPager.setCurrentItem(2);
                break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UserManagement.getInstance()
                .requestLogout(new LogoutResponseCallback() {
                    @Override
                    public void onCompleteLogout() {
                        Toast.makeText(MainActivity.this, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
                    }

                });
    }


    @Override
    public void loginSuccess() {
        binding.incdraw.loginBox.setVisibility(View.GONE);
        binding.incdraw.btnLogout.setVisibility(View.VISIBLE);

    }

    @Override
    public void kakaoUserData(Profile profile, String email, AgeRange age_range,
                              String birthday, Gender gender) {
        String st = "이름 : " + profile.getNickname() + "\n" +
                "이메일 : " + email + "\n" +
                "나이(연령대) : " + "??" + "\n" +
                "생일 : " + birthday + "\n" +
                "성별 : " + "??" + "\n";

//        Glide.with(this).load(profile.getProfileImageUrl()).placeholder(R.drawable.logo_placeholder)
//                .into(binding.incdraw.userImg);
        binding.incdraw.userInfo.setText(st);
    }


    @Override
    public void facebookUserData(String id, String name, String email) {
        binding.incdraw.userInfo.setText(id + "\n" + name + "\n" + email);
    }

    @Override
    public void naverUserData() {

    }

    @Override
    public void googleUserData() {

    }


    public void naverApi() {
        oAuthLogin = OAuthLogin.getInstance();
        oAuthLogin.init(
                context
                , getString(R.string.naver_client_id)
                , getString(R.string.naver_client_secret)
                , getString(R.string.naver_client_name)
        );

        @SuppressLint("HandlerLeak")
        OAuthLoginHandler oAuthLoginHandler = new OAuthLoginHandler() {
            @Override
            public void run(boolean success) {
                if (success) {
                    String accessToken = oAuthLogin.getAccessToken(context);
                    String refreshToken = oAuthLogin.getRefreshToken(context);
                    long expiresAt = oAuthLogin.getExpiresAt(context);
                    String tokenType = oAuthLogin.getTokenType(context);
                    Log.d("NaverAccessToken", accessToken);


                    new Thread() {
                        public void run() {
                            try {
                                String aaa = oAuthLogin.requestApi(context, accessToken, "https://openapi.naver.com/v1/nid/me");
                                JSONObject jsonObject = new JSONObject(aaa);
                                String data = "이름 : " + jsonObject.getJSONObject("response").getString("name")
                                        + "\n이메일 : " + jsonObject.getJSONObject("response").getString("email")
                                        + "\n생일 : " + jsonObject.getJSONObject("response").getString("birthday")
                                        + "\n닉네임 : " + jsonObject.getJSONObject("response").getString("nickname")
                                        + "\n연령대 : " + jsonObject.getJSONObject("response").getString("age")
                                        + "\n성별 : " + jsonObject.getJSONObject("response").getString("gender");
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        binding.incdraw.userInfo.setText(data);
                                    }
                                });
//                                        binding.incdraw.userInfo.setText(data);
//                                name = result.getJSONObject("response").getString("name");

                                Log.d("NaverProfileData", "name : " + data);

                            } catch (JSONException e) {
                                Log.d("NaverProfileData", "error : " + e.getMessage());
                            }
                        }
                    }.start();


                } else {
                    String errorCode = oAuthLogin
                            .getLastErrorCode(context).getCode();
                    String errorDesc = oAuthLogin.getLastErrorDesc(context);
                    Toast.makeText(MainActivity.this, "errorCode:" + errorCode
                            + ", errorDesc:" + errorDesc, Toast.LENGTH_SHORT).show();
                }

            }
        };
        oAuthLogin.startOauthLoginActivity(MainActivity.this, oAuthLoginHandler);
        loginSuccess();

    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handlesSignInResult(task);
        }
    }

   private void handlesSignInResult(Task<GoogleSignInAccount> completedTask){
        try{
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            updateUI(account);
        }catch (ApiException a){
            updateUI(null);
        }

   }


    private void updateUI(GoogleSignInAccount user) { //update ui code here
        if (user != null) {
            binding.incdraw.userInfo.setText(
                    "이메일 : "+user.getEmail()
                    +"\n이름 : "+user.getDisplayName()

            );
        }
    }
}

