package com.e.crud_sqlite.fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
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
import android.widget.Toast;

import com.e.crud_sqlite.R;
import com.e.crud_sqlite.helper.ConnectionSQLiteHelper;

import static android.app.Activity.RESULT_OK;
import static com.e.crud_sqlite.utility.ClientUtility.TABLE_CLIENTS;
import static com.e.crud_sqlite.utility.ClientUtility.VERSION;
import static com.e.crud_sqlite.utility.ClientUtility.showToast;

public class RegisterFragment extends Fragment {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    //private static final int TAG_SIMPLE_NOTIFICATION = 1;
    //public static final String TAG_NOTIFICATION_FRAGMENT = "fragment_notification";
    //public static final String EXTRA_FRAGMENT_TO_LAUNCH = "fragment_to_launch";

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
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
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
                    showToast(inflater,getContext(),"CLiENT SAVED");
                    //showSimpleNotification();
                } else {
                    showToast(inflater,getContext(),"ERROR");
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
            RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(getResources(),pictureTaken);
            drawable.setCircular(true);

            iv_avatarImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
            iv_avatarImage.setImageDrawable(drawable);
        }
    }

    /*private PendingIntent pendingIntentForNotification() {
        //Create the intent you want to show when the notification is clicked
        Intent intent = new Intent(getActivity(), MainActivity.class);

        //Add any extras (in this case, that you want to relaunch this fragment)
        intent.putExtra(EXTRA_FRAGMENT_TO_LAUNCH, TAG_NOTIFICATION_FRAGMENT);

        //This will hold the intent you've created until the notification is tapped.
        PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), 1, intent, 0);
        return pendingIntent;
    }*/

    /*private void showSimpleNotification() {
        //Use the NotificationCompat compatibility library in order to get gingerbread support.
        Notification notification = new NotificationCompat.Builder(getActivity())
                //Title of the notification
                .setContentTitle("hola")
                //Content of the notification once opened
                .setContentText("chau")
                //This bit will show up in the notification area in devices that support that
                .setTicker("wwwww")
                //Icon that shows up in the notification area
                .setSmallIcon(R.drawable.ic_add_black_24dp)
                //Icon that shows up in the drawer

                //Set the intent
                .setContentIntent(pendingIntentForNotification())
                //Build the notification with all the stuff you've just set.
                .build();

        //Add the auto-cancel flag to make it dismiss when clicked on
        //This is a bitmask value so you have to pipe-equals it.
        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        //Grab the NotificationManager and post the notification
        NotificationManager notificationManager = (NotificationManager)
                getActivity().getSystemService(Context.NOTIFICATION_SERVICE);

        //Set a tag so that the same notification doesn't get reposted over and over again and
        //you can grab it again later if you need to.
        notificationManager.notify(TAG_SIMPLE_NOTIFICATION, notification);
    }*/

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
