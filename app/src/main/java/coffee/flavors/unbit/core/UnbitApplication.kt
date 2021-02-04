package coffee.flavors.unbit.core

import android.app.Application
import coffee.flavors.unbit.core.di.components.ComponentApplication
import coffee.flavors.unbit.core.di.components.DaggerComponentApplication

/**
 * @author, evolandlupiz
 * @date, 5/22/2020
 * @property, Unbit
 * @purpose, Application module.
 */

/**
 * Generates a multi-module application that is dependency-injection ready.
 */
class UnbitApplication : Application(){

    /**
     * @return application component
     */
    lateinit var mComponent: ComponentApplication
        private set

    /**
     * Initial module declares injection components for the Application, then build the Application.
     */
    override fun onCreate() {
        super.onCreate()
        this.mComponent = DaggerComponentApplication.builder()
            .application(this)
            .build()
    }
}