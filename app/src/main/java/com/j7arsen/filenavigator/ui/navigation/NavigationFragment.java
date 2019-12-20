package com.j7arsen.filenavigator.ui.navigation;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.j7arsen.domain.model.NavigationDataModel;
import com.j7arsen.filenavigator.R;
import com.j7arsen.filenavigator.base.BaseActivity;
import com.j7arsen.filenavigator.base.BaseFragment;
import com.j7arsen.filenavigator.di.ComponentManager;
import com.j7arsen.filenavigator.di.app.Global;
import com.j7arsen.filenavigator.ui.navigation.adapter.NavigationAdapter;
import com.j7arsen.filenavigator.utils.IBackButtonListener;
import com.j7arsen.filenavigator.utils.ResUtils;
import com.j7arsen.filenavigator.utils.error.ErrorData;
import com.j7arsen.filenavigator.view.StubView;
import com.j7arsen.filenavigator.view.progress.ProgressView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import moxy.presenter.ProvidePresenterTag;
import ru.terrakok.cicerone.Router;

public class NavigationFragment extends BaseFragment implements INavigationView, IBackButtonListener, ProgressView.OnRetryListener, NavigationAdapter.OnItemClickListener {

    public static final String NAVIGATION_DATA = "NavigationFragment.NAVIGATION_DATA";

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.navigation_bar)
    Toolbar navigationBar;
    @BindView(R.id.rv_navigation_list)
    RecyclerView rvNavigationList;
    @BindView(R.id.tvv_navigation)
    StubView tvvNavigation;
    @BindView(R.id.rl_navigation_content)
    RelativeLayout rlNavigationContent;
    @BindView(R.id.pv_navigation)
    ProgressView pvNavigation;

    @Inject
    @Global
    Router router;

    @Inject
    ResUtils resUtils;

    @InjectPresenter
    NavigationPresenter presenter;

    private NavigationAdapter navigationAdapter;

    @ProvidePresenterTag(presenterClass = NavigationPresenter.class)
    String provideNavigationPresenterTag() {
        NavigationDataModel navigationDataModel = (NavigationDataModel) getArguments().getSerializable(NAVIGATION_DATA);
        return navigationDataModel != null ? navigationDataModel.getName() : "start";
    }

    @ProvidePresenter()
    public NavigationPresenter createNavigationPresenter() {
        return new NavigationPresenter(router, resUtils, (NavigationDataModel) getArguments().getSerializable(NAVIGATION_DATA));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        ComponentManager.getInstance().getMainComponent().inject(this);
        super.onCreate(savedInstanceState);
    }

    public static NavigationFragment newInstance(NavigationDataModel navigationDataModel) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(NAVIGATION_DATA, navigationDataModel);
        NavigationFragment fragment = new NavigationFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
        initAdapters();
        setListeners();
    }

    private void initToolbar() {
        NavigationDataModel navigationDataModel = (NavigationDataModel) getArguments().getSerializable(NAVIGATION_DATA);
        BaseActivity baseActivity = (BaseActivity) getActivity();
        baseActivity.setSupportActionBar(navigationBar);
        if (baseActivity.getSupportActionBar() != null) {
            baseActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);
            baseActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(navigationDataModel != null);
            baseActivity.getSupportActionBar().setDisplayShowHomeEnabled(navigationDataModel != null);
            if (navigationDataModel != null) {
                navigationBar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        presenter.onBackPressed();
                    }
                });
            }
            toolbarTitle.setText(navigationDataModel == null ? resUtils.getString(R.string.app_name) : navigationDataModel.getName());
        }
    }

    private void initAdapters() {
        navigationAdapter = new NavigationAdapter();
        LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvNavigationList.setLayoutManager(verticalLayoutManager);
        rvNavigationList.setAdapter(navigationAdapter);
    }

    private void setListeners() {
        pvNavigation.setOnRetryListener(this);
        navigationAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onRetry() {
        presenter.loadNavigationData();
    }

    @Override
    public void onItemClick(NavigationDataModel navigationDataModel) {
        presenter.onItemClick(navigationDataModel);
    }

    @Override
    public void setData(List<NavigationDataModel> navigationDataModelList) {
        if(navigationDataModelList != null && navigationDataModelList.size() != 0) {
            hideEmptyListView();
            if (navigationAdapter != null) {
                navigationAdapter.setData(navigationDataModelList);
            }
        } else{
            showEmptyListView();
        }
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        if (isAdded()) {
            rlNavigationContent.setVisibility(View.GONE);
            pvNavigation.startLoading();
        }
    }

    @Override
    public void hideProgress() {
        if (isAdded()) {
            rlNavigationContent.setVisibility(View.VISIBLE);
            pvNavigation.completeLoading();
        }
    }

    @Override
    public void showError(ErrorData errorData) {
        if (isAdded()) {
            rlNavigationContent.setVisibility(View.GONE);
            pvNavigation.errorLoading(errorData.getErrorMessage());
        }
    }

    @Override
    public void showEmptyListView() {
        rvNavigationList.setVisibility(View.GONE);
        tvvNavigation.show();
    }

    @Override
    public void hideEmptyListView() {
        tvvNavigation.hide();
        rvNavigationList.setVisibility(View.VISIBLE);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_navigation;
    }

    @Override
    public boolean onBackPressed() {
        presenter.onBackPressed();
        return true;
    }
}
