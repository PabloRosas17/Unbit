package coffee.flavors.unbit.model

/**
 * @author, evolandlupiz
 * @date, 5/23/2020
 * @property, Unbit
 * @purpose, model a non serialized member.
 */

/**
 * @desc definition for a member model.
 * @param id is the member title.
 * @param name is the member name is.
 * @param position is the member position.
 * @param description is member description.
 * @return model.
 */
data class ModelMember(
    var id: String? = null
    , var name : String? = null
    , var position : String? = null
    , var description : String ? = null
)