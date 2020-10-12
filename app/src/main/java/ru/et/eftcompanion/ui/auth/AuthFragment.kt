package ru.et.eftcompanion.ui.auth

import android.os.Bundle
import android.view.*
import kotlinx.android.synthetic.main.fragment_auth.*
import ru.et.eftcompanion.R
import ru.et.eftcompanion.data.MainRepository
import ru.et.eftcompanion.data.Prefs
import ru.et.eftcompanion.di.ApplicationComponent
import ru.et.eftcompanion.mvp.MvpFragment
import javax.inject.Inject

class AuthFragment : MvpFragment<AuthView, AuthPresenter>(), AuthView {

    @Inject
    lateinit var mainRepository: MainRepository

    @Inject
    lateinit var prefs: Prefs

    override fun inject(component: ApplicationComponent) {
        component!!.inject(this)
        component.inject(presenter)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()

        mainRepository.initHomeBtn(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_auth, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        user_btn.setOnClickListener { view ->
            hideKeyboard(mainRepository.mainActivity)
            mainRepository.mainActivity.checkLoginSuccess()
            mainRepository.mainActivity.navController.popBackStack()
        }
    }

}