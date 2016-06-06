package form.htl.tl_application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    // using bind to bind objects

    /*private Button button_signup;
    private Button button_login;
    private EditText username_edittext,password_edittext;

    */



        @Bind(R.id.signupButton) Button button_signup;
        @Bind(R.id.loginButton) Button button_login;
        @Bind(R.id.loginIdEditText) EditText username_edittext;
        @Bind(R.id.loginPasswordEditText) EditText password_edittext;


        @OnClick(R.id.signupButton) void signup()
        {

            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
            finish();
        }

        @OnClick(R.id.loginButton) void login()
        {

            String username,password;
            username=username_edittext.getText().toString();
            password=password_edittext.getText().toString();


            try{
                if(username.length() > 0 && password.length() >0)
                {
                    DBUserAdapter dbUser = new DBUserAdapter(LoginActivity.this);
                    dbUser.open();

                    if(dbUser.Login(username, password))
                    {
                        Toast.makeText(LoginActivity.this,"Successfully Logged In", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(LoginActivity.this,"Invalid Username/Password", Toast.LENGTH_LONG).show();
                    }
                    dbUser.close();
                }

            }catch(Exception e)
            {
                Toast.makeText(LoginActivity.this,e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        //   <since used butter to bind>       addListenerOnButton();

    }



    /*  bind used to bind
    private void addListenerOnButton() {


          used bind for this purpose
        button_signup=(Button) findViewById(R.id.signupButton);
            button_login=(Button) findViewById(R.id.loginButton);
            username_edittext=(EditText)findViewById(R.id.loginIdEditText);
            password_edittext=(EditText)findViewById(R.id.loginPasswordEditText);




        button_signup.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {


                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        */


        /*
        button_login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                String username,password;
                username=username_edittext.getText().toString();
                password=password_edittext.getText().toString();


                try{
                    if(username.length() > 0 && password.length() >0)
                    {
                        DBUserAdapter dbUser = new DBUserAdapter(LoginActivity.this);
                        dbUser.open();

                        if(dbUser.Login(username, password))
                        {
                            Toast.makeText(LoginActivity.this,"Successfully Logged In", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(LoginActivity.this,"Invalid Username/Password", Toast.LENGTH_LONG).show();
                        }
                        dbUser.close();
                    }

                }catch(Exception e)
                {
                    Toast.makeText(LoginActivity.this,e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }




        })
        ;*/


}
