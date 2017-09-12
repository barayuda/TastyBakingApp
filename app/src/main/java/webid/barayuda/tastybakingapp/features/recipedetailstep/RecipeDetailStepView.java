package webid.barayuda.tastybakingapp.features.recipedetailstep;

import android.content.Context;
import android.widget.ImageView;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;

import webid.barayuda.tastybakingapp.basemvp.BaseView;
import webid.barayuda.tastybakingapp.model.Step;

/**
 * Created by BARAYUDA on 7/4/2017.
 */

public interface RecipeDetailStepView extends BaseView {
    void bindData(Step step);
    Context getContextFromFragment();
    void onPlayerSet(SimpleExoPlayer player, MediaSource mediaSource);
    void onImageSet();
    void onNoMediaAvailable();
    ImageView getImageView();
}
