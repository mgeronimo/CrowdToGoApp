package com.crowdtogo.crowdie.network;

import com.crowdtogo.crowdie.model.UserProfileResponse;

import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by HP Pav G4 on 1/15/2015.
 */
public interface UserProfileInterface {
    //@Multipart
    @GET("/api/v1/crowdie/{id}")
    UserProfileResponse getUserSummaryInfo(@Path("id") String crowdieId );
<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
}
