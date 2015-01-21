package com.crowdtogo.crowdie.network.requests;

/**
 * Created by Alj on 12/19/2014.
 */

//Request for Accept and Reject Group Orders
import com.crowdtogo.crowdie.model.SuccessResponse;
import com.crowdtogo.crowdie.network.CheckOrderCodeInterface;
import com.crowdtogo.crowdie.network.OrdersConfirmation;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

public class CheckOrderCodeRequest extends RetrofitSpiceRequest<SuccessResponse,CheckOrderCodeInterface> {

    String code;
    String id;

    public CheckOrderCodeRequest(String code, String id)
    {
        super(SuccessResponse.class, CheckOrderCodeInterface.class);
        this.code = code;
        this.id = id;
    }

    @Override
    public SuccessResponse loadDataFromNetwork() throws Exception
    {
        return getService().checkOrderCode(code, id);
    }

}
