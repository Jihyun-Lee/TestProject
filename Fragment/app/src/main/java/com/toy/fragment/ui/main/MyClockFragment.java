package com.toy.fragment.ui.main;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.toy.fragment.R;
import com.toy.fragment.customview.SuperCircleView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyClockFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyClockFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private SuperCircleView mSuperCircleView;


    public static MyClockFragment newInstance() {
        return new MyClockFragment();
    }
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MyClockFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyClockFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyClockFragment newInstance(String param1, String param2) {
        MyClockFragment fragment = new MyClockFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }




    }

    private void setCurrentTime(){
        long now = System.currentTimeMillis();
        Date data = new Date(now);
        //SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        SimpleDateFormat sdfNow = new SimpleDateFormat("HH:mm");
        String strNow = sdfNow.format(data);

        TextView myClockText = getView().findViewById(R.id.currenttimetext);
        myClockText.setText(strNow);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myClockFragment = inflater.inflate(R.layout.fragment_my_clock, container, false);

        LinearLayout googleLogInButton = myClockFragment.findViewById(R.id.google_login);
        googleLogInButton.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(myClockFragment.getContext(),"google login clicked",Toast.LENGTH_SHORT).show();
            }
        });
        LinearLayout kakaoLogInButton = myClockFragment.findViewById(R.id.kakao_login_button);
        kakaoLogInButton.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(myClockFragment.getContext(),"kakao login clicked",Toast.LENGTH_SHORT).show();
            }
        });


        TextView circleText = myClockFragment.findViewById(R.id.circle_text);
        mSuperCircleView = myClockFragment.findViewById(R.id.superview);

        mSuperCircleView.setValue(100,circleText);

        mSuperCircleView.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //Randomly set the ring size
                int i = new Random().nextInt(100) + 1;
                Log.i("test","onClick : i = " + i);
                mSuperCircleView.setValue(i,circleText);
            }
        });


        return myClockFragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        setCurrentTime();
       // getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);

    }

    @Override
    public void onPause() {
        //getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onPause();
    }
}