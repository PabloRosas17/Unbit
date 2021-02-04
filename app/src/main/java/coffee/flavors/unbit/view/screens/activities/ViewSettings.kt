package coffee.flavors.unbit.view.screens.activities

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import coffee.flavors.unbit.R
import coffee.flavors.unbit.controller.adapters.SettingsAdapter
import coffee.flavors.unbit.controller.viewmodels.ViewModelSettings
import coffee.flavors.unbit.core.UnbitApplication
import coffee.flavors.unbit.databinding.LayoutViewDarkSettingsBinding
import coffee.flavors.unbit.databinding.LayoutViewDarkSettingsBindingImpl
import coffee.flavors.unbit.databinding.LayoutViewLightSettingsBinding
import coffee.flavors.unbit.databinding.LayoutViewLightSettingsBindingImpl
import coffee.flavors.unbit.tools.BinderIf
import coffee.flavors.unbit.tools.ThemeCallbackIf
import coffee.flavors.unbit.tools.constants.DebugConst
import coffee.flavors.unbit.tools.constants.MarshTokens
import coffee.flavors.unbit.tools.constants.StringGlobals
import coffee.flavors.unbit.view.presenters.activities.PresenterSettings
import javax.inject.Inject


/**
 * @author, evolandlupiz
 * @date, 5/22/2020
 * @property, Unbit
 * @purpose, Displays a collection of settings.
 */

/**
 * @decs settings view.
 */
class ViewSettings : AppCompatActivity() , BinderIf<ViewDataBinding>, ThemeCallbackIf {

    /**
     * @return [ViewModelProvider.Factory] which is the factory injection necessary for view model.
     */
    @Inject lateinit var factory: ViewModelProvider.Factory

    /**
     * Generate view models of type get(T) through the factory provided, Inject a view-model by factory.
     * @return [ViewModelSettings]
     */
    val mViewModel by viewModels<ViewModelSettings> { factory }

    /**
     * Binding object.
     * @return [ViewDataBinding]
     */
    override lateinit var mBinding: ViewDataBinding

    /**
     * Local that holds the default theme which can change.
     * @return the theme.
     */
    private var mTheme: String = StringGlobals.LIGHT_MODE

    /**
     * Perform injection, initialize bindings, and register ui elements.
     */
    override fun onCreate(state: Bundle?) {
        (application as UnbitApplication).mComponent.inject(this)
        super.onCreate(state)
        if(state == null){
            this.fireUiBindings()
        } else {
            if (state.get(MarshTokens.MARSHALLED_TOKEN_KEY_SM_THEME_MODE) != null) {
                this.mTheme = state.get(MarshTokens.MARSHALLED_TOKEN_KEY_SM_THEME_MODE) as String
            }
            this.fireUiBindings()
        }
    }

    /**
     * binding is generated through BR class, this will set the view through binding
     * , bind presenter instance with this view, execute pending bindings.
     */
    override fun fireUiBindings() {
        this.mBinding = DataBindingUtil.setContentView(this, R.layout.layout_view_light_settings)
        when(this.mTheme){
            StringGlobals.DARK_MODE -> {
                this.setTheme(R.style.AppDarkMtrlTheme)
                this.mBinding = DataBindingUtil.setContentView(this, R.layout.layout_view_dark_settings)
                val type = mBinding as LayoutViewDarkSettingsBinding
                this.statusBarColor(StringGlobals.DARK_MODE)
                type.mPresenter = PresenterSettings(this)
                this.registerUi(type)
                this.subscribeUi(type)
            }
            StringGlobals.LIGHT_MODE -> {
                this.setTheme(R.style.AppLightMtrlTheme)
                this.mBinding = DataBindingUtil.setContentView(this, R.layout.layout_view_light_settings)
                val type = mBinding as LayoutViewLightSettingsBinding
                this.statusBarColor(StringGlobals.LIGHT_MODE)
                type.mPresenter = PresenterSettings(this)
                this.registerUi(type)
                this.subscribeUi(type)
            }
            else -> { throw RuntimeException(DebugConst.VIEW_SETTINGS_BINDING_TYPE) }
        }
        mBinding.executePendingBindings()
    }

    /**
     * part of gracefully preserving users transient state.
     * serializes theme to handle configuration change.
     * @note scalable , stash adapater positions.
     * @throws RuntimeException when binding type is zZz unknown.
     */
    override fun onSaveInstanceState(state: Bundle) {
        super.onSaveInstanceState(state)
        if(state.get(MarshTokens.MARSHALLED_TOKEN_KEY_SM_THEME_MODE) != null) {
            this.mTheme = state.get(MarshTokens.MARSHALLED_TOKEN_KEY_SM_THEME_MODE) as String
        }
        when(this.mTheme) {
            StringGlobals.DARK_MODE -> {
                when(this.mBinding) {
                    is LayoutViewLightSettingsBindingImpl -> { this.fireUiBindings() }
                    is LayoutViewDarkSettingsBindingImpl -> { this.fireUiBindings() }
                    else -> { throw RuntimeException(DebugConst.VIEW_SETTINGS_BINDING_TYPE) }
                }
            }
            StringGlobals.LIGHT_MODE -> {
                when(this.mBinding) {
                    is LayoutViewLightSettingsBindingImpl -> { this.fireUiBindings() }
                    is LayoutViewDarkSettingsBindingImpl -> { this.fireUiBindings() }
                    else -> { throw RuntimeException(DebugConst.VIEW_SETTINGS_BINDING_TYPE) }
                }
            }
            else -> { throw RuntimeException(DebugConst.VIEW_SETTINGS_BINDING_TYPE) }
        }
        state.putString(MarshTokens.MARSHALLED_TOKEN_KEY_SM_THEME_MODE, this.mTheme)
    }


    /**
     * part of gracefully preserving users transient state.
     * restores deserialized theme to handle configuration change.
     * @note scalable , restore stashed adapater positions.
     * @throws RuntimeException when binding type is zZz unknown.
     */
    override fun onRestoreInstanceState(state: Bundle) {
        super.onRestoreInstanceState(state)
        when(this.mBinding) {
            is LayoutViewDarkSettingsBinding -> { }
            is LayoutViewLightSettingsBinding -> { }
            else -> { throw RuntimeException(DebugConst.VIEW_SETTINGS_BINDING_TYPE) }
        }
    }

    /**
     *  registration to ui elements.
     */
    private fun registerUi(binding : ViewDataBinding) {
        when(binding){
            is LayoutViewLightSettingsBinding -> { binding.settingsLv.adapter = SettingsAdapter(this) }
            is LayoutViewDarkSettingsBinding -> { binding.settingsLv.adapter = SettingsAdapter(this) }
            else -> { throw RuntimeException(DebugConst.VIEW_SETTINGS_BINDING_TYPE) }
        }
    }

    /**
     *  subscription to ui listeners.
     */
    private fun subscribeUi(binding : ViewDataBinding) {
        when(binding){
            is LayoutViewLightSettingsBinding -> { }
            is LayoutViewDarkSettingsBinding -> { }
            else -> { throw RuntimeException(DebugConst.VIEW_SETTINGS_BINDING_TYPE) }
        }
    }

    /**
     * Delegate that performs state changes to generate a new theme during runtime.
     * @param mode as the theme mode.
     */
    override fun call(mode: Boolean) {
        when(mode){
            true -> {
                val temp = Bundle()
                temp.putString(MarshTokens.MARSHALLED_TOKEN_KEY_SM_THEME_MODE, StringGlobals.DARK_MODE)
                this.onSaveInstanceState(temp)
            }
            false -> {
                val temp = Bundle()
                temp.putString(MarshTokens.MARSHALLED_TOKEN_KEY_SM_THEME_MODE, StringGlobals.LIGHT_MODE)
                this.onSaveInstanceState(temp)
            }
        }
    }

    /**
     * @desc handles a simple change in color for the status bar.
     * @note scalable , this can be moved to ui utils.
     * @requires SDK >= 21
     */
    private fun statusBarColor(color: String) {
        when(color){
            StringGlobals.DARK_MODE -> {
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.statusBarColor = this.resources.getColor(R.color.colorDarkPrimary, null)
            }
            StringGlobals.LIGHT_MODE -> {
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.statusBarColor = this.resources.getColor(R.color.colorLightPrimary, null)
            }
        }
    }
}