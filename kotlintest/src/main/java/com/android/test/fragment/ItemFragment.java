package com.android.test.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.test.R;
import com.android.test.fragment.dummy.DummyContent;

/**
 * A fragment representing a list of Items.
 */
public class ItemFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private static final String ARG_TITLTE = "title";
    private int mColumnCount = 1;
    private String title;
    private MyItemRecyclerViewAdapter adapter;

    public ItemFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ItemFragment newInstance(int columnCount, String title) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        args.putString(ARG_TITLTE, title);
        fragment.setArguments(args);
        return fragment;
    }

    OnActionListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (null == listener) {
            listener = (OnActionListener) context;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            title = getArguments().getString(ARG_TITLTE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("fragment", "---onCreateView---" + title);
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        TextView textView = view.findViewById(R.id.tv_title);
        RecyclerView recyclerView = view.findViewById(R.id.list);
        textView.setText(title);
        Context context = view.getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new MyItemRecyclerViewAdapter(DummyContent.ITEMS);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        listener.showFragmentSuccess(title);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("fragment", "---onDestroyView---" + title);
    }
}
