package com.github.rafallopatka.ssc.dashboard.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.github.rafallopatka.ssc.R
import com.github.rafallopatka.ssc.dashboard.logic.Series
import kotlinx.android.synthetic.main.list_item_series.view.*


class SeriesAdapter(
    private val context: Context,
    private val actionHandler: (Action) -> Unit
) :

    RecyclerView.Adapter<SeriesAdapter.ListViewHolder>() {
    private var dataset = arrayListOf<ListItem>()

    fun setData(data: Collection<Series>) {
        val list = arrayListOf<ListItem>()
        list.addAll(data.map { SeriesItem(it) })
        list.add(FooterItem())
        dataset.clear()
        dataset.addAll(list)

        this.notifyDataSetChanged()
    }

    interface ListItem
    data class SeriesItem(val series: Series) : ListItem
    data class FooterItem(val str: String = "") : ListItem

    abstract class ListViewHolder(view: View) : RecyclerView.ViewHolder(view)
    class SeriesViewHolder(view: View) : ListViewHolder(view) {
        val headText: TextView
        var descriptionText: TextView
        var options: View
        val itemsClickArea: View

        init {
            this.headText = view.textViewHead
            this.descriptionText = view.textViewDesc
            this.options = view.textViewOptions
            this.itemsClickArea = view.item_click_area
        }
    }

    class FooterViewHolder(view: View) : ListViewHolder(view)

    private val seriesViewType = 1
    private val footerViewType = 2
    private val unknownViewType = 0

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        if (viewType == seriesViewType) {
            val item_layout = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_series, parent, false)

            return SeriesViewHolder(item_layout)
        }

        val item_layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_footer, parent, false)

        return FooterViewHolder(item_layout)
    }

    override fun getItemViewType(position: Int): Int {
        val dataItem = dataset.elementAt(position)
        return when (dataItem) {
            is SeriesItem -> seriesViewType
            is FooterItem -> footerViewType
            else -> unknownViewType
        }
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val dataItem = dataset.elementAt(position)

        if (dataItem is SeriesItem && holder is SeriesViewHolder) {
            holder.headText.setText(dataItem.series.name)
            holder.descriptionText.setText(dataItem.series.sequence)

            holder.itemsClickArea.setOnClickListener {
                actionHandler(Action.View(dataItem.series))
            }

            holder.options.setOnClickListener {
                val popup = PopupMenu(context, holder.options)
                popup.inflate(R.menu.list_item_popup_menu)
                popup.setOnMenuItemClickListener { menuItem ->
                    when (menuItem.getItemId()) {
                        R.id.edit -> {
                            actionHandler(Action.Edit(dataItem.series))
                        }
                        R.id.delete -> {
                            actionHandler(Action.Delete(dataItem.series))
                        }
                    }
                    return@setOnMenuItemClickListener false
                }

                popup.show()
            }
        } else if (dataItem is FooterItem) {

        }
    }

    override fun getItemCount() = dataset.count()

    sealed class Action {
        class View(val series: Series) : Action()
        class Edit(val series: Series) : Action()
        class Delete(val series: Series) : Action()
    }
}