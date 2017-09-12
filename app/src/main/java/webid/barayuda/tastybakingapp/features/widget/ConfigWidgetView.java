package webid.barayuda.tastybakingapp.features.widget;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.LoaderManager;

import java.util.List;

import webid.barayuda.tastybakingapp.basemvp.BaseView;
import webid.barayuda.tastybakingapp.model.Recipe;

/**
 * Created by BARAYUDA on 7/15/2017.
 */

public interface ConfigWidgetView extends BaseView {
    void onBind(List<Recipe> data);
    void onComplete();
    Context getContextFromAct();
    ContentResolver getContentResolverFromAct();
    LoaderManager getLoaderFromAct();
    SharedPreferences getPrefs();
}
