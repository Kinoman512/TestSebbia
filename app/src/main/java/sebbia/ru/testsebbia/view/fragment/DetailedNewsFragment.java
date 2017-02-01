package sebbia.ru.testsebbia.view.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import java.util.List;

import sebbia.ru.testsebbia.R;
import sebbia.ru.testsebbia.model.New;

/**
 * Created by User on 01.02.2017.
 */

public class DetailedNewsFragment extends Fragment {


    private View rootView;
    private List<New> listNew;


    public DetailedNewsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_fnews, container, false);
        Context context = inflater.getContext();

        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey("list")) {
            listNew = (List<New>) bundle.getSerializable("list");

        } else if (bundle == null) {
//            Toast.makeText(getActivity(), "Error Нет setWords", Toast.LENGTH_LONG).show();
        }




        TextView fnews_title = (TextView) rootView.findViewById(R.id.fnews_title);
        TextView fnews_date = (TextView) rootView.findViewById(R.id.fnews_date);
        WebView fnews_full = (WebView) rootView.findViewById(R.id.fnews_full);

        String data = listNew.get(0).getFullDesc();
        fnews_title.setText(listNew.get(0).getTitle());
        fnews_date.setText(listNew.get(0).getDate());
        fnews_full.loadDataWithBaseURL("", data, "text/html", "UTF-8", "");

        return rootView;






    }
}
