package coffee.flavors.unbit.controller.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coffee.flavors.unbit.R
import coffee.flavors.unbit.model.data.ModelSimulatedObject
import coffee.flavors.unbit.tools.constants.NetworkKeys
import coffee.flavors.unbit.tools.services.api.RequestSimulator
import coffee.flavors.unbit.tools.services.api.RequestSimulatorCallback
import coffee.flavors.unbit.tools.services.api.SimulatorPostErrorCapableIf
import coffee.flavors.unbit.tools.services.api.SimulatorPostSuccessCapableIf
import coffee.flavors.unbit.view.screens.activities.ViewCreation
import com.google.gson.Gson
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.collections.HashMap

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
class ViewModelCreation @Inject constructor(private val application: Application): ViewModel() {

    /**
     * @return [SimulatorPostSuccessCapableIf] callback that handles post success.
     */
    private var mSimulatorPostSuccessCapableIf: SimulatorPostSuccessCapableIf? = null

    /**
     * setter function which initializes reference to the callback.
     * @param viewCreation as the context containing reference to the callback.
     */
    fun setSimulatorPostSuccessCapableIf(viewCreation: ViewCreation) {
        mSimulatorPostSuccessCapableIf = viewCreation
    }

    /**
     * @return [SimulatorPostErrorCapableIf] callback that handles post error.
     */
    private var mSimulatorPostErrorCapableIf: SimulatorPostErrorCapableIf? = null

    /**
     * setter function which initializes reference to the callback.
     * @param viewCreation as the context containing reference to the callback.
     */
    fun setSimulatorPostErrorCapableIf(viewCreation: ViewCreation) {
        mSimulatorPostErrorCapableIf = viewCreation
    }

    /**
     * POST network routine that launches a coroutine which executes a simulated network io operation.
     * Takes the response and model after GSON model, emit resulting id.
     * @note simulated network io is on a thread, normally this is not the case, only coroutine executes.
     * @note strings are compiled resources it's not encouraged to override a compiled resource during runtime.
     */
    fun fireNetworkPostCreate(map: HashMap<String,String>){
        viewModelScope.launch {
            RequestSimulator.post(application, map, RequestSimulator.ENDPOINT_CREATE_MEMBER
                , object : RequestSimulatorCallback() {
                    override fun onSuccess(response: String?) {
                        val gsonid = Math.abs(Random().nextInt())
                        val gsonres= application.resources.getString(R.string.get_members_success)
                        val gsonobj = Gson().fromJson(gsonres,ModelSimulatedObject::class.java)

                        /* however in the event we were using a server
                         * , we could prepare the data as such and post. */
                        var temp = ""
                        temp += "{\"display_name\":\""
                        temp += map.get(NetworkKeys.POST_DISPLAY_NAME_KEY)
                        temp += "\",\"job_title\":\""
                        temp += map.get(NetworkKeys.POST_JOB_TITLE_KEY)
                        temp += "\",\"description\":\""
                        temp += map.get(NetworkKeys.POST_DESCRIPTION_KEY)
                        temp += "\",\"id\":\""
                        temp += gsonid
                        temp += "\"}"
                        gsonobj.mData.add(temp)

                        mSimulatorPostSuccessCapableIf?.fireOnPostSuccess(gsonid)
                    }
                    override fun onFailure(response: String?) {
                        mSimulatorPostErrorCapableIf?.fireOnPostError()
                    }
                }
            )
        }
    }
}