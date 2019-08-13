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

import com.e.crud_sqlite.R;
import com.e.crud_sqlite.helper.ConnectionSQLiteHelper;

import static com.e.crud_sqlite.utility.ClientUtility.TABLE_CLIENTS;
import static com.e.crud_sqlite.utility.ClientUtility.VERSION;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegisterFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {
    private EditText tbx_name,tbx_email,tbx_telephone;
    private Button btn_submit;

    private OnFragmentInteractionListener mListener;

    public RegisterFragment() {
    }
    public static RegisterFragment newInstance() {
        RegisterFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_register, container, false);
        tbx_name = rootView.findViewById(R.id.tbx_nameRegister);
        tbx_email = rootView.findViewById(R.id.tbx_emailRegister);
        tbx_telephone = rootView.findViewById(R.id.tbx_telephoneRegister);
        btn_submit = rootView.findViewById(R.id.btn_submitRegister);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = tbx_name.getText().toString();
                String email = tbx_email.getText().toString();
                String telephone = tbx_telephone.getText().toString();
                ConnectionSQLiteHelper connectionSQLiteHelper = new ConnectionSQLiteHelper(getActivity(),TABLE_CLIENTS,null,VERSION);
                connectionSQLiteHelper.insertClient(name,email,telephone);
            }
        });
        return rootView;
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
