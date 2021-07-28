package info.ankurpandya.testrxjava.adapter

import android.graphics.Bitmap
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions.withCrossFade
import info.ankurpandya.testrxjava.R
import info.ankurpandya.testrxjava.api.responses.Country
import info.ankurpandya.testrxjava.utils.StringUtils


/**
 * Create by Ankur @ Worktable.sg
 */
open class CountryViewHolder(val rootView: View, @Nullable val handler: CountryHandler) :
    RecyclerView.ViewHolder(rootView) {

    val txt_country_name: TextView;
    val txt_country_id: TextView;
    val countText: TextView;
    val img_country: ImageView;
    val requestBuilder: RequestBuilder<Bitmap>

    init {
        txt_country_name = rootView.findViewById(R.id.txt_country_name)
        txt_country_id = rootView.findViewById(R.id.txt_country_id)
        countText = rootView.findViewById(R.id.count)
        img_country = rootView.findViewById(R.id.img_country)
        requestBuilder = Glide.with(rootView.context)
            .asBitmap()
            .centerCrop()
            .placeholder(R.drawable.img_default)
            .error(R.drawable.img_error)
            .transition(withCrossFade())
    }

    fun bind(country: Country, count: Int) {
        txt_country_name.text = country.name
        txt_country_id.text = country.alpha3Code
        countText.text = StringUtils.formatNumberCount(count)
        requestBuilder.load(StringUtils.getFlagUrl(country.alpha2Code)).into(img_country)
        rootView.setOnClickListener { handler?.onCountrySelected(country) }
    }
}