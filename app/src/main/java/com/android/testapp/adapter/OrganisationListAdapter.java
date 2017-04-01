package com.android.testapp.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.testapp.R;
import com.android.testapp.databinding.OrganisationListItemLayoutBinding;
import com.android.testapp.network.model.Organisation;

import java.util.List;

/**
 * Created by hp pc on 24-03-2017.
 */

public class OrganisationListAdapter extends RecyclerView.Adapter<OrganisationListAdapter.ViewHolder> {

    private Context mContext;
    private List<Organisation> mOrganisationList;
    private OnItemClickListener mListener;
    public OrganisationListAdapter(Context context, List<Organisation> organisationList)
    {
        mContext = context;
        mOrganisationList = organisationList;
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        mListener = listener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        OrganisationListItemLayoutBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.organisation_list_item_layout, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Organisation organisation = mOrganisationList.get(position);
        holder.bindData(organisation);
        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(null != mListener)
                    mListener.onItemSelected(organisation);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mOrganisationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private OrganisationListItemLayoutBinding binding;
        public ViewHolder(OrganisationListItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = DataBindingUtil.getBinding(binding.getRoot());
        }

        public void bindData(Organisation organisation) {
            if (null != binding) {
                binding.setData(organisation);
                binding.executePendingBindings();
            }
        }
    }

    public interface OnItemClickListener
    {
        public void onItemSelected(Object t);
    }
}
