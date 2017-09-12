package webid.barayuda.tastybakingapp.features.recipedetail;

import android.support.v4.app.FragmentManager;

import webid.barayuda.tastybakingapp.basemvp.BaseView;
import webid.barayuda.tastybakingapp.model.Recipe;

/**
 * Created by BARAYUDA on 7/2/2017.
 */

public interface RecipeDetailView extends BaseView {
    void bindData(Recipe recipe);
    FragmentManager getFragmentManagerFromActivity();
}
