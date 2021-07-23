package info.ankurpandya.testrxjava.customviews;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.StringRes;

import info.ankurpandya.testrxjava.R;

/**
 * Create by Ankur @ Worktable.sg
 */
public class KeyValuePairView extends RelativeLayout {

    private static final int UNSET_INT_VALUE = -1;

    private LinearLayout container;
    private TextView txtHint;
    private TextView txtValue;

    public KeyValuePairView(Context context) {
        super(context);
        init(null);
    }

    public KeyValuePairView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public KeyValuePairView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public KeyValuePairView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        loadAttrs(inflater, attrs);
    }

    private void loadAttrs(LayoutInflater inflater, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.KeyValuePairView);
            int orientation = typedArray.getInt(R.styleable.KeyValuePairView_android_orientation, LinearLayout.HORIZONTAL);
            initViewWithOrientation(inflater, orientation);

            CharSequence text = typedArray.getText(R.styleable.KeyValuePairView_android_text);
            setText(text);

            CharSequence hint = typedArray.getText(R.styleable.KeyValuePairView_android_hint);
            setHint(hint);

            int textColor = typedArray.getColor(R.styleable.KeyValuePairView_android_textColor, UNSET_INT_VALUE);
            if (textColor != UNSET_INT_VALUE) {
                setTextColor(textColor);
            }

            int textColorHint = typedArray.getColor(R.styleable.KeyValuePairView_android_textColorHint, UNSET_INT_VALUE);
            if (textColorHint != UNSET_INT_VALUE) {
                setHintTextColor(textColorHint);
            }

            int gravity = typedArray.getInt(R.styleable.KeyValuePairView_android_gravity, Gravity.START | Gravity.TOP);
            setGravity(gravity);

            int maxLines = typedArray.getInt(R.styleable.KeyValuePairView_android_maxLines, 1);
            setMaxLines(maxLines);

            int minLines = typedArray.getInt(R.styleable.KeyValuePairView_android_minLines, UNSET_INT_VALUE);
            if (minLines != UNSET_INT_VALUE) {
                setMinLines(minLines);
            }

            int ellipseIndex = typedArray.getInt(R.styleable.KeyValuePairView_android_ellipsize, UNSET_INT_VALUE);
            if (ellipseIndex != UNSET_INT_VALUE) {
                TextUtils.TruncateAt ellipsize = TextUtils.TruncateAt.values()[ellipseIndex];
                setEllipsize(ellipsize);
            }

            typedArray.recycle();
        } else {
            initViewWithOrientation(inflater, LinearLayout.HORIZONTAL);
        }
    }

    private void initViewWithOrientation(LayoutInflater inflater, int orientation) {
        View rootView;
        if (orientation == LinearLayout.HORIZONTAL) {
            rootView = inflater.inflate(R.layout.view_key_value_pair_horizontal, this, true);
        } else {
            rootView = inflater.inflate(R.layout.view_key_value_pair_vertical, this, true);
        }
        container = rootView.findViewById(R.id.container);
        txtHint = rootView.findViewById(R.id.txt_key);
        txtValue = rootView.findViewById(R.id.txt_value);
    }

    @Override
    public void setGravity(int gravity) {
        container.setGravity(gravity);
    }

    public void setMaxLines(int lines) {
        txtValue.setMaxLines(lines);
    }

    public void setMinLines(int lines) {
        txtValue.setMinLines(lines);
    }

    public void setEllipsize(TextUtils.TruncateAt ellipsize) {
        txtValue.setEllipsize(ellipsize);
    }

    public void setTextColor(@ColorInt int colorInt) {
        txtValue.setTextColor(colorInt);
    }

    public void setTextColor(ColorStateList colors) {
        txtValue.setTextColor(colors);
    }

    public void setHintTextColor(@ColorInt int colorInt) {
        txtHint.setTextColor(colorInt);
    }

    public void setHintTextColor(ColorStateList colors) {
        txtHint.setTextColor(colors);
    }

    public void setHint(CharSequence text) {
        txtHint.setText(text);
    }

    public void setHint(@StringRes int textRes) {
        txtHint.setText(textRes);
    }

    public void setText(CharSequence text) {
        txtValue.setText(text);
    }

    public void setText(@StringRes int textRes) {
        txtValue.setText(textRes);
    }

    public String getHint() {
        return txtHint.getText().toString();
    }

    public String getText() {
        return txtValue.getText().toString();
    }
}
