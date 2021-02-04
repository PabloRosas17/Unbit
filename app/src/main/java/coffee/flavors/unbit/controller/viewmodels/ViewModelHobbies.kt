package coffee.flavors.unbit.controller.viewmodels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coffee.flavors.unbit.model.ModelHobby
import coffee.flavors.unbit.model.data.ModelSimulatedHobby
import coffee.flavors.unbit.model.data.ModelSimulatedObject
import coffee.flavors.unbit.tools.services.api.SimulatorGetErrorCapableIf
import coffee.flavors.unbit.tools.services.api.RequestSimulator
import coffee.flavors.unbit.tools.services.api.RequestSimulatorCallback
import coffee.flavors.unbit.view.screens.activities.ViewHobbies
import com.google.gson.Gson
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author, evolandlupiz
 * @date, 5/23/2020
 * @property, Unbit
 * @purpose, view model for members.
 */

/**
 * @desc defines view model logic for hobbies.
 * @param application by injection required for accessing string resources in the simulated network client.
 */
class ViewModelHobbies @Inject constructor(private val application: Application): ViewModel() {

    /**
     * @return [SimulatorGetErrorCapableIf] callback that handles get error.
     */
    private var mSimulatorGetErrorCapableIf: SimulatorGetErrorCapableIf? = null

    /**
     * setter function which initializes reference to the callback.
     * @param viewHobbies as the context containing reference to the callback.
     */
    fun setSimulatorGetErrorCapableIf(viewHobbies: ViewHobbies) {
        mSimulatorGetErrorCapableIf = viewHobbies
    }

    /**
     * @return a list of hobby models.
     */
    val mHobbies = mutableListOf<ModelHobby>()

    /**
     * @return live data which emits a mutable list of hobby models.
     */
    val mModelHobbiesLiveData: MutableLiveData<MutableList<ModelHobby>>
            by lazy { MutableLiveData<MutableList<ModelHobby>>() }

    /**
     * GET network routine that launches a coroutine which executes a simulated network io operation.
     * Takes the response and model after GSON model, add the result to the list.
     * @note simulated network io is on a thread, normally this is not the case, only coroutine executes.
     */
    fun fireNetworkGetMemberHobbies(id: String) {
        viewModelScope.launch {
            RequestSimulator.get(application, "https://api.drund.com/members/$id/hobbies/"
                , object : RequestSimulatorCallback() {
                    override fun onSuccess(response: String?) {
                        val rsp = Gson().fromJson(response, ModelSimulatedObject::class.java)
                        for(i in 0 until rsp.mData.size()){
                            val temp = Gson().fromJson(
                                rsp.mData[i], ModelSimulatedHobby::class.java)
                            mHobbies.add(ModelHobby(temp.mTitle,temp.mSkillLevel))
                        }
                        mModelHobbiesLiveData.postValue(mHobbies)
                    }
                    override fun onFailure(response: String?) {
                        mSimulatorGetErrorCapableIf?.fireOnGetError()
                    }
                }
            )
        }
    }
}