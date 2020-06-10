package com.example.waqas.dashboardapp.Helper;

import androidx.fragment.app.FragmentManager;

import com.example.waqas.dashboardapp.Interface.NavigationManager;
import com.example.waqas.dashboardapp.MainActivity;

public class FragmentNavigationManager implements NavigationManager {

    private static FragmentNavigationManager mInstance;

    private FragmentManager mFragmentManager;
    private MainActivity mainActivity;

    public static FragmentNavigationManager getmInstance(MainActivity mainActivity){
        if(mInstance == null){
            mInstance = new FragmentNavigationManager();
        }
        mInstance.configure(mainActivity);
        return mInstance;
    }

    private void configure(MainActivity mainActivity){
        mainActivity = mainActivity;
        mFragmentManager = mainActivity.getSupportFragmentManager();
    }

    @Override
    public void showFragment(String title) {

    }
}
