package sebbia.ru.testsebbia.view.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import sebbia.ru.testsebbia.R;
import sebbia.ru.testsebbia.model.Categorie;
import sebbia.ru.testsebbia.view.adapter.CatListAdapter;

;

/**
 * A placeholder fragment containing a simple view.
 */
public class CatFragment extends Fragment {

    private View rootView;
    private List<Categorie> listCat;

    public CatFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        
        rootView = inflater.inflate(R.layout.fragment_main, container, false);
        Context context = inflater.getContext();

        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey("list")) {
            listCat = (List<Categorie>) bundle.getSerializable("list");
             
        } else if (bundle == null) {
//            Toast.makeText(getActivity(), "Error Нет setWords", Toast.LENGTH_LONG).show();
        }
        

        CatListAdapter mAdapter = new CatListAdapter(context,
                listCat);

        ListView lv = (ListView) rootView.findViewById(R.id.listCat);
        lv.setAdapter(mAdapter);

        return rootView;


    }
}
