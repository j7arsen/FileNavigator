package com.j7arsen.filenavigator.utils;


import androidx.fragment.app.Fragment;

import com.j7arsen.domain.model.NavigationDataModel;
import com.j7arsen.filenavigator.ui.file_detail.FileDetailFragment;
import com.j7arsen.filenavigator.ui.navigation.NavigationFragment;

import ru.terrakok.cicerone.android.support.SupportAppScreen;

public class Screens {

    public static final class FileDetailScreen extends SupportAppScreen {

        private NavigationDataModel navigationDataModel;

        public FileDetailScreen(NavigationDataModel navigationDataModel) {
            this.navigationDataModel = navigationDataModel;
        }

        @Override
        public Fragment getFragment() {
            return FileDetailFragment.newInstance(navigationDataModel);
        }
    }

    public static final class NavigatorScreen extends SupportAppScreen {

        private NavigationDataModel navigationDataModel;

        public NavigatorScreen(NavigationDataModel navigationDataModel) {
            this.navigationDataModel = navigationDataModel;
        }

        @Override
        public Fragment getFragment() {
            return NavigationFragment.newInstance(navigationDataModel);
        }
    }

}
