package coffee.flavors.unbit.model

import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


/**
 * @author, evolandlupiz
 * @date, 5/22/2020
 * @property, Unbit
 * @purpose, Test case to verify and validate the model.
 */

class ModelSettingsTest {

    @Mock
    private lateinit var mSettings: MutableMap<String,Any>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testPassSettingsPut(){
        println("* * * * * Mockito Test for testSettingsPut() , given: map, when: put, then: assert passed put * * * * *")
        mSettings.put("KEY","Value")
        Mockito.verify(mSettings).put("KEY","Value")
        assertEquals(0, mSettings.size)
    }

    @Test
    fun testFailSettingsPut(){
        println("* * * * * Mockito Test for testSettingsPut() , given: map, when: put, then: assert failed put * * * * *")
        mSettings.put("","Value") //Blank key will make this test fail
        Mockito.verify(mSettings).put("KEY","Value")
        assertEquals(0, mSettings.size)
    }
}