package softsluz.com.englishtensebuilder.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import softsluz.com.englishtensebuilder.R;

/**
 * Created by a_bas on 1/1/2018.
 */

public class InboxFragment extends Fragment{

    View view;
    ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_inbox, container, false);
        Toast.makeText(getActivity(), "Inbox Fragment", Toast.LENGTH_LONG).show();

        listView=(ListView)view.findViewById(R.id.message_list);

        return view;
    }
}
