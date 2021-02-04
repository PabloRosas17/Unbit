package coffee.flavors.unbit.view.screens.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import coffee.flavors.unbit.R
import coffee.flavors.unbit.controller.viewmodels.ViewModelCreation
import coffee.flavors.unbit.core.UnbitApplication
import coffee.flavors.unbit.databinding.LayoutViewCreationBinding
import coffee.flavors.unbit.tools.*
import coffee.flavors.unbit.tools.constants.DebugConst
import coffee.flavors.unbit.tools.constants.MarshTokens
import coffee.flavors.unbit.tools.services.api.SimulatorPostErrorCapableIf
import coffee.flavors.unbit.tools.services.api.SimulatorPostSuccessCapableIf
import coffee.flavors.unbit.view.presenters.activities.PresenterCreation
import javax.inject.Inject

/**
 * @author, evolandlupiz
 * @date, 5/22/2020
 * @property, Unbit
 * @purpose, Displays a workflow to create an entity.
 */

/**
 * @decs creation view.
 */
class ViewCreation : AppCompatActivity() , BinderIf<ViewDataBinding>
    , SimulatorPostSuccessCapableIf,
    SimulatorPostErrorCapableIf {

    /**
     * @return [ViewModelProvider.Factory] which is the factory injection necessary for view model.
     */
    @Inject lateinit var factory: ViewModelProvider.Factory

    /**
     * Generate view models of type get(T) through the factory provided, Inject a view-model by factory.
     * @return [ViewModelCreation]
     */
    val mViewModel by viewModels<ViewModelCreation> { factory }

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
        this.mViewModel.setSimulatorPostSuccessCapableIf(this)
        this.mViewModel.setSimulatorPostErrorCapableIf(this)
        super.onCreate(state)
        this.fireUiBindings()
    }

    /**
     * binding is generated through BR class, this will set the view through binding
     * , bind presenter instance with this view, execute pending bindings.
     */
    override fun fireUiBindings() {
        this.mBinding = DataBindingUtil.setContentView(this, R.layout.layout_view_creation)
        when (mBinding) {
            is LayoutViewCreationBinding -> {
                val type = mBinding as LayoutViewCreationBinding
                type.mPresenter = PresenterCreation(this)
                this.registerUi(type)
                this.subscribeUi(type)
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
            is LayoutViewCreationBinding -> {
                val type = mBinding as LayoutViewCreationBinding
            }
            else -> {
                throw RuntimeException(DebugConst.VIEW_CREATION_BINDING_TYPE)
            }
        }
    }

    /**
     *  subscription to ui listeners.
     */
    private fun subscribeUi(binding: ViewDataBinding) {
        when (mBinding) {
            is LayoutViewCreationBinding -> {
                val type = mBinding as LayoutViewCreationBinding
            }
            else -> {
                throw RuntimeException(DebugConst.VIEW_CREATION_BINDING_TYPE)
            }
        }
    }

    /**
     * callback for POST success.
     * @param data as the id.
     */
    override fun fireOnPostSuccess(data: Int) {
        when(mBinding){
            is LayoutViewCreationBinding -> {
                val type = mBinding as LayoutViewCreationBinding
                val mIntent = Intent(this,ViewMembers::class.java)
                mIntent.putExtra(MarshTokens.MARSHALLED_TOKEN_MEMBERS_ID
                    , data.toString())
                mIntent.putExtra(MarshTokens.MARSHALLED_TOKEN_MEMBERS_DISPLAY_NAME
                    , type.creationDisplayNameTiet.text.toString())
                mIntent.putExtra(MarshTokens.MARSHALLED_TOKEN_MEMBERS_JOB_TITLE
                    , type.creationJobTitleTiet.text.toString())
                mIntent.putExtra(MarshTokens.MARSHALLED_TOKEN_MEMBERS_DESCRIPTION
                    , type.creationDescriptionTiet.text.toString())
                this.startActivity(mIntent)
            }
            else -> { throw RuntimeException(DebugConst.VIEW_MEMBERS_BINDING_TYPE) }
        }
    }

    /**
     * callback for POST error.
     */
    override fun fireOnPostError() {
        Toast.makeText(this,"There was an error with the POST request.",Toast.LENGTH_SHORT).show()
    }
}