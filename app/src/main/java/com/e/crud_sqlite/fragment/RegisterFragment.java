package com.e.crud_sqlite.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ResourceCursorTreeAdapter;
import android.widget.Toast;

import com.e.crud_sqlite.R;
import com.e.crud_sqlite.helper.ConnectionSQLiteHelper;

import static android.app.Activity.RESULT_OK;
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
    static final int REQUEST_IMAGE_CAPTURE = 1;

    private EditText tbx_name, tbx_email, tbx_telephone;
    private Button btn_submit;
    private ImageButton btn_takePicture;
    private ImageView iv_avatarImage;

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
                ConnectionSQLiteHelper connectionSQLiteHelper = new ConnectionSQLiteHelper(getActivity(), TABLE_CLIENTS, null, VERSION);
                long idClient = connectionSQLiteHelper.insertClient(name, email, telephone);
                if (idClient != -1) {
                    Toast.makeText(getActivity(), R.string.SAVED, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(), R.string.ERROR, Toast.LENGTH_LONG).show();
                }
            }
        });
        iv_avatarImage = rootView.findViewById(R.id.iv_avatarImage);
        btn_takePicture = rootView.findViewById(R.id.btn_takePicture);

        btn_takePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });
        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_IMAGE_CAPTURE) {
            Bitmap pictureTaken = (Bitmap) data.getExtras().get("data");
            //iv_avatarImage.setImageBitmap(pictureTaken);
            RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(getResources(),pictureTaken);
            drawable.setCircular(true);

            iv_avatarImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
            iv_avatarImage.setImageDrawable(drawable);
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
