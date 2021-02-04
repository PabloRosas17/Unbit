package coffee.flavors.unbit.view.presenters.activities

import android.content.Intent
import coffee.flavors.unbit.view.screens.activities.ViewHobbies
import coffee.flavors.unbit.view.screens.activities.ViewMembers

/**
 * @author, evolandlupiz
 * @date, 5/23/2020
 * @property, Unbit
 * @purpose, presents on ui.
 */

/**
 * @desc presenter for hobbies.
 */
class PresenterHobbies(private val mViewHobbies: ViewHobbies){

    /**
     * action for back button, displays the members screen.
     */
    fun fireBackBtn(){
        val mIntent = Intent(this.mViewHobbies, ViewMembers::class.java)
        mViewHobbies.startActivity(mIntent)
    }
}