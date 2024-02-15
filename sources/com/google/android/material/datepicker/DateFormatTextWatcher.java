package com.google.android.material.datepicker;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.google.android.material.R;
import com.google.android.material.internal.TextWatcherAdapter;
import com.google.android.material.textfield.TextInputLayout;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import kotlin.text.Typography;

/* loaded from: classes3.dex */
public abstract class DateFormatTextWatcher extends TextWatcherAdapter {
    private static final int VALIDATION_DELAY = 1000;
    private final CalendarConstraints constraints;
    private final DateFormat dateFormat;
    private final String outOfRange;
    private final Runnable setErrorCallback;
    private Runnable setRangeErrorCallback;
    private final TextInputLayout textInputLayout;

    void onInvalidDate() {
    }

    abstract void onValidDate(Long l);

    public DateFormatTextWatcher(final String str, DateFormat dateFormat, TextInputLayout textInputLayout, CalendarConstraints calendarConstraints) {
        this.dateFormat = dateFormat;
        this.textInputLayout = textInputLayout;
        this.constraints = calendarConstraints;
        this.outOfRange = textInputLayout.getContext().getString(R.string.mtrl_picker_out_of_range);
        this.setErrorCallback = new Runnable() { // from class: com.google.android.material.datepicker.DateFormatTextWatcher$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                DateFormatTextWatcher.this.m158x5657fb8e(str);
            }
        };
    }

    /* renamed from: lambda$new$0$com-google-android-material-datepicker-DateFormatTextWatcher */
    public /* synthetic */ void m158x5657fb8e(String str) {
        TextInputLayout textInputLayout = this.textInputLayout;
        DateFormat dateFormat = this.dateFormat;
        Context context = textInputLayout.getContext();
        String string = context.getString(R.string.mtrl_picker_invalid_format);
        textInputLayout.setError(string + "\n" + String.format(context.getString(R.string.mtrl_picker_invalid_format_use), sanitizeDateString(str)) + "\n" + String.format(context.getString(R.string.mtrl_picker_invalid_format_example), sanitizeDateString(dateFormat.format(new Date(UtcDates.getTodayCalendar().getTimeInMillis())))));
        onInvalidDate();
    }

    @Override // com.google.android.material.internal.TextWatcherAdapter, android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.textInputLayout.removeCallbacks(this.setErrorCallback);
        this.textInputLayout.removeCallbacks(this.setRangeErrorCallback);
        this.textInputLayout.setError(null);
        onValidDate(null);
        if (TextUtils.isEmpty(charSequence)) {
            return;
        }
        try {
            Date parse = this.dateFormat.parse(charSequence.toString());
            this.textInputLayout.setError(null);
            long time = parse.getTime();
            if (this.constraints.getDateValidator().isValid(time) && this.constraints.isWithinBounds(time)) {
                onValidDate(Long.valueOf(parse.getTime()));
                return;
            }
            Runnable createRangeErrorCallback = createRangeErrorCallback(time);
            this.setRangeErrorCallback = createRangeErrorCallback;
            runValidation(this.textInputLayout, createRangeErrorCallback);
        } catch (ParseException unused) {
            runValidation(this.textInputLayout, this.setErrorCallback);
        }
    }

    private Runnable createRangeErrorCallback(final long j) {
        return new Runnable() { // from class: com.google.android.material.datepicker.DateFormatTextWatcher$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                DateFormatTextWatcher.this.m157x14d77527(j);
            }
        };
    }

    /* renamed from: lambda$createRangeErrorCallback$1$com-google-android-material-datepicker-DateFormatTextWatcher */
    public /* synthetic */ void m157x14d77527(long j) {
        this.textInputLayout.setError(String.format(this.outOfRange, sanitizeDateString(DateStrings.getDateString(j))));
        onInvalidDate();
    }

    private String sanitizeDateString(String str) {
        return str.replace(' ', Typography.nbsp);
    }

    public void runValidation(View view, Runnable runnable) {
        view.postDelayed(runnable, 1000L);
    }
}
