package ir.androidlife.newkotlinmvi.View

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.hannesdorfmann.mosby3.mvi.MviActivity
import com.jakewharton.rxbinding2.view.RxView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import ir.androidlife.newkotlinmvi.Model.MainView
import ir.androidlife.newkotlinmvi.R
import ir.androidlife.newkotlinmvi.Util.DataSource
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import java.util.*

class MainActivity : MviActivity<MainView,MainPresenter>() , MainView {


    internal lateinit var imageList:List<String>


    override fun createPresenter(): MainPresenter {
        return MainPresenter(DataSource(imageList))
    }

    override val imageIntent: Observable<Int>
        get() = RxView.clicks(btn_get_data)
                .map{
                   getRandomNumberInRange(0, imageList.size-1)
                }

    private fun  getRandomNumberInRange(min: Int, max: Int): Int? {
        if(min > max)
            throw IllegalArgumentException("Max must be greater than Min")
        val r = Random()
        return r.nextInt(max-min+1)+min
    }

    override fun render(viewState: MainViewState) {
        if(viewState.isLoading)
        {
            progress_bar.visibility = View.VISIBLE
            image_view.visibility = View.GONE
            btn_get_data.isEnabled = false
        }
        else if(viewState.error != null)
        {
            progress_bar.visibility = View.GONE
            image_view.visibility = View.GONE
            btn_get_data.isEnabled = true
            Toast.makeText(this@MainActivity,""+viewState.error!!.message,Toast.LENGTH_LONG).show()
        }
        else if(viewState.isImageViewShow)
        {

            btn_get_data.isEnabled = true

            Picasso.get().load(viewState.imageLink)
                    .fetch(object:Callback{
                        override fun onSuccess() {
                            image_view.alpha = 0f
                            Picasso.get().load(viewState.imageLink).into(image_view)
                            image_view.animate().setDuration(300).alpha(1f).start() // Fade Anim
                            progress_bar.visibility = View.GONE
                            image_view.visibility = View.VISIBLE
                        }

                        override fun onError(e: Exception?) {
                            progress_bar.visibility = View.GONE
                        }

                    })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageList = createImageList()
    }

    private fun  createImageList(): List<String> {
        //List of image link
        return Arrays.asList("https://img.taaghche.ir/frontCover/54880.jpg",
                "https://img.taaghche.ir/frontCover/54685.jpg",
                "https://img.taaghche.ir/frontCover/55164.jpg",
                "https://img.taaghche.ir/frontCover/55167.jpg",
                "https://img.taaghche.ir/frontCover/23242.jpg",
                "https://img.taaghche.ir/frontCover/37096.jpg")
    }
}
