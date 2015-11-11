package pl.droidcon.app.ui.view;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.text.Html;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.Target;

import butterknife.Bind;
import butterknife.ButterKnife;
import pl.droidcon.app.R;
import pl.droidcon.app.ui.transformation.CircleTransform;


public class SimpleListItem extends LinearLayout {

    private static final String TAG = SimpleListItem.class.getSimpleName();

    @Bind(R.id.speaker_list_item_text)
    TextView description;
    @Bind(R.id.speaker_list_item_full_name)
    TextView title;

    public SimpleListItem(Context context) {
        this(context, null);
    }

    public SimpleListItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleListItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SimpleListItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }


    protected void init(Context context, AttributeSet attributeSet, int defStyleAttr, int defStyleRes) {
        LayoutInflater.from(context).inflate(R.layout.speaker_list_item, this, true);
        ButterKnife.bind(this, this);
        setGravity(Gravity.CENTER_VERTICAL);
        setOrientation(VERTICAL);
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(android.R.attr.selectableItemBackground, typedValue, true);
        setBackgroundResource(typedValue.resourceId);
        setClickable(true);
        setFocusable(true);

        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                removeGlobalLayoutListener(this);
                MarginLayoutParams marginLayoutParams = (MarginLayoutParams) getLayoutParams();
                int marginPixelSize = getResources().getDimensionPixelSize(R.dimen.list_element_margin);
                marginLayoutParams.topMargin = marginPixelSize;
                marginLayoutParams.bottomMargin = marginPixelSize;
                setLayoutParams(marginLayoutParams);
                setPadding(marginPixelSize, marginPixelSize, marginPixelSize, marginPixelSize);
            }
        });
    }

    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void removeGlobalLayoutListener(ViewTreeObserver.OnGlobalLayoutListener globalLayoutListener) {
        if (Build.VERSION.SDK_INT < 16) {
            getViewTreeObserver().removeGlobalOnLayoutListener(globalLayoutListener);
        } else {
            getViewTreeObserver().removeOnGlobalLayoutListener(globalLayoutListener);
        }
    }

    protected void setTitle(String text) {
        title.setText(text);
    }

    protected void setImage(@NonNull String imageUrl) {
        load(Picasso.with(getContext()).load(imageUrl));
    }

    protected void setDescription(String text) {
        description.setText(Html.fromHtml(text));
    }

    protected void load(RequestCreator requestCreator) {
        requestCreator
                .resize(64, 64)
                .transform(new CircleTransform())
                .into(bitmapTarget);
    }

    protected Target bitmapTarget = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            Log.d(TAG, "onBitmapLoaded");
            description.setCompoundDrawablesWithIntrinsicBounds(new BitmapDrawable(getResources(), bitmap), null, null, null);
        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {
            Log.d(TAG, "onBitmapFailed");
        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
    };
}
