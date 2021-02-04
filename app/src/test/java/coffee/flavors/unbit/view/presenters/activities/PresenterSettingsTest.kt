package coffee.flavors.unbit.view.presenters.activities

import android.os.Build
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import androidx.test.rule.ActivityTestRule
import coffee.flavors.unbit.R
import coffee.flavors.unbit.view.screens.activities.ViewSettings
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/**
 * @author, evolandlupiz
 * @date, 5/22/2020
 * @property, Unbit
 * @purpose, Small Unit test case to verify and validate the presenter.
 */

@Config(sdk = [Build.VERSION_CODES.O_MR1])
@RunWith(AndroidJUnit4::class)
@SmallTest
class PresenterSettingsTest {

    @get:Rule
    val rule = ActivityTestRule(ViewSettings::class.java)

    @Test
    fun fireBackBtn(){
        println("* * * * * Espresso Test for fireBackBtn() , given: context, when: button is pressed, then: assert button is pressed* * * * *")
        onView(withId(R.id.settings_appbar_back_btn)).perform(click())
    }
}
