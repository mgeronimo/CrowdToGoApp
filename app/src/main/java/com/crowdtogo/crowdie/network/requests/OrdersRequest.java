package com.crowdtogo.crowdie.network.requests;

import com.crowdtogo.crowdie.model.OrdersResponse;
import com.crowdtogo.crowdie.network.UsersInterface;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

/**
 * Created by User on 12/5/2014.
 */
public class OrdersRequest extends RetrofitSpiceRequest<OrdersResponse,UsersInterface> {


    String crowdieId;

    public OrdersRequest(String crowdieId ){
        super(OrdersResponse.class, UsersInterface.class);
        this.crowdieId = crowdieId;
    }
    @Override
    public OrdersResponse loadDataFromNetwork() throws Exception {
        return getService().getOrder(crowdieId);
    }



}
