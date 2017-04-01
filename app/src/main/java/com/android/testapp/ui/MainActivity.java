package com.android.testapp.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.testapp.R;
import com.android.testapp.adapter.OrganisationListAdapter;
import com.android.testapp.databinding.ActivityMainBinding;
import com.android.testapp.network.DataListener;
import com.android.testapp.network.RestError;
import com.android.testapp.network.model.Organisation;
import com.android.testapp.util.Util;
import com.android.testapp.viewmodel.OrganisationViewModel;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DataListener<List<Organisation>>, OrganisationListAdapter.OnItemClickListener
{

    private ActivityMainBinding mBinding;
    private int visibleItemCount;
    private int totalItemCount;
    private int pastVisiblesItems;
    private boolean loading;
    private OrganisationViewModel mViewModel;
    private List<Organisation> mOrganisationList = new ArrayList<>();
    private OrganisationListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mViewModel = new OrganisationViewModel(this, this);
        mViewModel.loadOrganisationList(0);
        setUpRecyclerView();
        setAdapter();
    }

    @Override
    public void onDataLoaded(List<Organisation> organisations) {
        loading = true;
        mBinding.progress.setVisibility(View.GONE);
        mOrganisationList.addAll(organisations);
        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void onFailed(RestError error) {
        loading = true;
        mBinding.progress.setVisibility(View.GONE);
        mBinding.emptyTextMsg.setVisibility(View.VISIBLE);
        Toast.makeText(this, error.getErrorMsg(), Toast.LENGTH_SHORT).show();
    }

    private void setAdapter()
    {
        mAdapter = new OrganisationListAdapter(this, mOrganisationList);
        mAdapter.setOnItemClickListener(this);
        mBinding.organisationListRv.setAdapter(mAdapter);
    }

    /**
     * Set up recycler view
     */
    private void setUpRecyclerView()
    {
        final LinearLayoutManager linearLayoutManager = getResources().getBoolean(R.bool.isTablet) ? new GridLayoutManager(this, 2) : new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mBinding.organisationListRv.setLayoutManager(linearLayoutManager);
        mBinding.organisationListRv.setNestedScrollingEnabled(true);
        mBinding.organisationListRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy > 0) //check for scroll down
                {
                    visibleItemCount = linearLayoutManager.getChildCount();
                    totalItemCount = linearLayoutManager.getItemCount();
                    pastVisiblesItems = linearLayoutManager.findFirstVisibleItemPosition();

                    if(loading)
                    {
                        if((visibleItemCount + pastVisiblesItems) >= (totalItemCount - 3))
                        {
                            loading = false;
                            int sinceId = Util.listNotNull(mOrganisationList) ? mOrganisationList.get(mOrganisationList.size() - 1).getId() : 0;
                            mViewModel.loadOrganisationList(sinceId);

                        }
                    }
                }
            }
        });
    }

    @Override
    public void onItemSelected(Object t) {
        if(t instanceof Organisation)
        {
            startActivity(new Intent(this, OrganisationDetailActivity.class));
            EventBus.getDefault().postSticky(t);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_anim)
        {
            startActivity(new Intent(this, CustomViewActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
