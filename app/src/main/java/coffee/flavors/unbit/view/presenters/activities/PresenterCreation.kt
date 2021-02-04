package coffee.flavors.unbit.view.presenters.activities

import android.content.Intent
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import coffee.flavors.unbit.R
import coffee.flavors.unbit.databinding.LayoutViewCreationBinding
import coffee.flavors.unbit.tools.UiUtils
import coffee.flavors.unbit.tools.constants.DebugConst
import coffee.flavors.unbit.tools.constants.NetworkKeys
import coffee.flavors.unbit.view.screens.activities.ViewCreation
import coffee.flavors.unbit.view.screens.activities.ViewMembers
import com.google.android.material.snackbar.Snackbar

/**
 * @author, evolandlupiz
 * @date, 5/23/2020
 * @property, Unbit
 * @purpose, presents on ui.
 */

/**
 * @desc presenter for creation.
 */
class PresenterCreation(private val mViewCreation: ViewCreation){

    /**
     * action for back button, displays the members screen.
     */
    fun fireBackBtn(){
        val mIntent = Intent(this.mViewCreation, ViewMembers::class.java)
        mViewCreation.startActivity(mIntent)
    }

    /**
     * expression determines theme then performs a create operation.
     * ui logic and ui effects are handled.
     */
    fun fireCreateBtn() = when (mViewCreation.mBinding) {
        is LayoutViewCreationBinding -> {
            val type = mViewCreation.mBinding as LayoutViewCreationBinding
            val display_name = type.creationDisplayNameTiet
            val job_title = type.creationJobTitleTiet
            val description = type.creationDescriptionTiet
            val dnl = type.creationDisplayNameTil
            val jtl = type.creationJobTitleTil
            val dtl = type.creationDescriptionTil
            var error = false

            if (display_name.text.isNullOrBlank()) {
                error = true
                dnl.error = mViewCreation.getString(R.string.creation_error_display_name)
            } else {
                dnl.error = null
            }
            if (job_title.text.isNullOrBlank()) {
                error = true
                jtl.error = mViewCreation.getString(R.string.creation_error_job_title)
            } else {
                jtl.error = null
            }
            if (description.text.isNullOrBlank()) {
                error = true
                dtl.error = mViewCreation.getString(R.string.creation_error_description)
            } else {
                dtl.error = null

            }

            if(!error){
                val map = hashMapOf<String,String>(
                    Pair(NetworkKeys.POST_DISPLAY_NAME_KEY,display_name.text.toString())
                    ,Pair(NetworkKeys.POST_JOB_TITLE_KEY,job_title.text.toString())
                    ,Pair(NetworkKeys.POST_DESCRIPTION_KEY,description.text.toString())
                )
                mViewCreation.mViewModel.fireNetworkPostCreate(map)
            } else {
                val snackbar = UiUtils().fireLightSnackbar(
                    mViewCreation,mViewCreation.getString(R.string.snackbar_new_member_error)
                        ,Snackbar.LENGTH_SHORT, R.id.layout_creation_view_root)
                val slp = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                slp.setMargins(0, type.gdCreation50Hor.y.toInt(), 0, 0)
                snackbar.view.layoutParams = slp
                snackbar.show()
            }
        }
        else -> {
            throw RuntimeException(DebugConst.VIEW_CREATION_BINDING_TYPE)
        }
    }
}