package ru.et.eftcompanion.ui.NewsList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_news_list.*
import ru.et.eftcompanion.R
import ru.et.eftcompanion.data.MainRepository
import ru.et.eftcompanion.data.NewsRepository
import ru.et.eftcompanion.di.ApplicationComponent
import ru.et.eftcompanion.model.NewsModel
import ru.et.eftcompanion.mvp.MvpFragment
import ru.et.eftcompanion.util.DateTimeUtil
import java.util.*
import javax.inject.Inject

class NewsListFragment : MvpFragment<NewsListView, NewsListPresenter>(), NewsListView {

    @Inject
    lateinit var dataService: DateTimeUtil

    @Inject
    lateinit var newsRepository: NewsRepository

    @Inject
    lateinit var mainRepository: MainRepository

    private var  newsListAdapter: NewsListAdapter ?= null

    override fun inject(component: ApplicationComponent) {
        component!!.inject(this)
        component.inject(presenter)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_news_list, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
        presenter.getNews()
    }

    override fun createNewsList() {
        val newsModelList = ArrayList<NewsModel>()
        val cal = Calendar.getInstance()
        for (i in 1..8){
            val newsModel = NewsModel()
            newsModel.id = i.toString()
            newsModel.title = "Test news title " + i
            newsModel.desc = "Test news description " + i
            newsModel.date = cal.time.time
            newsModelList.add(newsModel)
        }

        newsListAdapter = NewsListAdapter(newsModelList, dataService)
        news_recycler.adapter = newsListAdapter
        news_recycler.layoutManager = LinearLayoutManager(getContext())
        
        newsListAdapter!!.onItemClick = { newsModel ->
            newsRepository.newsModel = newsModel
            mainRepository.mainActivity.navController.navigate(R.id.action_newsListFragment_to_newsFragment)

        }
    }

    override fun onResume() {
        super.onResume()
        mainRepository.initHomeBtn(false)
        mainRepository.mainActivity.supportActionBar?.title = getString(R.string.news_list_title)
    }

    override fun onPause() {
        super.onPause()

    }
}