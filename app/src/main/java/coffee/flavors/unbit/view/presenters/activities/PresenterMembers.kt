package coffee.flavors.unbit.view.presenters.activities

import android.content.Intent
import android.view.View
import androidx.appcompat.widget.PopupMenu
import coffee.flavors.unbit.R
import coffee.flavors.unbit.view.screens.activities.ViewCreation
import coffee.flavors.unbit.view.screens.activities.ViewMembers
import coffee.flavors.unbit.view.screens.activities.ViewSettings

/**
 * @author, evolandlupiz
 * @date, 5/22/2020
 * @property, Unbit
 * @purpose, presents on ui.
 */

/**
 * @desc presenter for members.
 */
class PresenterMembers(private val mViewMembers: ViewMembers){

    /**
     * action for options button, displays the options menu.
     */
    fun fireOptionsBtn(view: View){
        val popup = PopupMenu(mViewMembers, view)
        val inflater = popup.menuInflater
        inflater.inflate(R.menu.options_menu, popup.menu)
        popup.setOnMenuItemClickListener { item ->
            when(item.itemId) {
                R.id.options_menu_settings -> {
                    fireSettingsBtn()
                    true
                }
                R.id.options_menu_create -> {
                    fireCreateBtn()
                    true
                }
                else -> false
            }
        }
        popup.show()
    }

    /**
     * action for create button, displays the creation screen.
     */
    private fun fireCreateBtn(){
        val mIntent = Intent(this.mViewMembers, ViewCreation::class.java)
        mViewMembers.startActivity(mIntent)
    }

    /**
     * action for settings button, displays the settings screen.
     */
    private fun fireSettingsBtn(){
        val mIntent = Intent(this.mViewMembers, ViewSettings::class.java)
        mViewMembers.startActivity(mIntent)
    }
}