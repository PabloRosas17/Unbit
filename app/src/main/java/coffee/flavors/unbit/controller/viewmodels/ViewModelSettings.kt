package coffee.flavors.unbit.controller.viewmodels

import androidx.lifecycle.ViewModel
import coffee.flavors.unbit.model.ModelSettings
import javax.inject.Inject

/**
 * @author, evolandlupiz
 * @date, 5/22/2020
 * @property, Unbit
 * @purpose, view model for settings.
 */

/**
 * @desc defines view model logic for settings.
 */
class ViewModelSettings @Inject constructor(): ViewModel() {

    /**
     * contains the settings.
     */
    var mModelSettings = ModelSettings()
}