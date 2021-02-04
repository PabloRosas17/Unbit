package coffee.flavors.unbit.controller.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coffee.flavors.unbit.R
import coffee.flavors.unbit.tools.constants.MarshTokens
import coffee.flavors.unbit.view.screens.activities.ViewHobbies
import coffee.flavors.unbit.view.screens.activities.ViewMembers
import com.google.android.material.textview.MaterialTextView

/**
 * @author, evolandlupiz
 * @date, 5/22/2020
 * @property, Unbit
 * @purpose, definition for member items recycler view adapter.
 */

/**
 * @desc members item adapter.
 */
class MembersAdapter(private val mViewMembers: ViewMembers) : RecyclerView.Adapter<MembersAdapter.MemberItemViewHolder>() {

    /**
     * @return list of models representing the hobbies.
     */
    private val list = mViewMembers.mViewModel.mMembers

    /**
     * @return the member view holder which inflates the item.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberItemViewHolder {
        val mCardInflater = LayoutInflater.from(parent.context)
        val mItemView = mCardInflater.inflate(R.layout.item_member, parent, false)
        return MemberItemViewHolder(mItemView)
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
    override fun onBindViewHolder(holder: MemberItemViewHolder, position: Int) {
        holder.bind(position)
    }

    /**
     * @desc holds reference to an item in the recycler view.
     */
    inner class MemberItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val mName by lazy { itemView.findViewById<MaterialTextView>(R.id.item_member_name) }
        private val mPosition by lazy { itemView.findViewById<MaterialTextView>(R.id.item_member_position) }
        private val mDescription by lazy { itemView.findViewById<MaterialTextView>(R.id.item_member_description) }

        /**
         * binder handles items action, listen to subscribed elements (id).
         */
        fun bind(position: Int) {
            mName.text = list[position].name
            mPosition.text = list[position].position
            mDescription.text = list[position].description
            itemView.setOnClickListener {
                when(it.id) {
                    R.id.item_member_root_layout -> {
                        val mIntent = Intent(mViewMembers, ViewHobbies::class.java)
                        mIntent.putExtra(MarshTokens.MARSHALLED_TOKEN_MEMBERS_ID, list[position].id)
                        mViewMembers.startActivity(mIntent)
                    }
                }
            }
        }
    }
}