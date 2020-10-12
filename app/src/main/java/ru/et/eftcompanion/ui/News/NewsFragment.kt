package ru.et.eftcompanion.ui.News

import android.os.Bundle
import android.util.Log
import android.view.*
import kotlinx.android.synthetic.main.fragment_news.*
import ru.et.eftcompanion.R
import ru.et.eftcompanion.data.MainRepository
import ru.et.eftcompanion.data.NewsRepository
import ru.et.eftcompanion.di.ApplicationComponent
import ru.et.eftcompanion.mvp.MvpFragment
import ru.et.eftcompanion.util.DateTimeUtil
import javax.inject.Inject


class NewsFragment : MvpFragment<NewsView, NewsPresenter>(), NewsView {

    @Inject
    lateinit var dataService: DateTimeUtil

    @Inject
    lateinit var newsRepository: NewsRepository

    @Inject
    lateinit var mainRepository: MainRepository

    override fun inject(component: ApplicationComponent) {
        component!!.inject(this)
        component.inject(presenter)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_news, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        news_title.text = newsRepository.newsModel.title
        news_date.text = dataService.parseLongDateToString(newsRepository.newsModel.date)
        news_desc.text = newsRepository.newsModel.desc
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()

        mainRepository.initHomeBtn(true)
    }
}