package coffee.flavors.unbit.view.screens.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import coffee.flavors.unbit.R
import coffee.flavors.unbit.controller.adapters.HobbiesAdapter
import coffee.flavors.unbit.controller.viewmodels.ViewModelHobbies
import coffee.flavors.unbit.core.UnbitApplication
import coffee.flavors.unbit.databinding.LayoutViewHobbiesBinding
import coffee.flavors.unbit.tools.BinderIf
import coffee.flavors.unbit.tools.services.api.SimulatorGetErrorCapableIf
import coffee.flavors.unbit.tools.UiUtils
import coffee.flavors.unbit.tools.constants.DebugConst
import coffee.flavors.unbit.tools.constants.MarshTokens
import coffee.flavors.unbit.view.presenters.activities.PresenterHobbies
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

/**
 * @author, evolandlupiz
 * @date, 5/22/2020
 * @property, Unbit
 * @purpose, Display list of hobbies.
 */

/**
 * @decs hobbies view.
 */
class ViewHobbies : AppCompatActivity() , BinderIf<ViewDataBinding> ,
    SimulatorGetErrorCapableIf {

    /**
     * @return [ViewModelProvider.Factory] which is the factory injection necessary for view model.
     */
    @Inject lateinit var factory: ViewModelProvider.Factory

    /**
     * Generate view models of type get(T) through the factory provided, Inject a view-model by factory.
     * @return [ViewModelHobbies]
     */
    val mViewModel by viewModels<ViewModelHobbies> { factory }

    /**
     * Binding object.
     * @return [ViewDataBinding]
     */
    override lateinit var mBinding: ViewDataBinding

    /**
     * Perform injection, initialize bindings, and register ui elements.
     */
    override fun onCreate(state: Bundle?) {
        (application as UnbitApplication).mComponent.inject(this)
        this.mViewModel.setSimulatorGetErrorCapableIf(this)
        val id = intent.extras?.get(MarshTokens.MARSHALLED_TOKEN_MEMBERS_ID).toString()
        this.mViewModel.fireNetworkGetMemberHobbies(id)
        super.onCreate(state)
        this.fireUiBindings()
    }

    /**
     * binding is generated through BR class, this will set the view through binding
     * , bind presenter instance with this view, execute pending bindings.
     */
    override fun fireUiBindings() {
        this.mBinding = DataBindingUtil.setContentView(this, R.layout.layout_view_hobbies)
        when (mBinding) {
            is LayoutViewHobbiesBinding -> {
                val type = mBinding as LayoutViewHobbiesBinding
                type.mPresenter = PresenterHobbies(this)
                this.subscribeUi(type)
                this.registerUi(type)
            }
            else -> {
                throw RuntimeException(DebugConst.VIEW_CREATION_BINDING_TYPE)
            }
        }
        mBinding.executePendingBindings()
    }

    /**
     *  registration to ui elements.
     */
    private fun registerUi(binding: ViewDataBinding) {
        when (mBinding) {
            is LayoutViewHobbiesBinding -> {
                val type = mBinding as LayoutViewHobbiesBinding
                type.hobbiesRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                type.hobbiesRv.adapter = HobbiesAdapter(this)
            }
            else -> {
                throw RuntimeException(DebugConst.VIEW_HOBBIES_BINDING_TYPE)
            }
        }
    }

    /**
     *  subscription to ui listeners.
     */
    private fun subscribeUi(binding: ViewDataBinding) {
        val type: ViewDataBinding?
        when(mBinding){
            is LayoutViewHobbiesBinding -> { type = mBinding as LayoutViewHobbiesBinding }
            else -> { throw RuntimeException(DebugConst.VIEW_HOBBIES_BINDING_TYPE) }
        }

        this.mViewModel.mModelHobbiesLiveData.observe(this, Observer {
            type.hobbiesRv.adapter?.notifyDataSetChanged()
        })
    }

    /**
     * callback for GET error.
     */
    override fun fireOnGetError() {
        when(mBinding){
            is LayoutViewHobbiesBinding -> {
                val type = mBinding as LayoutViewHobbiesBinding
                UiUtils().fireLightSnackbar(this,"There was an error with the GET request."
                    , Snackbar.LENGTH_SHORT,R.id.layout_hobbies_view_root).show()
            }
            else -> { throw RuntimeException(DebugConst.VIEW_MEMBERS_BINDING_TYPE) }
        }
    }
}