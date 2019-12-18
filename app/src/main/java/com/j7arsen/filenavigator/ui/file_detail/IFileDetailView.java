package com.j7arsen.filenavigator.ui.file_detail;

import com.j7arsen.domain.model.NavigationDataModel;

import moxy.MvpView;
import moxy.viewstate.strategy.SingleStateStrategy;
import moxy.viewstate.strategy.StateStrategyType;

public interface IFileDetailView extends MvpView {

    @StateStrategyType(SingleStateStrategy.class)
    void showContent(NavigationDataModel navigationDataModel);

}
