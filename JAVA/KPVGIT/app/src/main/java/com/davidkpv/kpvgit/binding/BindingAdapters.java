package com.davidkpv.kpvgit.binding;

import android.view.View;

import androidx.databinding.BindingAdapter;

public class BindingAdapters {

    @BindingAdapter("visibleGone")
    public static void showOrHide(View view, boolean show){
        view.setVisibility(show ? View.VISIBLE : view.GONE);
    }
}
