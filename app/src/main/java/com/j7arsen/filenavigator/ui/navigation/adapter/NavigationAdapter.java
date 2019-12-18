package com.j7arsen.filenavigator.ui.navigation.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.j7arsen.domain.model.NavigationDataModel;
import com.j7arsen.filenavigator.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.ItemViewHolder> {

    private List<NavigationDataModel> navigationDataModelList;

    private OnItemClickListener onItemClickListener;

    public void setData(List<NavigationDataModel> navigationDataModelList) {
        this.navigationDataModelList = navigationDataModelList;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_navigation_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        if (navigationDataModelList != null && navigationDataModelList.size() != 0) {
            NavigationDataModel navigationDataModel = navigationDataModelList.get(position);
            if (navigationDataModel != null) {
                int imageRes = getImageForNavigationData(navigationDataModel);
                holder.ivNavigationIcon.setImageResource(imageRes);
                holder.tvNavigationName.setText(navigationDataModel.getName());
            }
        }
    }

    private int getImageForNavigationData(NavigationDataModel navigationDataModel) {
        switch (navigationDataModel.getImage()) {
            case DOCS:
                return R.drawable.ic_docs_black;
            case EXCEL:
                return R.drawable.ic_excel_black;
            case PDF:
                return R.drawable.ic_pdf_black;
            case FILE:
                return R.drawable.ic_file_black;
            case FOLDER:
                return R.drawable.ic_folder_black;
            default:
                return R.drawable.ic_undefined_black;
        }
    }

    @Override
    public int getItemCount() {
        return navigationDataModelList != null ? navigationDataModelList.size() : 0;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.iv_navigation_icon)
        ImageView ivNavigationIcon;
        @BindView(R.id.tv_navigation_name)
        TextView tvNavigationName;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(onItemClickListener != null){
                onItemClickListener.onItemClick(navigationDataModelList != null ? navigationDataModelList.get(getAdapterPosition()) : null);
            }
        }
    }

    public interface OnItemClickListener{

        void onItemClick(NavigationDataModel navigationDataModel);

    }

}
