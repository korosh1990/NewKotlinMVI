package ir.androidlife.newkotlinmvi.View

/**
 * Created by Mojtaba on 5/26/2019.
 */
class MainViewState (internal var isLoading:Boolean,
                     internal var isImageViewShow:Boolean,
                     internal var imageLink:String,
                     internal var error:Throwable?)

