package softsluz.com.englishtensebuilder.activities;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import softsluz.com.englishtensebuilder.R;

public class ClassHome extends AppCompatActivity {

    private TextView className_tv,adminName_tv,notification_tv;
    private Button quiz_btn;
    ListView quiz_lv;

    FirebaseAuth auth;
    DatabaseReference dbRef;
    private String id;
    private static String TAG;

    private ArrayList<String> Quizzes= new ArrayList<>();
    private ArrayList<String> QuizKeys= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_home);
        TAG = getClass().getName();

        Bundle bundle = getIntent().getExtras();
        String classId = bundle.getString("class_key");
        String className = bundle.getString("class_name");
        //user status shows that is user admin or general member
        final String user_status=bundle.getString("user_status");


        className_tv=(TextView)findViewById(R.id.Class_name_tv);
        adminName_tv=(TextView)findViewById(R.id.Admin_name_tv);
        notification_tv=(TextView)findViewById(R.id.notifications_tv);
        quiz_btn=(Button)findViewById(R.id.newQuiz_btn);
        quiz_lv=(ListView)findViewById(R.id.quiz_list);


        quiz_btn.setVisibility(View.GONE);
        className_tv.setText(className);
        //Get the class Id From last activity
        // (1.New Class creator, 2. MyClasses list. 3. JoinedClasses list)

        if(user_status.equals("admin")){
            quiz_btn.setVisibility(View.VISIBLE);
            quiz_lv.setLongClickable(true);
            quiz_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO move to add quiz activity
                }
            });

        }

        auth=auth.getInstance();
        final FirebaseUser user=auth.getCurrentUser();

        dbRef=FirebaseDatabase.getInstance().getReference().child("Classes").child(classId).child("admin");


        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String adminId= dataSnapshot.getValue(String.class);
                id=adminId;
                Log.v("AdminId",adminId);

                dbRef=FirebaseDatabase.getInstance().getReference().child("Users").child(adminId).child("name");

                dbRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String name = dataSnapshot.getValue(String.class);
                        adminName_tv.setText(name);
                        Log.v("AdminName","_"+name);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        //check if logged in user is the admin of this class Then show his name

        //This code will bring all quizzes of that class

        final ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, Quizzes);

        quiz_lv.setAdapter(arrayAdapter);

        dbRef=FirebaseDatabase.getInstance().getReference().child("Classes").child(classId).child("quiz");

        dbRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                QuizKeys.add(dataSnapshot.getKey());
                String value=dataSnapshot.getValue(String.class);
                Quizzes.add(value);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        quiz_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.v("QuizKey", QuizKeys.get(i));

                if(user_status.equals("admin")){
                    //TODO Quiz edit activity

                }else{
                    //TODO Take quiz Activity

                }

            }

        });

        quiz_lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(user_status.equals("admin")){
                    final AlertDialog.Builder alert = new AlertDialog.Builder(getApplicationContext());
                    Log.v("Long", "Admin");


                    alert.setMessage("Edit or delete");
                    alert.setTitle("Admin Permissions");


                    alert.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {

/*
                            DatabaseReference dbRef= FirebaseDatabase.getInstance().getReference().child("Classes");

                            String classKey =dbRef.push().getKey();
                            String className = edittext.getText().toString();
                            HashMap<String,String> map=new HashMap<String, String>();
                            map.put("name",className);
                            map.put("admin",user.getUid());
                            dbRef.child(classKey).setValue(map);

                            map.clear();


                            //map.put(classKey,className);
                            dbRef.getParent().child("Users").child(user.getUid()).child("MyClasses").child(classKey).setValue(className);

                            Intent i=new Intent(getActivity().getApplicationContext(),ClassHome.class);
                            i.putExtra("class_key",classKey);
                            i.putExtra("class_name",className);
                            i.putExtra("user_status","admin");
                            startActivity(i);
*/

                        }
                    });

                    alert.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            // what ever you want to do with No option.
                        }
                    });

                    alert.show();

                    return false;
                }
                return false;
            }
        });



    }
}
