package form.htl.tl_application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private Button button_signup;
    private Button button_login;
    private EditText username_edittext,password_edittext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        addListenerOnButton();
    }

    private void addListenerOnButton() {

            button_signup=(Button) findViewById(R.id.signupButton);
            button_login=(Button) findViewById(R.id.loginButton);
            username_edittext=(EditText)findViewById(R.id.loginIdEditText);
            password_edittext=(EditText)findViewById(R.id.passwordEditText);
        button_signup.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {


                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();
            }
        });

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




        });

    }
}
