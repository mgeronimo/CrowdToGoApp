package com.crowdtogo.crowdie.network.requests;

/**
 * Created by Alj on 12/19/2014.
 */

//Request for Accept and Reject Group Orders
import com.crowdtogo.crowdie.model.SuccessResponse;
import com.crowdtogo.crowdie.network.OrdersConfirmation;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;
public class ConfirmationRequest extends RetrofitSpiceRequest<SuccessResponse,OrdersConfirmation> {

    String action;
    String groupId;

    public ConfirmationRequest(String action,String groupId)
    {
        super(SuccessResponse.class, OrdersConfirmation.class);
        this.action = action;
        this.groupId = groupId;
    }

    @Override
    public SuccessResponse loadDataFromNetwork() throws Exception
    {
        return getService().confirmation(action,groupId);
    }

}
