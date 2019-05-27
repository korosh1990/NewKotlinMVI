package ir.androidlife.newkotlinmvi.Model

import com.hannesdorfmann.mosby3.mvp.MvpView
import ir.androidlife.newkotlinmvi.View.MainViewState
import io.reactivex.Observable

/**
 * Created by Mojtaba on 5/26/2019.
 */
interface MainView : MvpView {
    val imageIntent:Observable<Int> // imageIntent will use Integer index to get image from list

    fun render(viewState: MainViewState)  // Render function will render view base on view state
}