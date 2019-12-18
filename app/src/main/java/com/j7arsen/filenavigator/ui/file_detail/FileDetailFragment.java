package com.j7arsen.filenavigator.ui.file_detail;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.j7arsen.domain.model.NavigationDataModel;
import com.j7arsen.filenavigator.R;
import com.j7arsen.filenavigator.base.BaseActivity;
import com.j7arsen.filenavigator.base.BaseFragment;
import com.j7arsen.filenavigator.di.ComponentManager;
import com.j7arsen.filenavigator.di.app.Global;
import com.j7arsen.filenavigator.utils.IBackButtonListener;

import javax.inject.Inject;

import butterknife.BindView;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import ru.terrakok.cicerone.Router;

public class FileDetailFragment extends BaseFragment implements IFileDetailView, IBackButtonListener {

    public static final String FILE_DATA = "FileDetailFragment.FILE_DATA";

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.navigation_bar)
    Toolbar navigationBar;
    @BindView(R.id.tv_file_content)
    TextView tvFileContent;

    @Inject
    @Global
    Router router;

    @InjectPresenter
    FileDetailPresenter presenter;

    @ProvidePresenter
    public FileDetailPresenter createFileDetailPresenter() {
        return new FileDetailPresenter(router, (NavigationDataModel) getArguments().getSerializable(FILE_DATA));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        ComponentManager.getInstance().getMainComponent().inject(this);
        super.onCreate(savedInstanceState);
    }

    public static FileDetailFragment newInstance(NavigationDataModel navigationDataModel) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(FILE_DATA, navigationDataModel);
        FileDetailFragment fragment = new FileDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
    }

    private void initToolbar() {
        BaseActivity baseActivity = (BaseActivity) getActivity();
        baseActivity.setSupportActionBar(navigationBar);
        if (baseActivity.getSupportActionBar() != null) {
            baseActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);
            baseActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            baseActivity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        navigationBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onBackPressed();
            }
        });
    }

    @Override
    public void showContent(NavigationDataModel navigationDataModel) {
        if (navigationDataModel != null) {
            toolbarTitle.setText(navigationDataModel.getName());
            tvFileContent.setText(navigationDataModel.getContent());
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_file_detail;
    }

    @Override
    public boolean onBackPressed() {
        presenter.onBackPressed();
        return true;
    }
}
