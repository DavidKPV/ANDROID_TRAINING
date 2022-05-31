package com.davidkpv.kpvgit.binding;

import android.content.Context;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

public class FragmentBindingAdapters {

    final Fragment fragment;
    final Context context;

    @Inject
    public FragmentBindingAdapters(Fragment fragment, Context context){
        this.fragment = fragment;
        this.context = context;
    }

    @BindingAdapter("imageUrl")
    public void bindImage(ImageView imageView, String url){
        // INDICAMOS EN DONDE QUEREMOS CARGAR LA IMAGEN CON GLIDE
        Glide.with(context).load(url).into(imageView);
    }
}
