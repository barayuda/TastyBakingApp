package webid.barayuda.tastybakingapp.features.recipelist;

import android.content.ContentResolver;
import android.content.Context;
import android.support.v4.app.LoaderManager;

import java.util.List;

import webid.barayuda.tastybakingapp.basemvp.BaseView;
import webid.barayuda.tastybakingapp.model.Recipe;

/**
 * Created by BARAYUDA on 8/27/2017.
 */

public interface RecipeListView extends BaseView {
    void onDataReceived(List<Recipe> data);
    void onWarningMessageReceived(String message);
    void onDataLoading();
    ContentResolver getResolver();
    Context getContext();
    LoaderManager getLoaderManagerFromActivity();
}
