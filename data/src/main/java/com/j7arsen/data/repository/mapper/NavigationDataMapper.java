package com.j7arsen.data.repository.mapper;

import com.j7arsen.data.model.data.NavigationDataEntity;
import com.j7arsen.data.model.mapper.IObjectModelMapper;
import com.j7arsen.domain.model.NavigationDataModel;
import com.j7arsen.domain.model.NavigationImageType;
import com.j7arsen.domain.model.NavigationType;

import java.util.ArrayList;
import java.util.List;

public class NavigationDataMapper implements IObjectModelMapper<NavigationDataEntity, NavigationDataModel> {

    @Override
    public NavigationDataModel mapEntityToDomain(NavigationDataEntity navigationDataEntity) {
        NavigationDataModel navigationDataModel = new NavigationDataModel();
        navigationDataModel.setName(navigationDataEntity.getName());
        navigationDataModel.setType(navigationDataEntity.getType());
        if(navigationDataEntity.getContent() != null && !navigationDataEntity.getContent().isEmpty()){
            navigationDataModel.setContent(navigationDataEntity.getContent());
        } else{
            navigationDataModel.setContent(null);
        }
        navigationDataModel.setImage(getNavigationDataImage(navigationDataEntity));
        navigationDataModel.setNavigationDataModelList(mapEntityListToDomainList(navigationDataEntity.getNavigationDataEntityList()));
        return navigationDataModel;
    }

    public NavigationImageType getNavigationDataImage(NavigationDataEntity navigationDataEntity){
        if(navigationDataEntity.getType() == NavigationType.FOLDER){
            return NavigationImageType.FOLDER;
        } else{
            if(navigationDataEntity.getName().endsWith(".pdf")){
                return NavigationImageType.PDF;
            } else if(navigationDataEntity.getName().endsWith(".docs")){
                return NavigationImageType.DOCS;
            } else if(navigationDataEntity.getName().endsWith(".xlsx")){
                return NavigationImageType.EXCEL;
            } else{
                return NavigationImageType.FILE;
            }
        }
    }

    private List<NavigationDataModel> mapEntityListToDomainList(List<NavigationDataEntity> navigationDataEntityList){
        if(navigationDataEntityList != null && navigationDataEntityList.size() != 0){
            List<NavigationDataModel> navigationDataModelList = new ArrayList<>();
            for (int i= 0 ; i < navigationDataEntityList.size(); i ++ ){
                navigationDataModelList.add(mapEntityToDomain(navigationDataEntityList.get(i)));
            }
            return navigationDataModelList;
        } else{
            return null;
        }
    }

    @Override
    public NavigationDataEntity mapDomainToEntity(NavigationDataModel navigationDataModel) {
        NavigationDataEntity navigationDataEntity = new NavigationDataEntity();
        navigationDataEntity.setName(navigationDataModel.getName());
        navigationDataEntity.setType(navigationDataModel.getType());
        if(navigationDataModel.getContent() != null && !navigationDataModel.getContent().isEmpty()){
            navigationDataEntity.setContent(navigationDataModel.getContent());
        } else{
            navigationDataEntity.setContent(null);
        }
        navigationDataEntity.setNavigationDataEntityList(mapDomainListToEntityList(navigationDataModel.getNavigationDataModelList()));
        return navigationDataEntity;
    }

    private List<NavigationDataEntity> mapDomainListToEntityList(List<NavigationDataModel> navigationDataModelList){
        if(navigationDataModelList != null && navigationDataModelList.size() != 0){
            List<NavigationDataEntity> navigationDataEntityList = new ArrayList<>();
            for (int i= 0 ; i < navigationDataModelList.size(); i ++ ){
                navigationDataEntityList.add(mapDomainToEntity(navigationDataModelList.get(i)));
            }
            return navigationDataEntityList;
        } else{
            return null;
        }
    }

}
