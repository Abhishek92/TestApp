package com.android.testapp.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.android.testapp.R;
import com.android.testapp.databinding.ActivityOrganisationBinding;
import com.android.testapp.network.model.Organisation;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class OrganisationDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        ActivityOrganisationBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_organisation);
        if(null != binding.toolbar) {
            setSupportActionBar(binding.toolbar);
        }
        if(null != getSupportActionBar())
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Organisation organisation = EventBus.getDefault().getStickyEvent(Organisation.class);
        if(null != organisation)
            binding.setData(organisation);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEvent(Organisation organisation)
    {
        //TODO
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
