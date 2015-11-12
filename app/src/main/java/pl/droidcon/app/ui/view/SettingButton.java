package pl.droidcon.app.ui.view;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import pl.droidcon.app.R;

public class SettingButton extends LinearLayout {

    @Bind(R.id.checkbox)
    CheckBox checkBox;

    @Bind(R.id.description)
    TextView description;

    public SettingButton(Context context) {
        this(context, null);
    }

    public SettingButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SettingButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SettingButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context, AttributeSet attributeSet, int defStyleAttr, int defStyleRes) {
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER_VERTICAL);
        setClickable(true);
        setFocusable(true);
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(android.R.attr.selectableItemBackground, typedValue, true);
        setBackgroundResource(typedValue.resourceId);
        LayoutInflater.from(context).inflate(R.layout.setting_button, this, true);
        ButterKnife.bind(this, this);

        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.SettingButton, defStyleAttr, defStyleRes);
        String title = typedArray.getString(R.styleable.SettingButton_sb_title);
        String description = typedArray.getString(R.styleable.SettingButton_sb_description);
        setTitle(title);
        setDescription(description);
        typedArray.recycle();
    }

    public void setTitle(@StringRes int stringRes) {
        checkBox.setText(stringRes);
    }

    public void setTitle(@Nullable String title) {
        checkBox.setText(title);
    }

    public void setDescription(@Nullable String description) {
        this.description.setText(description);
    }

    public void setDescription(@StringRes int description) {
        this.description.setText(description);
    }

    public void setChecked(boolean checked) {
        checkBox.setChecked(checked);
    }

    public boolean isChecked() {
        return checkBox.isChecked();
    }

    public void toggle() {
        setChecked(!isChecked());
    }

}
