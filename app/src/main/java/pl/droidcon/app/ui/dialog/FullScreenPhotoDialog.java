package pl.droidcon.app.ui.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.droidcon.app.R;

public class FullScreenPhotoDialog extends AppCompatDialogFragment {

    private static final String TAG = FullScreenPhotoDialog.class.getSimpleName();

    private static final String PHOTO_URL_KEY = "photo";

    public static FullScreenPhotoDialog newInstance(String photoUrl) {
        Bundle args = new Bundle();
        args.putString(PHOTO_URL_KEY, photoUrl);
        FullScreenPhotoDialog fragment = new FullScreenPhotoDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Bind(R.id.full_screen_photo)
    ImageView fullScreenPhoto;

    private String photoUrl;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, getTheme());
        photoUrl = getArguments().getString(PHOTO_URL_KEY);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.full_screen_speaker_photo_dialog, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        Glide.with(getContext())
                .load(photoUrl)
                .fitCenter()
                .into(fullScreenPhoto);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Glide.clear(fullScreenPhoto);
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.full_screen_photo)
    void onClicked() {
        dismiss();
    }
}
