package webid.barayuda.tastybakingapp.features.recipestep;

import android.support.v4.app.FragmentManager;

import webid.barayuda.tastybakingapp.basemvp.BaseView;
import webid.barayuda.tastybakingapp.model.Recipe;

/**
 * Created by BARAYUDA on 7/4/2017.
 */

public interface RecipeStepView extends BaseView {
    void bindData(Recipe recipe);
    FragmentManager getFragmentManagerFromFragment();
}
