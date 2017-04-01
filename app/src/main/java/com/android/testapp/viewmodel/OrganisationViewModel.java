package com.android.testapp.viewmodel;

import android.content.Context;

import com.android.testapp.R;
import com.android.testapp.network.ApiRestClient;
import com.android.testapp.network.DataListener;
import com.android.testapp.network.RestCallback;
import com.android.testapp.network.RestError;
import com.android.testapp.network.model.Organisation;
import com.android.testapp.util.NetworkUtil;
import com.android.testapp.util.Util;

import java.util.List;


/**
 * Created by hp pc on 24-03-2017.
 */

public class OrganisationViewModel implements ViewModel {

    private Context mContext;
    private DataListener<List<Organisation>> mDataListener;

    public OrganisationViewModel(Context context, DataListener<List<Organisation>> dataListener) {
        mContext = context;
        mDataListener = dataListener;
    }

    public void loadOrganisationList(int since) {
        if(NetworkUtil.isConnectedToInternet(mContext)) {
            ApiRestClient.API_INSTANCE.getApiService().getListOfOrganisation(since).enqueue(new RestCallback<List<Organisation>>() {
                @Override
                public void onSuccess(List<Organisation> organisations) {
                    if (Util.listNotNull(organisations)) {
                        if (null != mDataListener)
                            mDataListener.onDataLoaded(organisations);

                    } else if (null != mDataListener) {
                        RestError error = new RestError();
                        error.setErrorMsg(mContext.getString(R.string.no_org_msg));
                        mDataListener.onFailed(error);
                    }
                }

                @Override
                public void onFailure(RestError restError) {
                    if (null != mDataListener)
                        mDataListener.onFailed(restError);
                }
            });
        }
        else
        {
            if(null != mDataListener) {
                RestError error = new RestError();
                error.setErrorMsg(mContext.getString(R.string.no_internet_message));
                mDataListener.onFailed(error);
            }
        }
    }

    @Override
    public void onDestroy() {

    }
}
