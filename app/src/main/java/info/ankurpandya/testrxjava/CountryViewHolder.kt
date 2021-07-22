package info.ankurpandya.testrxjava

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import info.ankurpandya.testrxjava.responses.Country

/**
 * Create by Ankur @ Worktable.sg
 */
open class CountryViewHolder(rootView: View) : RecyclerView.ViewHolder(rootView) {

    val text1: TextView;
    val text2: TextView;
    val countText: TextView;

    init {
        text1 = rootView.findViewById(R.id.text1)
        text2 = rootView.findViewById(R.id.text2)
        countText = rootView.findViewById(R.id.count)
    }

    fun bind(country: Country, count: Int) {
        text1.text = country.country
        text2.text = country.region
        countText.text = String.format("%02d", count)
    }
}