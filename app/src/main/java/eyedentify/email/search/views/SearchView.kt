package eyedentify.email.search.views

import androidx.annotation.StringRes
import eyedentify.email.model.Entry


interface SearchView {
    fun showProgress(show: Boolean)

    /**
     *
     * @param message The [String] message to be displayed.
     * @param error a boolean flag signifying if the `message` should be an error message or not.
     */
    fun showMessage(message: String, error: Boolean)

    /**
     *
     * @param resId The resource id for the string.
     * @param error a boolean flag signifying if the `resId` should be an error message or not.
     */
    fun showMessage(@StringRes resId: Int, error: Boolean)

    fun showEmpty()

    fun showMessageView(show: Boolean)

    fun showEntries(entries: List<Entry>)

}
