package com.puyue.www.qiaoge.fragment.mine.order;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.puyue.www.qiaoge.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TestFragment extends Fragment {


    public TestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_test, container, false);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int type = getArguments().getInt("orderDeliveryType");
        Log.i("arqrq", "onCreateView: "+type);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public static AllOrderFragment newInstance(int orderDeliveryType) {
        Bundle args = new Bundle();
        args.putInt("orderDeliveryType", orderDeliveryType);

        AllOrderFragment fragment = new AllOrderFragment();

        fragment.setArguments(args);
        return fragment;
    }
}
