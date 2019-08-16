package com.e.crud_sqlite.fragment;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.e.crud_sqlite.R;
import com.e.crud_sqlite.helper.ConnectionSQLiteHelper;
import com.e.crud_sqlite.model.Client;

import java.util.ArrayList;
import java.util.HashMap;

import static com.e.crud_sqlite.utility.ClientUtility.COLUMN_EMAIL;
import static com.e.crud_sqlite.utility.ClientUtility.COLUMN_NAME;
import static com.e.crud_sqlite.utility.ClientUtility.COLUMN_TELEPHONE;
import static com.e.crud_sqlite.utility.ClientUtility.TABLE_CLIENTS;
import static com.e.crud_sqlite.utility.ClientUtility.VERSION;

public class UpdateFragment extends Fragment {
    private int FIRST_POSITION = 0;
    private OnFragmentInteractionListener mListener;
    private TextView tv_tittle, tv_tittleResult;
    private EditText tbx_idToUpdate, tbx_name, tbx_email, tbx_telephone;
    private Button btn_searchToUpdate, btn_update;

    public UpdateFragment() {
        // Required empty public constructor
    }

    public static UpdateFragment newInstance(String param1, String param2) {
        UpdateFragment fragment = new UpdateFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_update, container, false);
        tv_tittle = rootView.findViewById(R.id.tv_updateTittle);
        tbx_idToUpdate = rootView.findViewById(R.id.tbx_idSearchToUpdate);
        btn_searchToUpdate = rootView.findViewById(R.id.btn_searchToUpdate);

        tv_tittleResult = rootView.findViewById(R.id.tv_updateResultTittle);
        tbx_name = rootView.findViewById(R.id.tbx_nameUpdate);
        tbx_email = rootView.findViewById(R.id.tbx_emailUpdate);
        tbx_telephone = rootView.findViewById(R.id.tbx_telephoneUpdate);
        btn_update = rootView.findViewById(R.id.btn_submitUpdate);

        btn_searchToUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int clientId = Integer.parseInt(tbx_idToUpdate.getText().toString());
                final ConnectionSQLiteHelper connectionSQLiteHelper = new ConnectionSQLiteHelper(getActivity(), TABLE_CLIENTS, null, VERSION);
                ArrayList<Client> client = connectionSQLiteHelper.getClientById(clientId);
                if (!client.isEmpty()) {
                    tv_tittleResult.setVisibility(View.VISIBLE);
                    tbx_name.setVisibility(View.VISIBLE);
                    tbx_email.setVisibility(View.VISIBLE);
                    tbx_telephone.setVisibility(View.VISIBLE);
                    btn_update.setVisibility(View.VISIBLE);

                    tv_tittle.setVisibility(View.GONE);
                    tbx_idToUpdate.setVisibility(View.GONE);
                    btn_searchToUpdate.setVisibility(View.GONE);

                    tbx_name.setText(client.get(FIRST_POSITION).getName());
                    tbx_email.setText(client.get(FIRST_POSITION).getEmail());
                    tbx_telephone.setText(client.get(FIRST_POSITION).getTelephone());

                    btn_update.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String name = tbx_name.getText().toString();
                            String email = tbx_email.getText().toString();
                            String telephone = tbx_telephone.getText().toString();
                            ConnectionSQLiteHelper connectionSQLiteHelper = new ConnectionSQLiteHelper(getActivity(), TABLE_CLIENTS, null, VERSION);
                            boolean isUpdated = connectionSQLiteHelper.updateClient(name, email, telephone, clientId);
                            if (isUpdated) {
                                tv_tittle.setVisibility(View.VISIBLE);
                                tbx_idToUpdate.setVisibility(View.VISIBLE);
                                btn_searchToUpdate.setVisibility(View.VISIBLE);
                                tv_tittleResult.setVisibility(View.GONE);
                                tbx_name.setVisibility(View.GONE);
                                tbx_email.setVisibility(View.GONE);
                                tbx_telephone.setVisibility(View.GONE);
                                btn_update.setVisibility(View.GONE);
                                tbx_idToUpdate.setText("");
                                Toast.makeText(getContext(), R.string.UPDATED, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(getContext(), R.string.NOT_FOUND, Toast.LENGTH_SHORT).show();
                }
            }
        });

        tv_tittle.setVisibility(View.VISIBLE);
        tbx_idToUpdate.setVisibility(View.VISIBLE);
        btn_searchToUpdate.setVisibility(View.VISIBLE);
        tv_tittleResult.setVisibility(View.GONE);
        tbx_name.setVisibility(View.GONE);
        tbx_email.setVisibility(View.GONE);
        tbx_telephone.setVisibility(View.GONE);
        btn_update.setVisibility(View.GONE);

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
