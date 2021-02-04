package coffee.flavors.unbit.model

/**
 * @author, evolandlupiz
 * @date, 5/23/2020
 * @property, Unbit
 * @purpose, model a non serialized hobby.
 */

/**
 * @desc definition for a hobby model.
 * @param title is the hobby title.
 * @param level is the hobby skill.
 * @return model.
 */
data class ModelHobby(
    var title : String? = null
    , var level : String? = null
)