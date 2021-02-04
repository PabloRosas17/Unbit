package coffee.flavors.unbit.model.entity

/**
 * @author, evolandlupiz
 * @date, 5/22/2020
 * @property, Unbit
 * @purpose, extensive definition for settings.
 */

/**
 * @unused , scalable.
 * @desc specific set of settings.
 */
sealed class UnbitSettings {

    /**
     * theme settings definition.
     * @param name as the setting name
     * @param value as the settings value
     */
    data class THEME(val name: String, val value: String) : UnbitSettings()

    /**
     * version acknowledgement settings definition.
     */
    object VERSION : UnbitSettings()
}