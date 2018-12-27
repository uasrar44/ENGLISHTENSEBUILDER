package softsluz.com.englishtensebuilder.activities;

import android.database.Cursor;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import softsluz.com.englishtensebuilder.R;
import softsluz.com.englishtensebuilder.adapters.lessonAdapter;
import softsluz.com.englishtensebuilder.model.lesson;
import softsluz.com.englishtensebuilder.sqliteManager.DatabaseHelper;

public class Lessons extends AppCompatActivity {//appcompatactivity is type of header file

    private Cursor c=null;
    private Toolbar toolbar;
    private RecyclerView lessonRecycler;
    private List<lesson> lessonList=new ArrayList<>();
    private lessonAdapter lessonAdpt;
    private DatabaseHelper myDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons);
        Bundle bundle= getIntent().getExtras();
        String lesson_type=bundle.getString("lesson");

        toolbar=(Toolbar)findViewById(R.id.toolbar_id);
        toolbar.setTitle("Lesson");
        setSupportActionBar(toolbar);
        myDbHelper= new DatabaseHelper(Lessons.this);


        lessonRecycler=(RecyclerView)findViewById(R.id.lesson_view);
        lessonAdpt=new lessonAdapter(lessonList);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        lessonRecycler.setLayoutManager(layoutManager);
        lessonRecycler.setItemAnimator(new DefaultItemAnimator());
        lessonRecycler.setAdapter(lessonAdpt);//qAdapter
        prepareLessonData(lesson_type);

    }

    private void prepareLessonData(String type){

        try {
            myDbHelper.openDataBase();
        } catch (SQLException sqle) {
            throw sqle;
        }
        c =myDbHelper.query("Lessons", null, "type=?",new String[]{type}, null, null, null);
        //c =myDbHelper.query("Lessons", null, null,null, null, null, null);
        if (c.moveToFirst()) {
            do {
               lesson l = new lesson(c.getString(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4));
                lessonList.add(l);
            } while (c.moveToNext());
        }
        lessonAdpt.notifyDataSetChanged();
    }
}
