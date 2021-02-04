package coffee.flavors.unbit.controller.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coffee.flavors.unbit.R
import coffee.flavors.unbit.tools.UiUtils
import coffee.flavors.unbit.view.screens.activities.ViewHobbies
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textview.MaterialTextView

/**
 * @author, evolandlupiz
 * @date, 5/22/2020
 * @property, Unbit
 * @purpose, definition for hobbies items recycler view adapter.
 */

/**
 * @desc hobbies item adapter.
 */
class HobbiesAdapter(private val mViewHobbies: ViewHobbies) : RecyclerView.Adapter<HobbiesAdapter.HobbiesItemViewHolder>() {

    /**
     * @return list of models representing the hobbies.
     */
    private val list = mViewHobbies.mViewModel.mHobbies

    /**
     * @return hobbies view holder which inflates the item.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HobbiesItemViewHolder {
        val mCardInflater = LayoutInflater.from(parent.context)
        val mItemView = mCardInflater.inflate(R.layout.item_hobbies, parent, false)
        return HobbiesItemViewHolder(mItemView)
    }

    /**
     * @return total item count.
     */
    override fun getItemCount(): Int {
        return list.size
    }

    /**
     * definition for binding the view holder.
     */
    override fun onBindViewHolder(holder: HobbiesItemViewHolder, position: Int) {
        holder.bind(position)
    }

    /**
     * @desc holds reference to an item in the recycler view.
     */
    inner class HobbiesItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val mTitle by lazy { itemView.findViewById<MaterialTextView>(R.id.item_hobbies_hobby_title) }
        private val mLevel by lazy { itemView.findViewById<MaterialTextView>(R.id.item_hobbies_hobby_level) }

        /**
         * binder handles items action, listen to subscribed elements (id).
         */
        fun bind(position: Int) {
            mTitle.text = list[position].title
            mLevel.text = list[position].level
            itemView.setOnClickListener {
                when(it.id) {
                    R.id.item_hobbies_root_layout -> {
                        UiUtils().fireLightSnackbar(mViewHobbies,"item_hobbies_root_layout, position:$position"
                            , Snackbar.LENGTH_SHORT,R.id.item_hobbies_root_layout).show()
                    }
                }
            }
        }
    }
}