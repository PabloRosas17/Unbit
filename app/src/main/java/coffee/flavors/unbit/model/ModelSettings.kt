package coffee.flavors.unbit.model

import coffee.flavors.unbit.tools.constants.DebugConst

/**
 * @author, evolandlupiz
 * @date, 5/23/2020
 * @property, Unbit
 * @purpose, define settings.
 */

/**
 * @desc model.
 */
class ModelSettings {

    /**
     * map with string as K and any as V
     * , settings must be ensured not to be null
     * , as out adapter depends on their size.
     * @return MutableMap<String,Any>
     */
    private var _mSettings: Map<String,Any>? = null
    val mSettings: MutableMap<String,Any>
        @Throws(AssertionError::class)
        get() {
            if (_mSettings == null) { _mSettings = mutableMapOf() }
            return (_mSettings ?:
                throw AssertionError(DebugConst.MODEL_SETTINGS_NULLS)) as MutableMap<String, Any>
        }

    /**
     * initialize settings data.
     */
    init {
        this.mSettings[THEME_SETTING] = "Light"
        this.mSettings[VERSION_SETTING] = 1.0
    }

    /**
     * definitions for the settings
     * , appropriate to tag with the model as a companion
     * , as these are closest related.
     */
    companion object SETTINGS_DEFINITION {
        const val THEME_SETTING = "Theme"
        const val VERSION_SETTING = "Version"
    }
}