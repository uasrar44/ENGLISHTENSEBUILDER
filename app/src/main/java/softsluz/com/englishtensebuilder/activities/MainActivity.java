package softsluz.com.englishtensebuilder.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import softsluz.com.englishtensebuilder.R;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    Button present_btn, past_btn ,future_btn, signin_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth=auth.getInstance();


        //linking xml buttons with java buttons
        present_btn=(Button)findViewById(R.id.present);
        past_btn=(Button)findViewById(R.id.past);
        future_btn=(Button)findViewById(R.id.future);
        signin_btn=(Button)findViewById(R.id.signin);
        if (auth.getCurrentUser() != null) {
        signin_btn.setText("Admin Area");
        }

        //On clink functions
        present_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Lessons.class);//get application context means that we are present on this activity
                i.putExtra("lesson","present");
                startActivity(i);
            }
        });
        past_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Lessons.class);
                i.putExtra("lesson","past");
                startActivity(i);
            }
        });

        future_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Lessons.class);
                i.putExtra("lesson","future");
                startActivity(i);
            }
        });

        signin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),SignInActivity.class);
                startActivity(i);
            }
        });




    }
}
