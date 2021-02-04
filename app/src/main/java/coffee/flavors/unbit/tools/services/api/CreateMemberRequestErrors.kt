package coffee.flavors.unbit.tools.services.api

import androidx.annotation.Nullable
import com.google.gson.annotations.SerializedName

/**
 * Model for the `members/create/` error response to simplify forming a proper JSON String.
 *
 *
 * Created by amwomer on 1/8/18.
 */

/**
 * @disclaimer inherited from Drund.
 */
class CreateMemberRequestErrors {
    @Nullable
    var errors: Errors? = null

    inner class Errors internal constructor() {
        @SerializedName("display_name")
        var displayName: String? = null
        @SerializedName("job_title")
        var jobTitle: String? = null
        @SerializedName("description")
        var description: String? = null
    }
}