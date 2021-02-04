package coffee.flavors.unbit.core.di.components

import android.app.Activity
import android.app.Application
import coffee.flavors.unbit.core.UnbitApplication
import coffee.flavors.unbit.core.di.modules.ModuleViewModel
import coffee.flavors.unbit.view.screens.activities.ViewCreation
import coffee.flavors.unbit.view.screens.activities.ViewHobbies
import coffee.flavors.unbit.view.screens.activities.ViewMembers
import coffee.flavors.unbit.view.screens.activities.ViewSettings
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * @author, evolandlupiz
 * @date, 5/22/2020
 * @property, Unbit
 * @purpose, Define component modules for injection
 */

/**
 * Components that will be used to map during builders lifecycle.
 */
@Component(
    modules = [
        ModuleViewModel::class
    ]
)

/**
 * Contract for the application to generate components.
 */
@Singleton
interface ComponentApplication {

    /**
     * builder that builds an application.
     */
    @Component.Builder
    interface Builder{

        /**
         * application method used during synthesis.
         */
        @BindsInstance
        fun application(application: Application): Builder

        /**
         * build method used during synthesis.
         */
        fun build(): ComponentApplication
    }

    /**
     * injection function used to inject a fresh made application from the factory.
     */
    fun inject(mUnbitApplication: UnbitApplication)

    /**
     * injection function used to inject a freshly made activity.
     */
    fun inject(mActivity: Activity)
    fun inject(mViewMembers: ViewMembers)
    fun inject(mViewCreation: ViewCreation)
    fun inject(mViewHobbies: ViewHobbies)
    fun inject(mViewSettings: ViewSettings)
}