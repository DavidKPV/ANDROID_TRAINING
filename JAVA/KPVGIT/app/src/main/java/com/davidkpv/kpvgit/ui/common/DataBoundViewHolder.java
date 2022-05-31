package com.davidkpv.kpvgit.ui.common;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public class DataBoundViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {
    public final T binding;

    // CONSTRUCTOR
    DataBoundViewHolder(T binding){
        super(binding.getRoot());
        this.binding = binding;
    }
}
