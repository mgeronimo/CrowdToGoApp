package com.crowdtogo.crowdie.network.requests;

import com.crowdtogo.crowdie.model.SuccessResponse;
import com.crowdtogo.crowdie.network.DeliveryStatusInterface;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

/**
 * Created by Alj on 12/19/2014.
 */

//Request for Start and Done Orders Status
public class DeliveryStatusRequest extends RetrofitSpiceRequest<SuccessResponse,DeliveryStatusInterface> {

    String status;
    String orderId;

    public DeliveryStatusRequest(String status, String orderId) {
        super(SuccessResponse.class, DeliveryStatusInterface.class);
        this.status = status;
        this.orderId = orderId;
    }

    @Override
    public SuccessResponse loadDataFromNetwork() throws Exception {
        return getService().deliveryStatus(status, orderId);
    }
}

