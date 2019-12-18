package com.j7arsen.filenavigator.ui.navigation;

import com.j7arsen.domain.model.NavigationDataModel;
import com.j7arsen.filenavigator.utils.error.ErrorData;

import java.util.List;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.SingleStateStrategy;
import moxy.viewstate.strategy.StateStrategyType;

public interface INavigationView extends MvpView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showProgress();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void hideProgress();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showError(ErrorData errorData);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showEmptyListView();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void hideEmptyListView();

    @StateStrategyType(SingleStateStrategy.class)
    void setData(List<NavigationDataModel> navigationDataModelList);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showMessage(String message);

}
