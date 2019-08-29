package com.example.mrzero.androidadkyaaapp3.ui.fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.CountDownTimer;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mrzero.androidadkyaaapp3.R;
import com.example.mrzero.androidadkyaaapp3.api.APIService;
import com.example.mrzero.androidadkyaaapp3.api.ServiceGenerator;
import com.example.mrzero.androidadkyaaapp3.model.getQuestion.Option;
import com.example.mrzero.androidadkyaaapp3.model.getQuestion.Question;
import com.example.mrzero.androidadkyaaapp3.model.getQuestion.ResultGetQuestion;
import com.example.mrzero.androidadkyaaapp3.model.getanswer.Answer;
import com.example.mrzero.androidadkyaaapp3.model.getanswer.ResultGetAnswer;
import com.example.mrzero.androidadkyaaapp3.ui.activites.HomeActivity;
import com.example.mrzero.androidadkyaaapp3.utils.Common;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChalengeFragment extends Fragment {

   private Button btn1,btn2,btn3,btn4,btn_send_answer;
  private   TextView tv_question,tv_time_elapesd,tv_question_number;
  ImageView img_question;

    AlertDialog dialog;
    AlertDialog.Builder mBuilder;
    Integer mOptionSelectedId=null;

    Question CurrunQuestion= new Question();
   Answer CurrunAnswer= new Answer();


    ArrayList<Option> options= new ArrayList<>();

    int questionNumber =0 ; //the first question is 1
    SeekBar mSeekBar;



    Integer repeatExam=1;
    /*
       //0>>  will reapet the exam and start from question 1 and increase the question questionNumber after new request
         * but if i put 1 >> that mean after new question request > the questionNumber with alwas return 1 ,
         * so >> if the user go out from the app > i put repeatExam = 1
         * * so here >> always start from question 1
        */


    long totalSeconds = 10000000;//max second
    long intervalSeconds = 1;
    CountDownTimer timer;
      int hour=0;
      int minte=0;
      int sec=0;

    public ChalengeFragment() {
        // Required empty public constructor
    }
    private static ChalengeFragment Instance;

    public static ChalengeFragment getInstance(){


         
        if (Instance==null) {
            Instance= new ChalengeFragment();
        }
        return Instance;
    }

    @Override
    public void onStop() {
        super.onStop();
        timer.cancel();
    }



    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
          hour=0;
          minte=0;
          sec=0;
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_chalenge, container, false);
        final ImageView menubar= view.findViewById(R.id.menubar);
        TextView tv_title= view.findViewById(R.id.tv_title);
        tv_title.setText(Common.CurrentSection.getName());
        init(view);

        sendQuestionRequest();

          timer = new CountDownTimer(totalSeconds * 1000, intervalSeconds * 1000) {


            public void onTick(long millisUntilFinished) {
                sec++;
                if (sec==59){
                    sec=0;
                    minte++;
                    if (minte ==59){
                        minte=0;
                        hour++;
                    }
                }

                String timeString = String.format(Locale.getDefault(),"%02d:%02d:%02d",hour,minte,sec);
                Log.d("seconds elapsed: " , timeString );

                tv_time_elapesd.setText(timeString);
            }

            public void onFinish() {
                Log.d( "done!", "Time's up!");
            }

        } ;


        menubar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //THIS IS FOR SHOW THE DrawerLayout
                //because the nav in the activity .. i use this
                DrawerLayout drawer = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);

                drawer.openDrawer(GravityCompat.START);
            }
        });

        ImageView notImage= view.findViewById(R.id.imageView9);
        notImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeActivity.replaceFragmentFromActivity(NotificationFragment.getInstance());

            }
        });



         btn1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                btn1.setTextColor(getResources().getColor(R.color.white));
                btn2.setTextColor(getResources().getColor(R.color.black));
                btn3.setTextColor(getResources().getColor(R.color.black));
                btn4.setTextColor(getResources().getColor(R.color.black));


                //set option 1
                mOptionSelectedId=options.get(0).getId();



                return false;
            }
        });

        btn2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                btn1.setTextColor(getResources().getColor(R.color.black));
                btn2.setTextColor(getResources().getColor(R.color.white));
                btn3.setTextColor(getResources().getColor(R.color.black));
                btn4.setTextColor(getResources().getColor(R.color.black));


                //set option 2
                mOptionSelectedId=options.get(1).getId();
                return false;
            }
        });

        btn3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                btn1.setTextColor(getResources().getColor(R.color.black));
                btn2.setTextColor(getResources().getColor(R.color.black));
                btn3.setTextColor(getResources().getColor(R.color.white));
                btn4.setTextColor(getResources().getColor(R.color.black));


                //set option 3
                mOptionSelectedId=options.get(2).getId();
                return false;
            }
        });


        btn4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                btn1.setTextColor(getResources().getColor(R.color.black));
                btn2.setTextColor(getResources().getColor(R.color.black));
                btn3.setTextColor(getResources().getColor(R.color.black));
                btn4.setTextColor(getResources().getColor(R.color.white));



                //set option 4
                mOptionSelectedId=options.get(3).getId();
                return false;
            }
        });

        btn_send_answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendAnswerRequest();
            }
        });

        return view;
    }
    public void showDialog( ){
        mBuilder = new AlertDialog.Builder(getActivity() );
        View mView = getLayoutInflater().inflate(R.layout.dialog_complete_message, null);
        mBuilder.setView(mView);
        dialog = mBuilder.create();
        dialog.show();

        final Button btn_ok =  mView.findViewById(R.id.btn_ok);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Fragment fragment= HomeFragment.getInstance();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.contianer_frame, fragment).commit();
            }
        });

    }
    private void init(View view) {
        btn1= view.findViewById(R.id.btn_answer_1);
        btn2= view.findViewById(R.id.btn_answer_2);
        btn3= view.findViewById(R.id.btn_answer_3);
        btn4= view.findViewById(R.id.btn_answer_4);
        btn_send_answer= view.findViewById(R.id.btn_send_answer);
        tv_question= view.findViewById(R.id.textView25);
        tv_time_elapesd = view.findViewById(R.id.tv_time);
        tv_time_elapesd.setText("00:00:00");
        tv_question_number = view.findViewById(R.id.tv_question_number);
        tv_question_number.setText(questionNumber + "/20");
        mSeekBar=view.findViewById(R.id.seekbar);
        img_question=view.findViewById(R.id.img_question);
        mSeekBar.setProgress(questionNumber);
    }

    private void sendAnswerRequest() {

        // todo get time , change current question
        String QuestionID=String.valueOf(CurrunQuestion.getId());
        String optionID=String.valueOf( mOptionSelectedId );
        String timeElapsed=tv_time_elapesd.getText().toString();

        if (mOptionSelectedId==null){
            Toast.makeText(getActivity(), "الرجاء اختيار اجابة", Toast.LENGTH_SHORT).show();
        }else {
            APIService apiService= ServiceGenerator.createService(APIService.class,Common.retrieveUserDataPreferance(getActivity()).getRememberToken());
            Call<ResultGetAnswer> call =  apiService.getAnswer(
                    QuestionID,
                    optionID,
                    String.valueOf(repeatExam),
                    timeElapsed

            );
             call.enqueue(new Callback<ResultGetAnswer>() {
                @Override
                public void onResponse(Call<ResultGetAnswer> call, Response<ResultGetAnswer> response) {
                    if (response.isSuccessful()){
                        CurrunAnswer =response.body().getData() ;

                        //before send new request question,, check if there a next question ?
                        //CurrunAnswer.getIsFinished()==1 >>> this condition not valid because is test for 21 question >> not for 20 !
                        //so i check from this condition
                        if (CurrunAnswer.getQuestionNumber()==20) {
                            //1 = true 0 = false
                            showEndDaialog();
                        }else {
                            questionNumber=CurrunAnswer.getQuestionNumber();
                            showCorrectAnswerThenSendNewQuestionRequest();

                            //i get the answer
                            //change the reatansewr to 0 ,>> to increase  questionNumber in the next request
                            //change to 1 only in defult state or if the user go out from the app
                            repeatExam=0;
                        }

                        //get next question todo ? or send new request question?
                         //you may need to get the next qouestion >> and equal to current question ...
                        // but the two question are randomly get that from api

                    }
                }

                @Override
                public void onFailure(Call<ResultGetAnswer> call, Throwable t) {
                    Toast.makeText(getActivity(), "خطأ في الاتصال", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    private void showCorrectAnswerThenSendNewQuestionRequest() {

        // 0 >> false   1 >>true
        int isCorrect= CurrunAnswer.getIsCorrect();
        int correctOpttionId= CurrunAnswer.getCorrectOptions().get(0);

        if (isCorrect==1){
            //true >> make the answer grean
            if ( options.get(0).getId()==correctOpttionId){
//            if true and   btn1 was selected  >> change background color
                btn1.setBackgroundResource(R.drawable.shape_btn_correct_answer);

            }else if (options.get(1).getId()==correctOpttionId){
                btn2.setBackgroundResource(R.drawable.shape_btn_correct_answer);
                
            }else if (options.get(2).getId()==correctOpttionId){
                btn3.setBackgroundResource(R.drawable.shape_btn_correct_answer);

            }else if (options.get(3).getId()==correctOpttionId){
                btn4.setBackgroundResource(R.drawable.shape_btn_correct_answer);

            }

                
        }else {
            //if quesetion not true >> make the correct answer green< and the false answer is red
            Toast.makeText(getActivity(), "ليس صحيح", Toast.LENGTH_SHORT).show();

            if ( options.get(0).getId()==correctOpttionId){
//            if true and   btn1 was selected  >> change background color
                btn1.setBackgroundResource(R.drawable.shape_btn_correct_answer);

            }else if (options.get(1).getId()==correctOpttionId){
                btn2.setBackgroundResource(R.drawable.shape_btn_correct_answer);

            }else if (options.get(2).getId()==correctOpttionId){
                btn3.setBackgroundResource(R.drawable.shape_btn_correct_answer);

            }else if (options.get(3).getId()==correctOpttionId){
                btn4.setBackgroundResource(R.drawable.shape_btn_correct_answer);

            }



            if (options.get(0).getId() ==mOptionSelectedId){
                btn1.setBackgroundResource(R.drawable.shape_btn_wrong_answer);

            }else if (options.get(1).getId() ==mOptionSelectedId){
                btn2.setBackgroundResource(R.drawable.shape_btn_wrong_answer);

            }else if (options.get(2).getId() ==mOptionSelectedId){
                btn3.setBackgroundResource(R.drawable.shape_btn_wrong_answer);

            }else if (options.get(3).getId() ==mOptionSelectedId){
                btn4.setBackgroundResource(R.drawable.shape_btn_wrong_answer);

            }
        }
        sendQuestionRequest();
    }

    private void showEndDaialog() {
        //show message to user
        //todo
        showDialog();

    }

    @Override
    public void onResume(){
        super.onResume();
        timer.start();
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    // handle back button's click listener
                    showAlarmNoteToEndTheQuiz();
                     return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        timer.cancel();

    }


    private void showAlarmNoteToEndTheQuiz() {
        timer.cancel();

        mBuilder = new AlertDialog.Builder(getActivity() );
        View mView = getLayoutInflater().inflate(R.layout.show_alarm_message_to_end_quiz, null);
        mBuilder.setView(mView);
        dialog = mBuilder.create();
        dialog.show();

        final Button btn_ok =  mView.findViewById(R.id.btn_ok);
        final Button btn_cancel =  mView.findViewById(R.id.btn_cancel);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                timer.cancel();
                dialog.dismiss();
                Fragment fragment= SecondMatrialFragment.getInstance();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.contianer_frame, fragment).commit();


            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

            }
        });

        //dismiss if cancel
    }

    private void resetAnswerButton() {
        btn1.clearFocus();
        btn2.clearFocus();
        btn3.clearFocus();
        btn4.clearFocus();

        //restore defult color
        btn1.setTextColor(getResources().getColor(R.color.black));
        btn2.setTextColor(getResources().getColor(R.color.black));
        btn3.setTextColor(getResources().getColor(R.color.black));
        btn4.setTextColor(getResources().getColor(R.color.black));

        //rest background
        btn1.setBackgroundResource(R.drawable.shape_btn_answer);
        btn2.setBackgroundResource(R.drawable.shape_btn_answer);
        btn3.setBackgroundResource(R.drawable.shape_btn_answer);
        btn4.setBackgroundResource(R.drawable.shape_btn_answer);



    }

    private void sendQuestionRequest() {
        String tok=Common.retrieveUserDataPreferance(getActivity()).getRememberToken() ;
        APIService apiService= ServiceGenerator.createService(APIService.class,tok);

        String materialId=String.valueOf(Common.CurrentMaterial.getId());
        String seconderyMaterialId=String.valueOf(Common.CurrentSecondMaterial.getId());
        String sectionId=String.valueOf(Common.CurrentSection.getId());

       Call<ResultGetQuestion> call =  apiService.getQuestion(materialId,seconderyMaterialId,sectionId);
       //todo get parameter
       call.enqueue(new Callback<ResultGetQuestion>() {
           @Override
           public void onResponse(Call<ResultGetQuestion> call, Response<ResultGetQuestion> response) {
               if (response.isSuccessful()){

                   //check if there is no question
                   if (response.body().getData().getText().equals("")){

                       showNoQuestionDailog();
                   }else {
                       CurrunQuestion= response.body().getData() ;
                       options= (ArrayList<Option>) CurrunQuestion.getOptions();
                       resetAnswerButton();
                       updateUiWithData();

                   }
               }
           }

           @Override
           public void onFailure(Call<ResultGetQuestion> call, Throwable t) {
               Toast.makeText(getActivity(), "خطأ في الاتصال", Toast.LENGTH_SHORT).show();
           }
       });
    }

    private void showNoQuestionDailog() {
        timer.cancel();
        mBuilder = new AlertDialog.Builder(getActivity() );
        View mView = getLayoutInflater().inflate(R.layout.no_question_dailog, null);
        mBuilder.setView(mView);
        dialog = mBuilder.create();
        dialog.show();

        final Button btn_ok =  mView.findViewById(R.id.btn_ok);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.cancel();
                dialog.dismiss();
                Fragment fragment= HomeFragment.getInstance();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.contianer_frame, fragment).commit();
            }
        });
    }


    private void updateUiWithData() {

        if (CurrunQuestion!= null && options !=null){

            btn1.setText(options.get(0).getText());
            btn2.setText(options.get(1).getText());
            btn3.setText(options.get(2).getText());
            btn4.setText(options.get(3).getText());

            //convert from html to text
            String desc=CurrunQuestion.getText();
            tv_question.setText(Html.fromHtml( desc ));


            if (CurrunQuestion.getImage()!=null){
                //show image and set it
                img_question.setVisibility(View.VISIBLE);
                Picasso.get().load(CurrunQuestion.getImage()).into(img_question);
            }else {
                img_question.setVisibility(View.GONE);
            }
        }

        tv_question_number.setText(questionNumber + "/20");
        mSeekBar.setProgress(questionNumber);
    }
}
