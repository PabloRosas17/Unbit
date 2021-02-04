package coffee.flavors.unbit.controller.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.recyclerview.widget.RecyclerView
import coffee.flavors.unbit.R
import coffee.flavors.unbit.databinding.LayoutViewDarkSettingsBinding
import coffee.flavors.unbit.databinding.LayoutViewLightSettingsBinding
import coffee.flavors.unbit.tools.UiUtils
import coffee.flavors.unbit.tools.constants.DebugConst
import coffee.flavors.unbit.tools.constants.StringGlobals
import coffee.flavors.unbit.view.screens.activities.ViewSettings
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textview.MaterialTextView

/**
 * @author, evolandlupiz
 * @date, 5/22/2020
 * @property, Unbit
 * @purpose, definition for settings items list view adapter.
 */

/**
 * @desc settings item adapter.
 */
class SettingsAdapter(private val mViewSettings: ViewSettings) : BaseAdapter()  {

    /**
     * @return Map<K,V> pair , where K is the setting key , where V is the setting value.
     */
    private val map = mViewSettings.mViewModel.mModelSettings.mSettings

    /**
     * @return inflater as the layout.
     */
    private val mInflater: LayoutInflater = LayoutInflater.from(mViewSettings)

    /**
     * @return total item count.
     */
    override fun getCount(): Int {
        return map.size
    }

    /**
     * @return item based on position.
     */
    override fun getItem(position: Int): Any {
        return map.toList()[position]
    }

    /**
     * @return item id based on position.
     */
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    /**
     * @return dynamic view and its holder based on position.
     */
    @SuppressLint("ViewHolder") //list view is implemented on purpose for simple settings.
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val view: View? = when(mViewSettings.mBinding){
            is LayoutViewDarkSettingsBinding -> {
                mInflater.inflate(R.layout.item_dark_settings, parent, false)
            }
            is LayoutViewLightSettingsBinding -> {
                mInflater.inflate(R.layout.item_light_settings, parent, false)
            }
            else -> { throw RuntimeException(DebugConst.VIEW_SETTINGS_BINDING_TYPE) }
        }
        view?.let { SettingsItemViewHolder(it).bind(position) }
        return view
    }

    /**
     * @desc holds reference to an item in the recycler view.
     */
    private inner class SettingsItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        /**
         * @return immutable invocation for item title.
         */
        private val title by lazy { itemView.findViewById<MaterialTextView>(R.id.item_settings_title) }

        /**
         * @return immutable invocation for item value.
         */
        private val value by lazy { itemView.findViewById<MaterialTextView>(R.id.item_settings_value) }

        /**
         * initiator and define item definition for each of its elements.
         */
        init {
            map.forEach { it ->
                title.text = it.key
                value.text = it.value.toString()
            }
        }

        /**
         * binder handles items action, listen to subscribed elements (id).
         * @note theme mode is enabled for settings
         * , handle registration for dynamic bindings on each item.
         */
        fun bind(position: Int) {
            title.text = map.toList()[position].first
            value.text = map.toList()[position].second.toString()

            itemView.setOnClickListener {
                when(it.id) {
                    R.id.item_settings_root_layout -> {
                        UiUtils().fireLightSnackbar(mViewSettings,"item_settings_root_layout, position:$position"
                            , Snackbar.LENGTH_SHORT,R.id.item_settings_root_layout).show()
                        if(title.text.toString() == StringGlobals.THEME_TAG) {
                            when (value.text) {
                                StringGlobals.LIGHT_MODE -> {
                                    value.text = StringGlobals.DARK_MODE
                                    map.put(title.text.toString(), value.text.toString())
                                    notifyDataSetChanged()
                                    mViewSettings.call(true)
                                }
                                StringGlobals.DARK_MODE -> {
                                    value.text = StringGlobals.LIGHT_MODE
                                    map.put(title.text.toString(), value.text.toString())
                                    notifyDataSetChanged()
                                    mViewSettings.call(false)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}