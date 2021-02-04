package coffee.flavors.unbit.controller.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coffee.flavors.unbit.model.ModelMember
import coffee.flavors.unbit.model.data.ModelSimulatedMember
import coffee.flavors.unbit.model.data.ModelSimulatedObject
import coffee.flavors.unbit.tools.services.api.SimulatorGetErrorCapableIf
import coffee.flavors.unbit.tools.services.api.RequestSimulator
import coffee.flavors.unbit.tools.services.api.RequestSimulatorCallback
import coffee.flavors.unbit.view.screens.activities.ViewMembers
import com.google.gson.Gson
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author, evolandlupiz
 * @date, 5/22/2020
 * @property, Unbit
 * @purpose, view model for members.
 */

/**
 * @desc defines view model logic for members.
 * @param application by injection required for accessing string resources in the simulated network client.
 */
class ViewModelMembers @Inject constructor(application: Application): ViewModel() {

    /**
     * @return a list of hobby models.
     */
    val mMembers = mutableListOf<ModelMember>()

    /**
     * @return live data which emits a mutable list of member models.
     */
    val mModelMembersLiveData: MutableLiveData<MutableList<ModelMember>>
            by lazy { MutableLiveData<MutableList<ModelMember>>() }

    /**
     * @return [SimulatorGetErrorCapableIf] callback that handles get error.
     */
    private var mSimulatorGetErrorCapableIf: SimulatorGetErrorCapableIf? = null

    /**
     * setter function which initializes reference to the callback.
     * @param viewMembers as the context containing reference to the callback.
     */
    fun setSimulatorGetErrorCapableIf(viewMembers: ViewMembers) {
        mSimulatorGetErrorCapableIf = viewMembers
    }

    /**
     * initialization and launch network operation on view model scope.
     */
    init {
        fireNetworkGetMembers(application)
    }

    /**
     * GET network routine that launches a coroutine which executes a simulated network io operation.
     * Takes the response and model after GSON model, add the result to the list.
     * @note simulated network io is on a thread, normally this is not the case, only coroutine executes.
     */
    private fun fireNetworkGetMembers(context: Context){
        viewModelScope.launch {
            RequestSimulator.get(context, RequestSimulator.ENDPOINT_GET_MEMBERS
                , object : RequestSimulatorCallback() {
                    override fun onSuccess(response: String?) {
                        val rsp = Gson().fromJson(response,ModelSimulatedObject::class.java)
                        for(i in 0 until rsp.mData.size()){
                            val temp = Gson().fromJson(rsp.mData[i],ModelSimulatedMember::class.java)
                            mMembers.add(ModelMember(temp.mId,temp.mDisplayName,temp.mJobTitle,temp.mDescription))
                        }
                        mModelMembersLiveData.postValue(mMembers)
                    }
                    override fun onFailure(response: String?) {
                        mSimulatorGetErrorCapableIf?.fireOnGetError()
                    }
                }
            )
        }
    }
}