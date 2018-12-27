package softsluz.com.englishtensebuilder.activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.io.IOException;

import softsluz.com.englishtensebuilder.R;
import softsluz.com.englishtensebuilder.sqliteManager.DatabaseHelper;

public class SplashScreenActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 2000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        DatabaseHelper myDbHelper = new DatabaseHelper(SplashScreenActivity.this);
        try {
            myDbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        /*try {
            myDbHelper.openDataBase();
        } catch (SQLException sqle) {
            throw sqle;
        }
        Toast.makeText(SplashScreenActivity.this, "Success", Toast.LENGTH_SHORT).show();
        c = myDbHelper.query("Lessons", null, null, null, null, null, null);
        if (c.moveToFirst()) {
            do {
                Toast.makeText(SplashScreenActivity.this,
                        "_id: " + c.getString(0) + "\n" +
                                "E_NAME: " + c.getString(1) + "\n" +
                                "E_AGE: " + c.getString(2) + "\n" +
                                "E_DEPT:  " + c.getString(3),
                        Toast.LENGTH_LONG).show();
            } while (c.moveToNext());
        }
*/
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
