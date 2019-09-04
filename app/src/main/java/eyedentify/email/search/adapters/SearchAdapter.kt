package eyedentify.email.search.adapters

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import eyedentify.email.R
import eyedentify.email.api.GravatarApi
import eyedentify.email.model.Entry

class SearchAdapter :
    RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    private var entries: List<Entry> = emptyList()
    private lateinit var clickListener: ClickListener

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_list_item, parent, false) as ViewGroup

        return SearchViewHolder(view)
    }

    override fun getItemCount() = entries.size

    fun setItems(newEntries: List<Entry>) {
        entries = newEntries
        notifyDataSetChanged()
    }

    override fun getItemId(position: Int): Long {
        return entries[position].id?.toLong() ?: return super.getItemId(position)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(entries[position])
        holder.view.setOnClickListener {
            clickListener.onClickEntry(entries[position])
        }
    }

    class SearchViewHolder(val view: ViewGroup) : RecyclerView.ViewHolder(view) {
        private var avatarImageView: ImageView? = null
        private var fullNameTextView: TextView? = null
        private var emailTextView: TextView? = null
        private var aboutTextView: TextView? = null

        private var filter: ColorMatrixColorFilter

        init {
            avatarImageView = view.findViewById(R.id.avatar)
            fullNameTextView = view.findViewById(R.id.fullname)
            emailTextView = view.findViewById(R.id.email)
            aboutTextView = view.findViewById(R.id.about)

            val colorMatrix = ColorMatrix()
            colorMatrix.setSaturation(0f)
            filter = ColorMatrixColorFilter(colorMatrix)
        }

        fun bind(entry: Entry) {
            entry.thumbnailUrl?.let { thumbnailUrl ->
                avatarImageView?.let {
                    it.colorFilter = filter
                    Glide.with(view)
                        .load(GravatarApi.generateThumbnailUrl(thumbnailUrl))
                        .into(it)
                }
            }

            entry.name?.formatted.let { fullNameTextView?.setText(it) }
            entry.aboutMe?.let { aboutTextView?.setText(it) }
            entry.email?.let { emailTextView?.setText(it) }
        }
    }

    fun setClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }

    interface ClickListener {
        fun onClickEntry(entry: Entry)
    }
}
