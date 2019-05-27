package ir.androidlife.newkotlinmvi.Model

/**
 * Created by Mojtaba on 5/26/2019.
 */
interface PartialMainState {
    class Loading:PartialMainState
    class GotImageLink(var imageLink: String):PartialMainState
    class Error(var error:Throwable):PartialMainState
}