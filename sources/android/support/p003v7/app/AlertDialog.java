package android.support.p003v7.app;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.ArrayRes;
import android.support.annotation.AttrRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.p003v7.app.AlertController;
import android.support.p003v7.appcompat.C0257R;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

/* renamed from: android.support.v7.app.AlertDialog */
public class AlertDialog extends AppCompatDialog implements DialogInterface {
    static final int LAYOUT_HINT_NONE = 0;
    static final int LAYOUT_HINT_SIDE = 1;
    final AlertController mAlert;

    protected AlertDialog(@NonNull Context context) {
        this(context, 0);
    }

    protected AlertDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, resolveDialogTheme(context, themeResId));
        this.mAlert = new AlertController(getContext(), this, getWindow());
    }

    protected AlertDialog(@NonNull Context context, boolean cancelable, @Nullable DialogInterface.OnCancelListener cancelListener) {
        this(context, 0);
        setCancelable(cancelable);
        setOnCancelListener(cancelListener);
    }

    static int resolveDialogTheme(@NonNull Context context, @StyleRes int resid) {
        if (((resid >>> 24) & 255) >= 1) {
            return resid;
        }
        TypedValue outValue = new TypedValue();
        context.getTheme().resolveAttribute(C0257R.attr.alertDialogTheme, outValue, true);
        return outValue.resourceId;
    }

    public Button getButton(int whichButton) {
        return this.mAlert.getButton(whichButton);
    }

    public ListView getListView() {
        return this.mAlert.getListView();
    }

    public void setTitle(CharSequence title) {
        super.setTitle(title);
        this.mAlert.setTitle(title);
    }

    public void setCustomTitle(View customTitleView) {
        this.mAlert.setCustomTitle(customTitleView);
    }

    public void setMessage(CharSequence message) {
        this.mAlert.setMessage(message);
    }

    public void setView(View view) {
        this.mAlert.setView(view);
    }

    public void setView(View view, int viewSpacingLeft, int viewSpacingTop, int viewSpacingRight, int viewSpacingBottom) {
        this.mAlert.setView(view, viewSpacingLeft, viewSpacingTop, viewSpacingRight, viewSpacingBottom);
    }

    /* access modifiers changed from: package-private */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setButtonPanelLayoutHint(int layoutHint) {
        this.mAlert.setButtonPanelLayoutHint(layoutHint);
    }

    public void setButton(int whichButton, CharSequence text, Message msg) {
        this.mAlert.setButton(whichButton, text, (DialogInterface.OnClickListener) null, msg, (Drawable) null);
    }

    public void setButton(int whichButton, CharSequence text, DialogInterface.OnClickListener listener) {
        this.mAlert.setButton(whichButton, text, listener, (Message) null, (Drawable) null);
    }

    public void setButton(int whichButton, CharSequence text, Drawable icon, DialogInterface.OnClickListener listener) {
        this.mAlert.setButton(whichButton, text, listener, (Message) null, icon);
    }

    public void setIcon(int resId) {
        this.mAlert.setIcon(resId);
    }

    public void setIcon(Drawable icon) {
        this.mAlert.setIcon(icon);
    }

    public void setIconAttribute(int attrId) {
        TypedValue out = new TypedValue();
        getContext().getTheme().resolveAttribute(attrId, out, true);
        this.mAlert.setIcon(out.resourceId);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mAlert.installContent();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (this.mAlert.onKeyDown(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (this.mAlert.onKeyUp(keyCode, event)) {
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    /* renamed from: android.support.v7.app.AlertDialog$Builder */
    public static class Builder {

        /* renamed from: P */
        private final AlertController.AlertParams f21P;
        private final int mTheme;

        public Builder(@NonNull Context context) {
            this(context, AlertDialog.resolveDialogTheme(context, 0));
        }

        public Builder(@NonNull Context context, @StyleRes int themeResId) {
            this.f21P = new AlertController.AlertParams(new ContextThemeWrapper(context, AlertDialog.resolveDialogTheme(context, themeResId)));
            this.mTheme = themeResId;
        }

        @NonNull
        public Context getContext() {
            return this.f21P.mContext;
        }

        public Builder setTitle(@StringRes int titleId) {
            this.f21P.mTitle = this.f21P.mContext.getText(titleId);
            return this;
        }

        public Builder setTitle(@Nullable CharSequence title) {
            this.f21P.mTitle = title;
            return this;
        }

        public Builder setCustomTitle(@Nullable View customTitleView) {
            this.f21P.mCustomTitleView = customTitleView;
            return this;
        }

        public Builder setMessage(@StringRes int messageId) {
            this.f21P.mMessage = this.f21P.mContext.getText(messageId);
            return this;
        }

        public Builder setMessage(@Nullable CharSequence message) {
            this.f21P.mMessage = message;
            return this;
        }

        public Builder setIcon(@DrawableRes int iconId) {
            this.f21P.mIconId = iconId;
            return this;
        }

        public Builder setIcon(@Nullable Drawable icon) {
            this.f21P.mIcon = icon;
            return this;
        }

        public Builder setIconAttribute(@AttrRes int attrId) {
            TypedValue out = new TypedValue();
            this.f21P.mContext.getTheme().resolveAttribute(attrId, out, true);
            this.f21P.mIconId = out.resourceId;
            return this;
        }

        public Builder setPositiveButton(@StringRes int textId, DialogInterface.OnClickListener listener) {
            this.f21P.mPositiveButtonText = this.f21P.mContext.getText(textId);
            this.f21P.mPositiveButtonListener = listener;
            return this;
        }

        public Builder setPositiveButton(CharSequence text, DialogInterface.OnClickListener listener) {
            this.f21P.mPositiveButtonText = text;
            this.f21P.mPositiveButtonListener = listener;
            return this;
        }

        public Builder setPositiveButtonIcon(Drawable icon) {
            this.f21P.mPositiveButtonIcon = icon;
            return this;
        }

        public Builder setNegativeButton(@StringRes int textId, DialogInterface.OnClickListener listener) {
            this.f21P.mNegativeButtonText = this.f21P.mContext.getText(textId);
            this.f21P.mNegativeButtonListener = listener;
            return this;
        }

        public Builder setNegativeButton(CharSequence text, DialogInterface.OnClickListener listener) {
            this.f21P.mNegativeButtonText = text;
            this.f21P.mNegativeButtonListener = listener;
            return this;
        }

        public Builder setNegativeButtonIcon(Drawable icon) {
            this.f21P.mNegativeButtonIcon = icon;
            return this;
        }

        public Builder setNeutralButton(@StringRes int textId, DialogInterface.OnClickListener listener) {
            this.f21P.mNeutralButtonText = this.f21P.mContext.getText(textId);
            this.f21P.mNeutralButtonListener = listener;
            return this;
        }

        public Builder setNeutralButton(CharSequence text, DialogInterface.OnClickListener listener) {
            this.f21P.mNeutralButtonText = text;
            this.f21P.mNeutralButtonListener = listener;
            return this;
        }

        public Builder setNeutralButtonIcon(Drawable icon) {
            this.f21P.mNeutralButtonIcon = icon;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            this.f21P.mCancelable = cancelable;
            return this;
        }

        public Builder setOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
            this.f21P.mOnCancelListener = onCancelListener;
            return this;
        }

        public Builder setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
            this.f21P.mOnDismissListener = onDismissListener;
            return this;
        }

        public Builder setOnKeyListener(DialogInterface.OnKeyListener onKeyListener) {
            this.f21P.mOnKeyListener = onKeyListener;
            return this;
        }

        public Builder setItems(@ArrayRes int itemsId, DialogInterface.OnClickListener listener) {
            this.f21P.mItems = this.f21P.mContext.getResources().getTextArray(itemsId);
            this.f21P.mOnClickListener = listener;
            return this;
        }

        public Builder setItems(CharSequence[] items, DialogInterface.OnClickListener listener) {
            this.f21P.mItems = items;
            this.f21P.mOnClickListener = listener;
            return this;
        }

        public Builder setAdapter(ListAdapter adapter, DialogInterface.OnClickListener listener) {
            this.f21P.mAdapter = adapter;
            this.f21P.mOnClickListener = listener;
            return this;
        }

        public Builder setCursor(Cursor cursor, DialogInterface.OnClickListener listener, String labelColumn) {
            this.f21P.mCursor = cursor;
            this.f21P.mLabelColumn = labelColumn;
            this.f21P.mOnClickListener = listener;
            return this;
        }

        public Builder setMultiChoiceItems(@ArrayRes int itemsId, boolean[] checkedItems, DialogInterface.OnMultiChoiceClickListener listener) {
            this.f21P.mItems = this.f21P.mContext.getResources().getTextArray(itemsId);
            this.f21P.mOnCheckboxClickListener = listener;
            this.f21P.mCheckedItems = checkedItems;
            this.f21P.mIsMultiChoice = true;
            return this;
        }

        public Builder setMultiChoiceItems(CharSequence[] items, boolean[] checkedItems, DialogInterface.OnMultiChoiceClickListener listener) {
            this.f21P.mItems = items;
            this.f21P.mOnCheckboxClickListener = listener;
            this.f21P.mCheckedItems = checkedItems;
            this.f21P.mIsMultiChoice = true;
            return this;
        }

        public Builder setMultiChoiceItems(Cursor cursor, String isCheckedColumn, String labelColumn, DialogInterface.OnMultiChoiceClickListener listener) {
            this.f21P.mCursor = cursor;
            this.f21P.mOnCheckboxClickListener = listener;
            this.f21P.mIsCheckedColumn = isCheckedColumn;
            this.f21P.mLabelColumn = labelColumn;
            this.f21P.mIsMultiChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(@ArrayRes int itemsId, int checkedItem, DialogInterface.OnClickListener listener) {
            this.f21P.mItems = this.f21P.mContext.getResources().getTextArray(itemsId);
            this.f21P.mOnClickListener = listener;
            this.f21P.mCheckedItem = checkedItem;
            this.f21P.mIsSingleChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(Cursor cursor, int checkedItem, String labelColumn, DialogInterface.OnClickListener listener) {
            this.f21P.mCursor = cursor;
            this.f21P.mOnClickListener = listener;
            this.f21P.mCheckedItem = checkedItem;
            this.f21P.mLabelColumn = labelColumn;
            this.f21P.mIsSingleChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(CharSequence[] items, int checkedItem, DialogInterface.OnClickListener listener) {
            this.f21P.mItems = items;
            this.f21P.mOnClickListener = listener;
            this.f21P.mCheckedItem = checkedItem;
            this.f21P.mIsSingleChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(ListAdapter adapter, int checkedItem, DialogInterface.OnClickListener listener) {
            this.f21P.mAdapter = adapter;
            this.f21P.mOnClickListener = listener;
            this.f21P.mCheckedItem = checkedItem;
            this.f21P.mIsSingleChoice = true;
            return this;
        }

        public Builder setOnItemSelectedListener(AdapterView.OnItemSelectedListener listener) {
            this.f21P.mOnItemSelectedListener = listener;
            return this;
        }

        public Builder setView(int layoutResId) {
            this.f21P.mView = null;
            this.f21P.mViewLayoutResId = layoutResId;
            this.f21P.mViewSpacingSpecified = false;
            return this;
        }

        public Builder setView(View view) {
            this.f21P.mView = view;
            this.f21P.mViewLayoutResId = 0;
            this.f21P.mViewSpacingSpecified = false;
            return this;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        @Deprecated
        public Builder setView(View view, int viewSpacingLeft, int viewSpacingTop, int viewSpacingRight, int viewSpacingBottom) {
            this.f21P.mView = view;
            this.f21P.mViewLayoutResId = 0;
            this.f21P.mViewSpacingSpecified = true;
            this.f21P.mViewSpacingLeft = viewSpacingLeft;
            this.f21P.mViewSpacingTop = viewSpacingTop;
            this.f21P.mViewSpacingRight = viewSpacingRight;
            this.f21P.mViewSpacingBottom = viewSpacingBottom;
            return this;
        }

        @Deprecated
        public Builder setInverseBackgroundForced(boolean useInverseBackground) {
            this.f21P.mForceInverseBackground = useInverseBackground;
            return this;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Builder setRecycleOnMeasureEnabled(boolean enabled) {
            this.f21P.mRecycleOnMeasure = enabled;
            return this;
        }

        public AlertDialog create() {
            AlertDialog dialog = new AlertDialog(this.f21P.mContext, this.mTheme);
            this.f21P.apply(dialog.mAlert);
            dialog.setCancelable(this.f21P.mCancelable);
            if (this.f21P.mCancelable) {
                dialog.setCanceledOnTouchOutside(true);
            }
            dialog.setOnCancelListener(this.f21P.mOnCancelListener);
            dialog.setOnDismissListener(this.f21P.mOnDismissListener);
            if (this.f21P.mOnKeyListener != null) {
                dialog.setOnKeyListener(this.f21P.mOnKeyListener);
            }
            return dialog;
        }

        public AlertDialog show() {
            AlertDialog dialog = create();
            dialog.show();
            return dialog;
        }
    }
}
