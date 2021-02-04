package coffee.flavors.unbit.view.screens.activities

import android.content.Context
import coffee.flavors.unbit.R
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

/**
 * @author, evolandlupiz
 * @date, 5/22/2020
 * @property, Unbit
 * @purpose, Test case to verify and validate the view.
 */

@RunWith(MockitoJUnitRunner::class)
class ViewSettingsTest {

    @Mock
    private lateinit var context: Context

    @Test
    fun testResourcesString(){
        println("* * * * * Mockito Test for testResourcesString() , given: string, when: activity, then: assert* * * * *")
        `when`(context.getString(R.string.get_members_success)).thenReturn("True")
    }
}

