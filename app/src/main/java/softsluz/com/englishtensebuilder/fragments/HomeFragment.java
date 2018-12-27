package softsluz.com.englishtensebuilder.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import softsluz.com.englishtensebuilder.R;

/**
 * Created by a_bas on 1/1/2018.
 */

public class HomeFragment extends Fragment {
    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);

        Toast.makeText(getActivity(), "Home Fragment", Toast.LENGTH_LONG).show();
        return view;
    }
}
