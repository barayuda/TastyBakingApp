package webid.barayuda.tastybakingapp.adapter.viewholder;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import webid.barayuda.tastybakingapp.R;
import webid.barayuda.tastybakingapp.adapter.RecipeAdapter;
import webid.barayuda.tastybakingapp.features.recipedetail.RecipeDetailActivity;
import webid.barayuda.tastybakingapp.model.Recipe;

import static webid.barayuda.tastybakingapp.util.RecipeImageGenerator.getImage;

/**
 * Created by BARAYUDA on 8/27/2017.
 */

public class RecipeViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.img_recipe)ImageView img;
    @BindView(R.id.tv_recipe_name)TextView name;
    @BindView(R.id.tv_recipe_servings)TextView servings;

    public RecipeViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(final Recipe data, @Nullable final RecipeAdapter.RecipeClickFromWidgetListener clickFromWidget){
        if(data.getImage().equals("")){
            Glide.with(itemView.getContext())
                    .load(getImage(data.getId()))
                    .into(img);
        }else{
            Glide.with(itemView.getContext())
                    .load(data.getImage())
                    .placeholder(R.drawable.placeholder)
                    .into(img);
        }

        name.setText(data.getName());
        servings.setText(data.getResolvedServings());

        if(clickFromWidget == null){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent detail = new Intent(itemView.getContext(), RecipeDetailActivity.class);
                    detail.putExtra(Intent.EXTRA_TEXT, new Gson().toJson(data));
                    itemView.getContext().startActivity(detail);
                }
            });
        }else{
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("clickedFromWidget", data.getName());
                    clickFromWidget.onClickFromWidget(data);
                }
            });
        }

    }
}
