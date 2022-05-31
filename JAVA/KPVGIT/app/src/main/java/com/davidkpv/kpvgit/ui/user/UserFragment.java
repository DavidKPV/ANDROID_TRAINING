package com.davidkpv.kpvgit.ui.user;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.davidkpv.kpvgit.R;
import com.davidkpv.kpvgit.binding.FragmentDataBindingComponent;
import com.davidkpv.kpvgit.databinding.FragmentUserBinding;
import com.davidkpv.kpvgit.di.Injectable;
import com.davidkpv.kpvgit.model.Repo;
import com.davidkpv.kpvgit.model.User;
import com.davidkpv.kpvgit.repository.Resource;
import com.davidkpv.kpvgit.ui.common.NavigationController;
import com.davidkpv.kpvgit.ui.common.RepoListAdapter;
import com.davidkpv.kpvgit.ui.common.RetryCall;
import com.davidkpv.kpvgit.utils.AutoClearedValue;

import java.util.List;

import javax.inject.Inject;


public class UserFragment extends Fragment implements Injectable {

    private static final String LOGIN_KEY = "login";

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    NavigationController navigationController;

    DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this, getActivity());

    private UserViewModel userViewModel;

    AutoClearedValue<FragmentUserBinding> binding;
    private AutoClearedValue<RepoListAdapter> adapter;

    public static UserFragment create(String login){
        UserFragment userFragment = new UserFragment();
        Bundle bundle = new Bundle();
        bundle.putString(LOGIN_KEY, login);
        userFragment.setArguments(bundle);
        return userFragment;
    }

    public UserFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentUserBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_user,
                container, false, null); // dataBindingComponent
        dataBinding.setRetryCallback(new RetryCall() {
            @Override
            public void retry() {
                userViewModel.retry();
            }
        });

        binding = new AutoClearedValue<>(this, dataBinding);
        return dataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        userViewModel = ViewModelProviders.of(this, viewModelFactory).get(UserViewModel.class);
        userViewModel.setLogin(getArguments().getString(LOGIN_KEY));
        userViewModel.getUser().observe(getViewLifecycleOwner(), new Observer<Resource<User>>() {
            @Override
            public void onChanged(Resource<User> userResource) {
                binding.get().setUser(userResource == null ? null : userResource.data);
                binding.get().setUserResource(userResource);
                binding.get().executePendingBindings();
            }
        });

        RepoListAdapter rvAdapter = new RepoListAdapter(dataBindingComponent, false, new RepoListAdapter.RepoClickCallback() {
            @Override
            public void onClick(Repo repo) {
                navigationController.navigateToRepo(repo.owner.login, repo.name);
            }
        });

        binding.get().repoList.setAdapter(rvAdapter);
        this.adapter = new AutoClearedValue<>(this, rvAdapter);
        initRepoList();
    }

    private void initRepoList(){
        userViewModel.getRepositories().observe(getViewLifecycleOwner(), new Observer<Resource<List<Repo>>>() {
            @Override
            public void onChanged(Resource<List<Repo>> repos) {
                if(repos == null){
                    adapter.get().replace(null);
                } else {
                    adapter.get().replace(repos.data);
                }
            }
        });
    }
}