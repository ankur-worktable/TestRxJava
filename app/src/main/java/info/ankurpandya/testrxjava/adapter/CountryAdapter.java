package info.ankurpandya.testrxjava.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import info.ankurpandya.testrxjava.R;
import info.ankurpandya.testrxjava.api.responses.Country;

/**
 * Create by Ankur @ Worktable.sg
 */
public class CountryAdapter extends RecyclerView.Adapter<CountryViewHolder> {

    private final List<Country> countryList;
    private final CountryHandler handler;

    public CountryAdapter(List<Country> countryList, CountryHandler handler) {
        this.countryList = countryList;
        this.handler = handler;
    }

    @NonNull
    @NotNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new CountryViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_country, parent, false),
                handler
        );
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CountryViewHolder holder, int position) {
        holder.bind(countryList.get(position), position + 1);
    }

    public void clearItems() {
        if (countryList != null) {
            countryList.clear();
            notifyDataSetChanged();
        }
    }

    public void replaceItems(List<Country> newCountries) {
        if (countryList != null) {
            countryList.clear();
            countryList.addAll(newCountries);
            notifyDataSetChanged();
        }
    }

    public void addItems(List<Country> newCountries) {
        if (countryList != null) {
            countryList.addAll(newCountries);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (countryList != null) {
            return countryList.size();
        }
        return 0;
    }
}
