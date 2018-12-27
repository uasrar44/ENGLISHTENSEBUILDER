package softsluz.com.englishtensebuilder.activities;

import android.database.Cursor;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import softsluz.com.englishtensebuilder.R;
import softsluz.com.englishtensebuilder.model.Question;
import softsluz.com.englishtensebuilder.model.lesson;
import softsluz.com.englishtensebuilder.sqliteManager.DatabaseHelper;

public class ShowQuestionsActivity extends AppCompatActivity {


    //TODO make database of questions

    TextView counter_tv,Question_title_tv;
    Button back_btn, submit_btn;
    RadioGroup radioGroup;
    RadioButton r1,r2,r3,r4;
    private DatabaseHelper myDbHelper;
    private int Marks,questionCounter;
    private List<Question> questionList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_questions);

        //counter_tv=(TextView)findViewById(R.id.counter_tv);
        Question_title_tv=(TextView)findViewById(R.id.Question_Title_tv);

        //back_btn=(Button)findViewById(R.id.back_btn);
        submit_btn=(Button)findViewById(R.id.submit_btn);

        radioGroup=(RadioGroup)findViewById(R.id.radioGroup_id);
        r1=(RadioButton)findViewById(R.id.opt1_id);
        r2=(RadioButton)findViewById(R.id.opt2_id);
        r3=(RadioButton)findViewById(R.id.opt3_id);
        r4=(RadioButton)findViewById(R.id.opt4_id);

        Bundle extras = getIntent().getExtras();
        String lecture_id=extras.get("id").toString();
        myDbHelper= new DatabaseHelper(getApplicationContext());

        prepareData(lecture_id);    //Added to the array list

        int num_questions=questionList.size();    //number of elements in array


        //handling the questions
         questionCounter=0;
         Marks=0;

            for (int i=0; i< num_questions;i++) {
                setQuestion(questionList.get(questionCounter));

                submit_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Question q = questionList.get(questionCounter);

                        //get the selected radio buttons's text and compair it with answer
                        int selected_id = radioGroup.getCheckedRadioButtonId();

                        if (selected_id == r1.getId()) {
                            if (r1.getText().toString().equals(q.getAnswer())) {
                                Marks++;
                            }
                            questionCounter++;
                        }
                        else if (selected_id == r2.getId()) {
                            if (r2.getText().toString().equals(q.getAnswer())) {
                                Marks++;
                            }
                            questionCounter++;
                        }
                        else if (selected_id == r3.getId()) {
                            if (r3.getText().toString().equals(q.getAnswer())) {
                                Marks++;
                            }
                            questionCounter++;
                        }
                        else if (selected_id == r4.getId()) {
                            if (r4.getText().toString().equals(q.getAnswer())) {
                                Marks++;
                            }
                            questionCounter++;
                        }
                    }
                });

            }

        Toast.makeText(getApplicationContext(),Marks+"/"+num_questions,Toast.LENGTH_SHORT).show();
        if(questionCounter==num_questions){
            finish();
        }
    }


    void setQuestion(Question question){

        Question_title_tv.setText(question.getQuestionTitle());
        r1.setText(question.getOpt1());
        r2.setText(question.getOpt2());
        r3.setText(question.getOpt3());
        r4.setText(question.getOpt4());


    }


    void prepareData(String lecture_id){
        Cursor c=null;

        try {
            myDbHelper.openDataBase();
        } catch (SQLException sqle) {
            throw sqle;
        }
        c =myDbHelper.query("Questions", null, "lecture_id=?",new String[]{lecture_id}, null, null, null);
        //c =myDbHelper.query("Lessons", null, null,null, null, null, null);
        if (c.moveToFirst()) {
            do {
                Question q = new Question(c.getString(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6),c.getString(7));
                questionList.add(q);
            } while (c.moveToNext());
        }

    }

}
