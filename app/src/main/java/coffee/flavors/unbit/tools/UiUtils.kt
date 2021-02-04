package coffee.flavors.unbit.tools

import android.app.Activity
import coffee.flavors.unbit.R
import com.google.android.material.snackbar.Snackbar

/**
 * @author, evolandlupiz
 * @date, 5/22/2020
 * @property, Unbit
 * @purpose, utils for the ui.
 */

/**
 * @desc ui utilities.
 */
class UiUtils {
    /**
     * Styles the snackbar for light mode.
     * @param view as the activity.
     * @param msg as the message.
     * @param duration as the time to show.
     * @param id as the parent.
     * @return the snackbar as a light [Snackbar].
     */
    fun fireLightSnackbar(view: Activity, msg: String, duration: Int, id: Int): Snackbar {
        val snackbar = Snackbar.make(view.findViewById(id),msg, duration)
        snackbar.setBackgroundTint(view.resources.getColor(R.color.colorLightPrimary, null))
        snackbar.setTextColor(view.resources.getColor(R.color.colorDark, null))
        return snackbar
    }

    /**
     * Styles the snackbar for dark mode.
     * @param view as the activity.
     * @param msg as the message.
     * @param duration as the time to show.
     * @param id as the parent.
     * @return the snackbar as a dark [Snackbar].
     */
    fun fireDarkSnackbar(view: Activity, msg: String, duration: Int, id: Int): Snackbar {
        val snackbar = Snackbar.make(view.findViewById(id),msg, duration)
        snackbar.setBackgroundTint(view.resources.getColor(R.color.colorDarkPrimary, null))
        snackbar.setTextColor(view.resources.getColor(R.color.colorLight, null))
        return snackbar
    }
}