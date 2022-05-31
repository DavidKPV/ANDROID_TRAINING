package com.davidkpv.kpvgit.ui.common;

import android.os.AsyncTask;
import android.view.ViewGroup;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// CLASE QUE NOS PERMITIRÁ ACTUALIZAR UNA LISTA EN CASO DE QUE LOS DATOS HAYAN CAMBIADO
// (CALCULAR ACTUALIZACIONES DE UN RECYCLE VIEW)
public abstract class DataBoundListAdapter<T, V extends ViewDataBinding> extends RecyclerView.Adapter<DataBoundViewHolder<V>> {

    private List<T> items;
    private int dataVersion = 0;

    @Override
    public DataBoundViewHolder<V> onCreateViewHolder(ViewGroup parent, int viewType) {
        V binding = createBinding(parent);
        return new DataBoundViewHolder<>(binding);
    }

    protected abstract V createBinding(ViewGroup parent);

    @Override
    public void onBindViewHolder(DataBoundViewHolder<V> holder, int position) {
        bind(holder.binding, items.get(position));
        holder.binding.executePendingBindings();
    }

    protected abstract void bind(V binding, T item);

    // PARA NOTIFICAR QUE SE HA SUFRIDO UN CAMBIO EN LA LISTA Y DE ESTA MANERA PODER ACTUALIZAR
    @MainThread
    public void replace(List<T> update){
        dataVersion++;
        if(items == null){
            if(update == null){
                return;
            }
            items = update;
            notifyDataSetChanged();
        } else if(update == null){
            int oldSize = items.size();
            items = null;
            notifyItemRangeRemoved(0, oldSize);
        } else {
            final int startVersion = dataVersion;
            final List<T> oldItems = items;
            new AsyncTask<Void, Void, DiffUtil.DiffResult>() {
                @Override
                protected DiffUtil.DiffResult doInBackground(Void... voids) {
                    return DiffUtil.calculateDiff(new DiffUtil.Callback() {
                        // NOS DEVUELVE EL TAMAÑO DE LA LISTA VIEJA
                        @Override
                        public int getOldListSize() {
                            return oldItems.size();
                        }

                        // NOS DEVUELVE EL TAMAÑO DE LA NUEVA LISTA
                        @Override
                        public int getNewListSize() {
                            return update.size();
                        }

                        // CUANDO LOS DATOS SON LOS MISMOS (LISTA NUEVA Y LISTA VIEJA)
                        @Override
                        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                            T oldItem = oldItems.get(oldItemPosition);
                            T newItem = update.get(newItemPosition);
                            return DataBoundListAdapter.this.areItemsTheSame(oldItem, newItem);
                        }

                        // ESTE MÉTODO VERIFICA SI DOS ITEMS TIENEN LOS MISMOS DATOS
                        @Override
                        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                            T oldItem = oldItems.get(oldItemPosition);
                            T newItem = update.get(newItemPosition);
                            return DataBoundListAdapter.this.areContentsTheSame(oldItem, newItem);
                        }
                    });
                }

                @Override
                protected void onPostExecute(DiffUtil.DiffResult diffResult) {
                    if(startVersion != dataVersion){
                        return;
                    }
                    items = update;
                    // NOTIFICAR AL ADAPTER EL CAMBIO
                    diffResult.dispatchUpdatesTo(DataBoundListAdapter.this);
                }
            }.execute();
        }
    }

    protected abstract boolean areItemsTheSame(T oldItem, T newItem);
    protected abstract boolean areContentsTheSame(T oldItem, T newItem);

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }
}
