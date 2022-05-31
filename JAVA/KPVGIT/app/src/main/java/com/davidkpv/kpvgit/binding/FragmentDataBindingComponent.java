package com.davidkpv.kpvgit.binding;

import android.content.Context;

import androidx.databinding.DataBindingComponent;
import androidx.fragment.app.Fragment;

public class FragmentDataBindingComponent implements DataBindingComponent {

    private final FragmentBindingAdapters adapters;

    public FragmentDataBindingComponent(Fragment fragment, Context context){
        this.adapters = new FragmentBindingAdapters(fragment, context);
    }

    public FragmentBindingAdapters getFragmentBindingAdapters(){
        return adapters;
    }
}
