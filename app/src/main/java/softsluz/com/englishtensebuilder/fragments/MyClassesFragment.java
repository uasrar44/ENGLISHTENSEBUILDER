package softsluz.com.englishtensebuilder.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

import softsluz.com.englishtensebuilder.activities.ClassHome;
import softsluz.com.englishtensebuilder.R;

/**
 * Created by a_bas on 1/1/2018.
 */

public class MyClassesFragment extends Fragment {
    FirebaseAuth firebaseAuth;
    View view;
    DatabaseReference databaseReference;
    private ListView myclasses_LV;
    private Button newClassBtn;

    private ArrayList<String> classes= new ArrayList<>();
    private ArrayList<String> classKeys= new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_my_classes, container, false);

        myclasses_LV=(ListView) view.findViewById(R.id.MyClasses_LV);
        newClassBtn=(Button) view.findViewById(R.id.NewClass_btn);

        firebaseAuth=firebaseAuth.getInstance();
        final FirebaseUser user=firebaseAuth.getCurrentUser();
        //user.getUid()
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Users").child(user.getUid()).child("MyClasses");


        //Toast.makeText(getActivity(), "My Classes Fragment", Toast.LENGTH_SHORT).show();
        //
        final ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, classes);

        myclasses_LV.setAdapter(arrayAdapter);


        //gets data from the server (Firebase)
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                String value=dataSnapshot.getValue(String.class);
                String key=dataSnapshot.getKey();
                classes.add(value);
                classKeys.add(key);
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


        //List item click function
        myclasses_LV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // String item = (String) myclasses_LV.getItemAtPosition(position);
                // Toast.makeText(getActivity(),"You selected : " + classKeys.get(position),Toast.LENGTH_SHORT).show();
                Intent i=new Intent(getActivity(),ClassHome.class);
                i.putExtra("class_key",classKeys.get(position));
                i.putExtra("class_name",classes.get(position));
                i.putExtra("user_status","admin");
                startActivity(i);
            }
        });




        final AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        newClassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText edittext = new EditText(getActivity().getApplicationContext());
                alert.setMessage("Class Name");
                alert.setTitle("Create New Class");

                alert.setView(edittext);

                alert.setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //What ever you want to do with the value
                        //Editable YouEditTextValue = edittext.getText();
                        //OR
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

                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // what ever you want to do with No option.
                    }
                });

                alert.show();
            }
        });

        return view;
    }
}
