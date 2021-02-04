package coffee.flavors.unbit.controller.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations

/**
 * @author, evolandlupiz
 * @date, 5/22/2020
 * @property, Unbit
 * @purpose, Test case to verify and validate the view model.
 */


class ViewModelSettingsTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    lateinit var vm: ViewModelSettings

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        this.vm = ViewModelSettings()
    }

    @Test
    fun testViewModelInstance(){
        println("* * * * * Mockito Test for testViewModelInstance() , given: vm, when: this, then: assert* * * * *")
        assertNotNull(vm)
    }
}
