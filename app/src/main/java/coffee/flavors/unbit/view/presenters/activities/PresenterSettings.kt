package coffee.flavors.unbit.view.presenters.activities

import android.content.Intent
import coffee.flavors.unbit.view.screens.activities.ViewMembers
import coffee.flavors.unbit.view.screens.activities.ViewSettings

/**
 * @author, evolandlupiz
 * @date, 5/22/2020
 * @property, Unbit
 * @purpose, presents on ui.
 */

/**
 * @desc presenter for settings.
 */
class PresenterSettings(private val mViewSettings: ViewSettings){

    /**
     * action for back button, displays the members screen.
     */
    fun fireBackBtn(){
        val mIntent = Intent(this.mViewSettings, ViewMembers::class.java)
        mViewSettings.startActivity(mIntent)
    }
}