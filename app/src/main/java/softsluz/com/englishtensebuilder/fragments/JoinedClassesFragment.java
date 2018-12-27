package softsluz.com.englishtensebuilder.fragments;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import softsluz.com.englishtensebuilder.activities.ClassHome;
import softsluz.com.englishtensebuilder.R;

/**
 * Created by a_bas on 1/1/2018.
 */

public class JoinedClassesFragment extends Fragment {
    View view;

    FirebaseDatabase firebaseDatabase;
    FirebaseAuth auth;
    DatabaseReference databaseReference;
    ListView Joined_Classes_LV;

    ArrayList<String> myJoinedClasses=new ArrayList<>();

    private ArrayList<String> classKeys= new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_joined_classes, container, false);
        Joined_Classes_LV=(ListView)view.findViewById(R.id.Joind_classes_LV);

        auth=auth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        String uid=user.getUid();

        databaseReference=FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("JoinedClasses");


        final ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, myJoinedClasses);
        Joined_Classes_LV.setAdapter(arrayAdapter);

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                String value=dataSnapshot.getValue(String.class);
                String key=dataSnapshot.getKey();
                myJoinedClasses.add(value);
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

        //Toast.makeText(getActivity(), "Joined Classes Fragment", Toast.LENGTH_SHORT).show();
        Joined_Classes_LV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String item = (String) Joined_Classes_LV.getItemAtPosition(position);
                Toast.makeText(getActivity(),"You selected : " + classKeys.get(position),Toast.LENGTH_SHORT).show();
                Intent i=new Intent(getActivity(),ClassHome.class);
                i.putExtra("class_key",classKeys.get(position));
                i.putExtra("class_name",myJoinedClasses.get(position));
                i.putExtra("user_status","member");
                startActivity(i);
            }
        });


        return view;
    }
}
