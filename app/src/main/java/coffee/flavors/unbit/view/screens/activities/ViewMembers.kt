package coffee.flavors.unbit.view.screens.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import coffee.flavors.unbit.R
import coffee.flavors.unbit.controller.adapters.MembersAdapter
import coffee.flavors.unbit.controller.viewmodels.ViewModelMembers
import coffee.flavors.unbit.core.UnbitApplication
import coffee.flavors.unbit.databinding.LayoutViewMembersBinding
import coffee.flavors.unbit.model.ModelMember
import coffee.flavors.unbit.tools.BinderIf
import coffee.flavors.unbit.tools.services.api.SimulatorGetErrorCapableIf
import coffee.flavors.unbit.tools.UiUtils
import coffee.flavors.unbit.tools.constants.DebugConst
import coffee.flavors.unbit.tools.constants.MarshTokens
import coffee.flavors.unbit.view.presenters.activities.PresenterMembers
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

/**
 * @author, evolandlupiz
 * @date, 5/22/2020
 * @property, Unbit
 * @purpose, Displays a collection of information from a simulated entity.
 */

/**
 * @decs members view.
 */
class ViewMembers : AppCompatActivity() , BinderIf<ViewDataBinding> ,
    SimulatorGetErrorCapableIf {

    /**
     * @return [ViewModelProvider.Factory] which is the factory injection necessary for view model.
     */
    @Inject lateinit var factory: ViewModelProvider.Factory

    /**
     * Generate view models of type get(T) through the factory provided, Inject a view-model by factory.
     * @return [ViewModelMembers]
     */
    val mViewModel by viewModels<ViewModelMembers> { factory }

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
        super.onCreate(state)
        this.fireUiBindings()
    }

    /**
     * binding is generated through BR class, this will set the view through binding
     * , bind presenter instance with this view, execute pending bindings.
     */
    override fun fireUiBindings() {
        this.mBinding = DataBindingUtil.setContentView(this, R.layout.layout_view_members)
        when(mBinding){
            is LayoutViewMembersBinding -> {
                val type = mBinding as LayoutViewMembersBinding
                type.mPresenter = PresenterMembers(this)
                this.subscribeUi(type)
                this.registerUi(type)
            }
            else -> { throw RuntimeException(DebugConst.VIEW_MEMBERS_BINDING_TYPE) }
        }
        mBinding.executePendingBindings()
    }

    /**
     *  registration to ui elements.
     */
    private fun registerUi(binding : ViewDataBinding) {
        when(mBinding){
            is LayoutViewMembersBinding -> {
                val type = mBinding as LayoutViewMembersBinding
                type.membersRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                type.membersRv.addItemDecoration(
                    DividerItemDecoration(this,DividerItemDecoration.VERTICAL).apply {
                        this.setDrawable(resources.getDrawable(R.drawable.rv_divider,null))
                })
                type.membersRv.adapter = MembersAdapter(this)
                if(intent.extras != null){
                    this.mViewModel.mMembers.add(
                        ModelMember(
                            intent.extras?.get(MarshTokens.MARSHALLED_TOKEN_MEMBERS_ID) as String?
                            , intent.extras?.get(MarshTokens.MARSHALLED_TOKEN_MEMBERS_DISPLAY_NAME) as String?
                            , intent.extras?.get(MarshTokens.MARSHALLED_TOKEN_MEMBERS_JOB_TITLE) as String?
                            , intent.extras?.get(MarshTokens.MARSHALLED_TOKEN_MEMBERS_DESCRIPTION) as String?
                        )
                    )
                    type.membersRv.adapter?.notifyDataSetChanged()
                }
            }
            else -> { throw RuntimeException(DebugConst.VIEW_MEMBERS_BINDING_TYPE) }
        }
    }

    /**
     *  subscription to ui listeners.
     */
    private fun subscribeUi(binding : ViewDataBinding) {
        val type: ViewDataBinding?
        when(mBinding){
            is LayoutViewMembersBinding -> { type = mBinding as LayoutViewMembersBinding }
            else -> { throw RuntimeException(DebugConst.VIEW_MEMBERS_BINDING_TYPE) }
        }
        this.mViewModel.mModelMembersLiveData.observe(this, Observer {
            type.membersRv.adapter?.notifyDataSetChanged()
        })
    }


    /**
     * callback for GET error.
     */
    override fun fireOnGetError() {
        when(mBinding){
            is LayoutViewMembersBinding -> {
                val type = mBinding as LayoutViewMembersBinding
                UiUtils().fireLightSnackbar(this,"There was an error with the GET request."
                    , Snackbar.LENGTH_SHORT,R.id.layout_members_view_root).show()
            }
            else -> { throw RuntimeException(DebugConst.VIEW_MEMBERS_BINDING_TYPE) }
        }
    }
}