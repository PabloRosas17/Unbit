package coffee.flavors.unbit.model.data

import com.google.gson.JsonArray
import com.google.gson.annotations.SerializedName

/**
 * @author, evolandlupiz
 * @date, 5/23/2020
 * @property, Unbit
 * @purpose, data models.
 */

/**
 * @desc definition for a simulated object.
 * @param mData of JsonArray type, serialized as "data"
 * @return model.
 */
data class ModelSimulatedObject(
    @SerializedName("data")
    var mData: JsonArray
)

/**
 * @desc definition for a simulated model.
 * @param mId is the member identification.
 * @param mDisplayName is the member display name.
 * @param mJobTitle is the member job title.
 * @param mDescription is the member description.
 * @return model.
 */
data class ModelSimulatedMember(
    @SerializedName("id")
    var mId: String? = null,
    @SerializedName("display_name")
    var mDisplayName: String? = null,
    @SerializedName("job_title")
    var mJobTitle: String? = null,
    @SerializedName("description")
    var mDescription: String? = null
)

/**
 * @desc definition for a simulated hobby.
 * @param mTitle is the hobby title.
 * @param mSkillLevel is the hobby skill.
 * @return model.
 */
data class ModelSimulatedHobby(
    @SerializedName("title")
    var mTitle: String? = null,
    @SerializedName("skill_level")
    var mSkillLevel: String? = null
)