package com.davidkpv.kpvgit.ui.search;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.IBinder;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.davidkpv.kpvgit.R;
import com.davidkpv.kpvgit.binding.FragmentDataBindingComponent;
import com.davidkpv.kpvgit.databinding.FragmentSearchBinding;
import com.davidkpv.kpvgit.di.Injectable;
import com.davidkpv.kpvgit.model.Repo;
import com.davidkpv.kpvgit.repository.Resource;
import com.davidkpv.kpvgit.ui.common.NavigationController;
import com.davidkpv.kpvgit.ui.common.RepoListAdapter;
import com.davidkpv.kpvgit.ui.common.RetryCall;
import com.davidkpv.kpvgit.utils.AutoClearedValue;

import java.util.List;

import javax.inject.Inject;

public class SearchFragment extends Fragment implements Injectable {

    @Inject
    ViewModelProvider.Factory viewModelProvider;

    @Inject
    NavigationController navigationController;

    DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this, getActivity());

    AutoClearedValue<FragmentSearchBinding> binding;
    AutoClearedValue<RepoListAdapter> adapter;

    private SearchViewModel searchViewModel;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentSearchBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search,
                container, false, null);
        binding = new AutoClearedValue<>(this, dataBinding);
        return dataBinding.getRoot();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        searchViewModel = ViewModelProviders.of(this, viewModelProvider).get(SearchViewModel.class);
        initRecyclerView();

        RepoListAdapter rvAdapter = new RepoListAdapter(dataBindingComponent, true, new RepoListAdapter.RepoClickCallback() {
            @Override
            public void onClick(Repo repo) {
                navigationController.navigateToRepo(repo.owner.login, repo.name);
            }
        });

        binding.get().repoList.setAdapter(rvAdapter);
        adapter = new AutoClearedValue<>(this, rvAdapter);

        initSearchInputListener();

        binding.get().setCallback(new RetryCall() {
            @Override
            public void retry() {
                searchViewModel.refresh();
            }
        });
    }

    private void initSearchInputListener(){
        binding.get().input.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    SearchFragment.this.doSearch(v);
                    return true;
                }
                return false;
            }
        });

        binding.get().input.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)){
                    doSearch(v);
                    return true;
                }
                return false;
            }
        });
    }

    private void doSearch(View v){
        String query = binding.get().input.getText().toString();
        dismissKeyBoard(v.getWindowToken());
        binding.get().setQuery(query);
        searchViewModel.setQuery(query);
    }

    private void initRecyclerView(){
        binding.get().repoList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int lastPosition = layoutManager.findLastVisibleItemPosition();
                if(lastPosition == adapter.get().getItemCount()-1){
                    searchViewModel.loadNextPage();
                }
            }
        });

        searchViewModel.getResults().observe(getViewLifecycleOwner(), new Observer<Resource<List<Repo>>>() {
            @Override
            public void onChanged(Resource<List<Repo>> result) {
                binding.get().setSearchResource(result);
                binding.get().setResultCount((result == null || result.data == null) ? 0 : result.data.size());
                adapter.get().replace(result == null ? null : result.data);
                binding.get().executePendingBindings();
            }
        });

        searchViewModel.getLoadMoreStatus().observe(getViewLifecycleOwner(), new Observer<SearchViewModel.LoadMoreState>() {
            @Override
            public void onChanged(SearchViewModel.LoadMoreState loadingMore) {
                if(loadingMore == null){
                    binding.get().setLoadingMore(false);
                } else {
                    binding.get().setLoadingMore(loadingMore.isRunning());
                    String error = loadingMore.getErrorMessageIfNoHandled();
                    if(error != null){
                        Log.d("TAG1", "Error en cargar mas [loadMore]");
                    }
                    // PARA ACTUALIZAR LA INTERFAZ
                    binding.get().executePendingBindings();
                }
            }
        });
    }

    // MÉTODO PARA OCULTAR EL TECLADO
    private void dismissKeyBoard(IBinder windowToken){
        FragmentActivity activity = getActivity();
        if(activity != null){
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(windowToken, 0);
        }
    }
}