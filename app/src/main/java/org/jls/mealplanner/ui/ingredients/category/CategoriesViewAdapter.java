package org.jls.mealplanner.ui.ingredients.category;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jls.mealplanner.R;
import org.jls.mealplanner.ingredient.CategoryItem;
import org.jls.mealplanner.ui.ingredients.ingredient.IngredientCategoryActivity;

import java.util.ArrayList;

public class CategoriesViewAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

    private RecyclerView recyclerView;
    private ArrayList<CategoryItem> categoryItems;

    public CategoriesViewAdapter(ArrayList<CategoryItem> items) {
        this.categoryItems = items;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ingredient_category, parent, false);
        addClickListenerToCategoryItems(parent, view);
        return new CategoryViewHolder(view);
    }

    private void addClickListenerToCategoryItems(@NonNull final ViewGroup parent, @NonNull View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CategoryItem categoryItem = getClickedCategoryItem(view);
                startIngredientCategoryActivity(categoryItem.getIngredientCategory());
            }

            private CategoryItem getClickedCategoryItem(@NonNull final View view) {
                int itemPosition = recyclerView.getChildLayoutPosition(view);
                return categoryItems.get(itemPosition);
            }

            private void startIngredientCategoryActivity(final IngredientCategory category) {
                Intent intent = new Intent(parent.getContext(), IngredientCategoryActivity.class);
                intent.putExtra("category", category.id());
                parent.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.getCategoryImageView().setImageResource(getCategoryItemAtPosition(position).getImageResource());
        holder.getCategoryNameTextView().setText(getCategoryItemAtPosition(position).getCategoryDisplayName());
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @Override
    public int getItemCount() {
        return categoryItems.size();
    }

    private CategoryItem getCategoryItemAtPosition(final int position) {
        return categoryItems.get(position);
    }
}
