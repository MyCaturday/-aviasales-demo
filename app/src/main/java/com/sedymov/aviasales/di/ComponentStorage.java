package com.sedymov.aviasales.di;

import com.sedymov.aviasales.di.app.AppComponent;
import com.sedymov.aviasales.di.app.DaggerAppComponent;
import com.sedymov.aviasales.di.main.MainComponent;
import com.sedymov.aviasales.di.search.SearchComponent;

public final class ComponentStorage {

    private final Object mLock = new Object();

    private static volatile ComponentStorage instance;

    private volatile AppComponent appComponent;
    private volatile MainComponent mainComponent;
    private volatile SearchComponent searchComponent;

    private ComponentStorage() {
    }

    public static ComponentStorage getInstance() {

        ComponentStorage localInstance = instance;

        if (localInstance == null) {

            synchronized (ComponentStorage.class) {

                localInstance = instance;

                if (localInstance == null) {

                    instance = localInstance = new ComponentStorage();
                }
            }
        }

        return localInstance;
    }

    //<editor-fold desc="AppComponent">
    private AppComponent getAppComponent() {

        AppComponent localInstance = appComponent;

        if (localInstance == null) {

            synchronized (mLock) {

                localInstance = appComponent;

                if (localInstance == null) {

                    appComponent = localInstance =
                            DaggerAppComponent.builder().build();
                }
            }
        }

        return localInstance;
    }

    private void clearAppComponent() {

        synchronized (mLock) {

            appComponent = null;
        }
    }
    //</editor-fold>

    //<editor-fold desc="MainComponent">
    public MainComponent getMainComponent() {

        MainComponent localInstance = mainComponent;

        if (localInstance == null) {

            synchronized (mLock) {

                localInstance = mainComponent;

                if (localInstance == null) {

                    mainComponent = localInstance = getAppComponent().mainComponentBuilder().build();
                }
            }
        }

        return localInstance;
    }

    public void clearMainComponent() {

        synchronized (mLock) {

            mainComponent = null;
        }
    }
    //</editor-fold>

    //<editor-fold desc="SearchComponent">
    public SearchComponent getSearchComponent() {

        SearchComponent localInstance = searchComponent;

        if (localInstance == null) {

            synchronized (mLock) {

                localInstance = searchComponent;

                if (localInstance == null) {

                    searchComponent = localInstance = getAppComponent().searchComponentBuilder().build();
                }
            }
        }

        return localInstance;
    }

    public void clearSearchComponent() {

        synchronized (mLock) {

            searchComponent = null;
        }
    }
    //</editor-fold>
}
