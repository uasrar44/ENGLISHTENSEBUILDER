package softsluz.com.englishtensebuilder.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import softsluz.com.englishtensebuilder.R;
import softsluz.com.englishtensebuilder.fragments.HomeFragment;
import softsluz.com.englishtensebuilder.fragments.InboxFragment;
import softsluz.com.englishtensebuilder.fragments.JoinedClassesFragment;
import softsluz.com.englishtensebuilder.fragments.MyClassesFragment;
import softsluz.com.englishtensebuilder.fragments.NotificationFragment;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {



    TextView user_mail, user_name;
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;
    private FragmentManager fm;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fm=getSupportFragmentManager();

        auth = FirebaseAuth.getInstance();

        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(Home.this, SignInActivity.class));
                    finish();
                }
            }
        };

        Fragment fragment=null;
        fragment=new HomeFragment();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.main_fragment, fragment);
        transaction.commit();
/*

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        /*user_mail=(TextView)findViewById(R.id.user_email_tv);
        user_mail.setText(user.getEmail().toString());*/
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
            Fragment fragment=null;
            fragment=new HomeFragment();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.main_fragment, fragment);
            transaction.commit();
            toolbar.setTitle("Home");

        } else if (id == R.id.nav_inbox) {
            Fragment fragment=null;
            fragment=new InboxFragment();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.main_fragment, fragment);
            transaction.commit();
            toolbar.setTitle("Inbox");
        }
        else if (id == R.id.nav_notification) {
            Fragment fragment=null;
            fragment=new NotificationFragment();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.main_fragment, fragment);
            transaction.commit();
            toolbar.setTitle("Notifications");
        }
        else if (id == R.id.nav_classes) {
            Fragment fragment=null;
            fragment=new MyClassesFragment();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.main_fragment, fragment);
            transaction.commit();
            toolbar.setTitle("My Classes");
        }
        else if (id == R.id.nav_joined) {
            Fragment fragment=null;
            fragment=new JoinedClassesFragment();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.main_fragment, fragment);
            transaction.commit();
            toolbar.setTitle("Joined Classes");
        }
        else if (id == R.id.nav_share) {

        }
        else if (id == R.id.nav_logout) {
            auth.signOut();
            finish();
// this listener will be called when there is change in firebase user session
            FirebaseAuth.AuthStateListener authListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    if (user == null) {
                        // user auth state is changed - user is null
                        // launch login activity
                        startActivity(new Intent(Home.this, SignInActivity.class));

                    }
                }
            };
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
