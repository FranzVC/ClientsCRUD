package com.e.crud_sqlite.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.e.crud_sqlite.R;
import com.e.crud_sqlite.helper.ConnectionSQLiteHelper;
import com.e.crud_sqlite.model.Client;

import java.util.HashMap;
import java.util.List;

import static com.e.crud_sqlite.utility.ClientUtility.TABLE_CLIENTS;
import static com.e.crud_sqlite.utility.ClientUtility.VERSION;

public class SearchFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private EditText tbx_idSearch;
    private TextView tv_result;
    private Button btn_search;

    public SearchFragment() {
        // Required empty public constructor
    }
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) { ;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        tbx_idSearch = rootView.findViewById(R.id.tbx_idSearch);
        btn_search = rootView.findViewById(R.id.btn_submitSearch);
        tv_result = rootView.findViewById(R.id.tv_searchResult);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Client> listResult;
                int clientId = Integer.parseInt(tbx_idSearch.getText().toString());
                ConnectionSQLiteHelper connectionSQLiteHelper = new ConnectionSQLiteHelper(getActivity(),TABLE_CLIENTS,null,VERSION);
                listResult = connectionSQLiteHelper.getClientById(clientId);
                if (listResult.isEmpty())
                {
                    tv_result.setText(R.string.NOT_FOUND);
                }else
                {
                    tv_result.setText(listResult.toString());
                }
            }
        });
        return rootView;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
