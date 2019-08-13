package com.e.crud_sqlite.fragment;

import android.content.Context;
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

import java.util.HashMap;
import java.util.List;

import static com.e.crud_sqlite.utility.ClientUtility.TABLE_CLIENTS;
import static com.e.crud_sqlite.utility.ClientUtility.VERSION;

public class DeleteFragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    private EditText tbx_idDelete;
    private TextView tv_result;
    private Button btn_delete;

    public DeleteFragment() {
        // Required empty public constructor
    }

    public static DeleteFragment newInstance(String param1, String param2) {
        DeleteFragment fragment = new DeleteFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_delete, container, false);
        tbx_idDelete = rootView.findViewById(R.id.tbx_idDelete);
        tv_result = rootView.findViewById(R.id.tv_deleteResult);
        btn_delete = rootView.findViewById(R.id.btn_submitDelete);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean result;
                int clientId = Integer.parseInt(tbx_idDelete.getText().toString());
                ConnectionSQLiteHelper connectionSQLiteHelper = new ConnectionSQLiteHelper(getActivity(),TABLE_CLIENTS,null,VERSION);
                result = connectionSQLiteHelper.deleteClient(clientId);
                if (result)
                {
                    /*TODO*/
                    String message = "CLIENT REMOVED \n"+ connectionSQLiteHelper.getClients().toString();
                    tv_result.setText(message);
                }else
                {
                    tv_result.setText(R.string.NOT_FOUND);
                }
            }
        });
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
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
