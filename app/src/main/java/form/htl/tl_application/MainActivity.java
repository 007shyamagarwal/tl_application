package form.htl.tl_application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.support.design.widget.Snackbar;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText editText_name, editText_password,editText_confirmpassword,editText_mobile,editText_email;
    private Button button;
    private ImageButton image_button;
    private boolean pstatus = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        addListenerOnButton();

    }

    public void addListenerOnButton() {
        editText_password = (EditText) findViewById(R.id.passwordEditText);
        editText_confirmpassword = (EditText) findViewById(R.id.confirmEditText);
        editText_name=(EditText)findViewById(R.id.firstEditText);
        editText_email=(EditText)findViewById(R.id.emailEditText);
        editText_mobile=(EditText)findViewById(R.id.mobileEditText);
        button = (Button) findViewById(R.id.submitButton);
        image_button=(ImageButton)findViewById(R.id.imageButton);

        button.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if(editText_name.getText().toString().trim().length() == 0){
                editText_name.setError("Please enter Name.");
                editText_name.requestFocus();
                }
                else if(editText_email.getText().toString().trim().length() == 0){
                    editText_email.setError("Please enter email id");
                    editText_email.requestFocus();
                }
                else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(editText_email.getText().toString()).matches()) {
                    editText_email.setError("Email address should contains a valid maild_id format !!");
                    editText_email.requestFocus();

                }
                else if (editText_password.getText().toString().trim().length() == 0) {
                    editText_password.setError("You can't left this empty");
                    editText_password.requestFocus();


                }
                else if (editText_password.getText().toString().trim().length() < 6 ) {
                    editText_password.setError("Password can't be less than 6 characters");
                    editText_password.requestFocus();


                }
                else if ((editText_password.getText().toString())
                        .equals(editText_confirmpassword.getText().toString()) != true) {

                    editText_confirmpassword.setError("These password don't match Try again?");
                    editText_confirmpassword.requestFocus();

                } else if(editText_mobile.getText().toString().length() <10 ){
                    editText_mobile.setError("Enter 10 digit mobile number");
                    editText_mobile.requestFocus();

                }
                else {
                    Snackbar.make(findViewById(android.R.id.content), "ready to roll", Snackbar.LENGTH_LONG).show();
                    pstatus=true;
                }
                if(pstatus==true) {






                    try{

                            DBUserAdapter dbUser = new DBUserAdapter(MainActivity.this);
                            dbUser.open();

                                dbUser.AddUser(editText_name.getText().toString(),editText_password.getText().toString(),editText_email.getText().toString(),editText_mobile.getText().toString());
                        Snackbar.make(findViewById(android.R.id.content), "ready to rollllll", Snackbar.LENGTH_LONG).show();
                                dbUser.close();


                    }catch(Exception e)
                    {
                        Toast.makeText(MainActivity.this,e.getMessage(), Toast.LENGTH_LONG).show();
                    }

 /*                   Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(i);
                    finish();
    */
                }
            }
        });


        image_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
                finish();
            }
        });


    }
}