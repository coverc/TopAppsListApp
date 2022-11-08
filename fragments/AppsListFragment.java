package edu.uncc.inclass05.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import edu.uncc.inclass05.AppAdapter;
import edu.uncc.inclass05.R;
import edu.uncc.inclass05.databinding.FragmentAppsListBinding;
import edu.uncc.inclass05.models.DataServices;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AppsListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AppsListFragment extends Fragment {
    static ArrayList<DataServices.App> appByCategory = new ArrayList<DataServices.App>();
    ListView listview;
    IListener mListener;
    //ArrayList<String> categoryList;
    AppAdapter adapter;
    //ArrayList<String> appChosen;
    DataServices.App appClickedOn;
    FragmentAppsListBinding binding;
    static String chosenCategory;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AppsListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AppsListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AppsListFragment newInstance(String param1, String param2) {
        AppsListFragment fragment = new AppsListFragment();
        ListView listview;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAppsListBinding.inflate(inflater, container, false);
        getActivity().setTitle(chosenCategory);

        binding.listviewAppsByCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                appClickedOn = adapter.getItem(i);
                Log.d("demo", "onItemClick: " + appClickedOn);
                mListener.appChosen(appClickedOn);
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, appByCategory);

        adapter = new AppAdapter(getActivity(), R.layout.applist, appByCategory);
        binding.listviewAppsByCategories.setAdapter(adapter);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
            mListener = (IListener) context;
    }

    public interface IListener{
        void appChosen(DataServices.App appClickedOn);
    }

    public static void appsByCategory(String categoryChosen){
        appByCategory = DataServices.getAppsByCategory(categoryChosen);
        chosenCategory = categoryChosen;
    }

}