package webid.barayuda.tastybakingapp.features.recipedetail;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.google.gson.Gson;

import webid.barayuda.tastybakingapp.R;
import webid.barayuda.tastybakingapp.basemvp.BasePresenter;
import webid.barayuda.tastybakingapp.features.recipedetailstep.RecipeDetailStepFragment;
import webid.barayuda.tastybakingapp.features.recipestep.RecipeStepFragment;
import webid.barayuda.tastybakingapp.model.Recipe;

/**
 * Created by BARAYUDA on 7/2/2017.
 */

public class RecipeDetailPresenter implements BasePresenter<RecipeDetailView> {

    private static final String TAG = RecipeDetailPresenter.class.getSimpleName();
    private RecipeDetailView view;
    private Gson gson;

    @Override
    public void onAttach(RecipeDetailView BaseView) {
        view = BaseView;
        gson = new Gson();
    }

    @Override
    public void onDetach() {
        gson = null;
        view = null;
    }

    //navigate from detail step
    void replaceFragment(Fragment fragment){
        view.getFragmentManagerFromActivity().beginTransaction()
                .replace(R.id.container, fragment, RecipeDetailStepFragment.class.getSimpleName())
                .commit();
    }

    void addFragment(Fragment fragment){
        String fragmentTag = "";
        if(fragment instanceof RecipeStepFragment){
            fragmentTag = RecipeStepFragment.class.getSimpleName();
        }else if(fragment instanceof RecipeDetailStepFragment){
            fragmentTag = RecipeDetailStepFragment.class.getSimpleName();
        }

        if(view.getFragmentManagerFromActivity().findFragmentByTag(RecipeStepFragment.class.getSimpleName()) == null){
            view.getFragmentManagerFromActivity().beginTransaction()
                    .add(R.id.container, fragment, fragmentTag)
                    //.addToBackStack(fragmentTag)
                    .commit();
            view.getFragmentManagerFromActivity().executePendingTransactions();
        }
    }

    void addFragments(Fragment left, Fragment right){
        view.getFragmentManagerFromActivity().beginTransaction()
                .add(R.id.container_left, left)
                .add(R.id.container_right, right)
                .commit();
        view.getFragmentManagerFromActivity().executePendingTransactions();
    }

    void changeFragmentRight(Fragment fragment){
        view.getFragmentManagerFromActivity().beginTransaction()
                .replace(R.id.container_right, fragment)
                .commit();
    }

    Fragment getStepDetailFragment(@Nullable String json,
                                   int currentStep,
                                   int totalStep,
                                   int previousStep,
                                   int nextStep){
        return RecipeDetailStepFragment.newInstance(json, currentStep, totalStep, previousStep, nextStep);
    }

    Fragment getStepFragment(String json, boolean isTwoPane){
        return RecipeStepFragment.newInstance(json, isTwoPane);
    }

    void getRecipeModel(String json){
        view.bindData(gson.fromJson(json, Recipe.class));
    }

    String getStepJsonByIndex(String jsonRecipe, int indexID){
        Recipe recipe = gson.fromJson(jsonRecipe, Recipe.class);
        int pos = 0;
        for(int i=0; i<recipe.getSteps().size(); i++){
            if(recipe.getSteps().get(i).getId() == indexID){
                pos = i;
            }
        }
        return gson.toJson(recipe.getSteps().get(pos));
    }

    int getPreviousStepIDByTargetID(String jsonRecipe, int targetID){
        Recipe recipe = gson.fromJson(jsonRecipe, Recipe.class);
        int pos = 0;
        for(int i=0; i<recipe.getSteps().size(); i++){
            if(recipe.getSteps().get(i).getId() == targetID){
                pos = i;
            }
        }

        //Log.d(TAG, "getPreviousStepIDByTargetID pos: "+pos);
        if(pos > 0){
            //pos in range 1 - end
            return recipe.getSteps().get(pos-1).getId();
        }else if(pos == 0){
            //pos is minimum
            return recipe.getSteps().get(pos).getId();
        }else{
            return 0;
        }
        //return recipe.getSteps().get(pos-1).getId();
    }

    int getNextStepIDByTargetID(String jsonRecipe, int targetID){
        //Log.d("targetID", ""+targetID);
        Recipe recipe = gson.fromJson(jsonRecipe, Recipe.class);
        int pos = 0;
        for(int i=0; i<recipe.getSteps().size(); i++){
            if(recipe.getSteps().get(i).getId() == targetID){
                pos = i;
            }
        }

        //Log.d(TAG, "getNextStepIDByTargetID pos: "+pos);
        if(pos == recipe.getSteps().size()-1) {
            //size 5, index 4, pos = 4, return id of that pos
            return recipe.getSteps().get(pos).getId();
        }else{
            return recipe.getSteps().get(pos+1).getId();
        }
        //return recipe.getSteps().get(pos+1).getId();
    }
}
