package com.tregz.mvvm.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_LEGACY
import androidx.core.widget.doOnTextChanged
import com.tregz.mvvm.R
import com.tregz.mvvm.arch.bind.BindFactory
import com.tregz.mvvm.base.BaseFragment
import com.tregz.mvvm.core.date.DateUtil
import com.tregz.mvvm.data.item.ItemApple
import com.tregz.mvvm.list.ListApple
import kotlinx.android.synthetic.main.fragment_main.*
import java.lang.NumberFormatException
import java.text.SimpleDateFormat
import java.util.*

class MainFragment : BaseFragment() {

    private val backend: MainBackend by lazy {
        // Inject MainListener interface in ViewModel
        with(ViewModelProviders.of(this, BindFactory { MainBackend(listener) })) {
            get(MainBackend::class.java)
        }
    }

    private var input: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ListApple.clear()

        onAppleCreated(backend.insertApple(Date(), R.color.colorAccent))
        onAppleCreated(backend.insertApple(DateUtil.addMonth(Date(), -1), android.R.color.white))
        onAppleCreated(backend.insertApple(null, R.color.colorPrimary))

        input_editor.doOnTextChanged { text, _, _, _ ->
            with(text.toString()) { if (isNotEmpty()) input = this }
        }

        negative_button.setOnClickListener {
            input_editor.setText("")
            sum.text = ""
        }

        positive_button.setOnClickListener {
            try {
                if (Integer.parseInt(input) == ListApple.listCount)
                    sum.text = getString(R.string.answer_positive)
                else sum.text = getString(R.string.answer_negative)
            } catch (e: NumberFormatException) {
                e.message?.let { listener.toast("Error: $it") }
                sum.text = getString(R.string.answer_negative)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        listener.onFragmentStart("Main")
    }

    private fun onAppleCreated(apple: ItemApple) {
        log.append(HtmlCompat.fromHtml("<b>Pomme ajoutée</b>", FROM_HTML_MODE_LEGACY))
        val skeleton = "d MMMM yyyy"
        val formatter = SimpleDateFormat(skeleton, Locale.getDefault())
        val day = apple.ripe?.let { formatter.format(it) }
        val unknown = "Non cueillie ou date de cueillette inconnue."
        val riped = if (day != null) "Cueillie le $day." else unknown
        log.append("\n" + riped + "\n")
        val total = "Taille de la liste: ${ListApple.listCount}"
        sum.text = total
    }

    companion object {
        fun newInstance() = MainFragment()

        init {
            TAG = MainFragment::class.java.simpleName
        }

        fun getTAG() : String {
            return TAG
        }
    }
}
