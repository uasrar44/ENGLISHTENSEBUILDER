package softsluz.com.englishtensebuilder.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import softsluz.com.englishtensebuilder.R;
import softsluz.com.englishtensebuilder.activities.ShowLessonActivity;
import softsluz.com.englishtensebuilder.activities.ShowQuestionsActivity;
import softsluz.com.englishtensebuilder.model.lesson;

/**
 * Created by a_bas on 1/9/2018.
 */

public class lessonAdapter extends RecyclerView.Adapter<lessonAdapter.MyViewHolder> {
    private List<lesson> lessonsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, des, example, ques, answer;
        Button lesson_btn,quiz_btn;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title_tv);//
            des = (TextView) view.findViewById(R.id.description_tv);
            lesson_btn=(Button)view.findViewById(R.id.viewLesson_btn);
            quiz_btn=(Button)view.findViewById(R.id.startQuiz_btn);

            //example = (TextView) view.findViewById(R.id.example_tv);
            //ques = (TextView) view.findViewById(R.id.question_tv);
            //answer = (TextView) view.findViewById(R.id.answer_tv);

        }


    }


    public lessonAdapter(List<lesson> lList) {
        this.lessonsList = lList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                //.inflate(R.layout.lesson_page, parent, false);
                .inflate(R.layout.row_card_lesson, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final lesson l = lessonsList.get(position);
        holder.title.setText(l.getTitle());
        holder.des.setText(l.getDescription());
        //holder.example.setText(l.getExample());
        //holder.ques.setText(l.getQuestion());
        //holder.answer.setText(l.getAnswer());

        holder.quiz_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id= l.getId().toString();
                Intent intent=new Intent(holder.itemView.getContext(), ShowQuestionsActivity.class);
                intent.putExtra("id",id);
                holder.itemView.getContext().startActivity(intent);
            }
        });
        holder.lesson_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(holder.itemView.getContext(),"Lesson Button",Toast.LENGTH_SHORT).show();
                String id= l.getId().toString();
                Intent intent=new Intent(holder.itemView.getContext(), ShowLessonActivity.class);
                intent.putExtra("id",id);
                holder.itemView.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return lessonsList.size();
    }

}
