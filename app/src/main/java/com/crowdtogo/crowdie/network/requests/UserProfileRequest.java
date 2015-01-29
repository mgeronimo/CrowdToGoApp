package com.crowdtogo.crowdie.network.requests;

import com.crowdtogo.crowdie.model.UserProfileResponse;
import com.crowdtogo.crowdie.network.UserProfileInterface;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

/**
 * Created by HP Pav G4 on 1/15/2015.
 */
public class UserProfileRequest extends RetrofitSpiceRequest<UserProfileResponse, UserProfileInterface>

    {

        private String crowdieId;

        public UserProfileRequest(String crowdieId) {
        super(UserProfileResponse.class, UserProfileInterface.class);
        this.crowdieId = crowdieId;
    }

        @Override
        public UserProfileResponse loadDataFromNetwork() throws Exception {
<<<<<<< Updated upstream
        //return getService().getUserSummaryInfo(this.crowdieId);
=======
>>>>>>> Stashed changes
        return getService().getUserSummaryInfo(crowdieId);
    }

}
