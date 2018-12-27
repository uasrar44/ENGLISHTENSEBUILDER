package softsluz.com.englishtensebuilder.activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import softsluz.com.englishtensebuilder.R;
import softsluz.com.englishtensebuilder.model.lesson;
import softsluz.com.englishtensebuilder.sqliteManager.DatabaseHelper;

public class ShowLessonActivity extends AppCompatActivity {


    private DatabaseHelper myDbHelper;
    public TextView title, des, example, ques, answer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_lesson);
        Bundle extras = getIntent().getExtras();
        String id=extras.get("id").toString();
        myDbHelper= new DatabaseHelper(ShowLessonActivity.this);
        //Toast.makeText(this,"ID is: "+ id,Toast.LENGTH_SHORT).show();

        title = (TextView) findViewById(R.id.title_tv);//
        des = (TextView) findViewById(R.id.description_tv);
        example = (TextView) findViewById(R.id.example_tv);
        ques = (TextView) findViewById(R.id.question_tv);
        answer = (TextView) findViewById(R.id.answer_tv);

        prepareData(id);
        myDbHelper.close();

    }

    void prepareData(String id){
        Cursor c=null;
        try {
            myDbHelper.openDataBase();
        } catch (SQLException sqle) {
            throw sqle;
        }
        c =myDbHelper.query("Lessons", null, "id=?",new String[]{id}, null, null, null);
        //c =myDbHelper.query("Lessons", null, null,null, null, null, null);
        if (c.moveToFirst()) {
            do {
                lesson l = new lesson(c.getString(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4));
                title.setText(l.getTitle());
                des.setText(l.getDescription());
                example.setText(l.getExample());
                //ques.setText(l.getQuestion());
                //answer.setText(l.getAnswer());

            } while (c.moveToNext());

        }

    }
}
