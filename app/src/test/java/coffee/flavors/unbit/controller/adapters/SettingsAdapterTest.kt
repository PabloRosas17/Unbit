package coffee.flavors.unbit.controller.adapters

import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import coffee.flavors.unbit.view.screens.activities.ViewHobbies
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.annotation.Config

/**
 * @author, evolandlupiz
 * @date, 5/22/2020
 * @property, Unbit
 * @purpose, Test case to verify and validate the adapter.
 */

@Config(sdk = [Build.VERSION_CODES.O_MR1])
@RunWith(AndroidJUnit4::class)
@LargeTest
class SettingsAdapterTest {

    @Mock
    lateinit var vh: ViewHobbies

    lateinit var adapter: HobbiesAdapter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        this.adapter = HobbiesAdapter(vh)
    }

    @Test
    fun testAdapter(){
        println("* * * * * Mockito Test for testAdapter() , given: adapter, when: this, then: assert* * * * *")
        assertNotNull(adapter)
        //while this appears to work , inside the adapter is a list reference that needs mock , otherwise fails.
        // private val list = mViewHobbies.mViewModel.mHobbies
    }
}