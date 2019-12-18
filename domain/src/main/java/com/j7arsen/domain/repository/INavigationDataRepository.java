package com.j7arsen.domain.repository;

import com.j7arsen.domain.model.NavigationDataModel;

import io.reactivex.Observable;

public interface INavigationDataRepository {

    Observable<NavigationDataModel> getNavigationData();

}
