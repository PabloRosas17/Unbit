package coffee.flavors.unbit.core.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import coffee.flavors.unbit.controller.viewmodels.ViewModelCreation
import coffee.flavors.unbit.controller.viewmodels.ViewModelHobbies
import coffee.flavors.unbit.controller.viewmodels.ViewModelMembers
import coffee.flavors.unbit.controller.viewmodels.ViewModelSettings
import coffee.flavors.unbit.core.di.keys.KeyViewModel
import coffee.flavors.unbit.core.factory.FactoryViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * @author, evolandlupiz
 * @date, 5/22/2020
 * @property, Unbit
 * @purpose, Binding module to make the view-models available.
 */

@Module
abstract class ModuleViewModel {

    @Binds
    abstract fun bindFactoryViewModel(factory: FactoryViewModel): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @KeyViewModel(ViewModelMembers::class)
    abstract fun provideViewModelMembers(mViewModelMembers: ViewModelMembers): ViewModel

    @Binds
    @IntoMap
    @KeyViewModel(ViewModelHobbies::class)
    abstract fun provideViewModelHobbies(mViewModelHobbies: ViewModelHobbies): ViewModel

    @Binds
    @IntoMap
    @KeyViewModel(ViewModelCreation::class)
    abstract fun provideViewModelCreation(mViewModelCreation: ViewModelCreation): ViewModel

    @Binds
    @IntoMap
    @KeyViewModel(ViewModelSettings::class)
    abstract fun provideViewModelSettings(mViewModelSettings: ViewModelSettings): ViewModel
}