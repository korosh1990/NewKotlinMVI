package ir.androidlife.newkotlinmvi.Util

import io.reactivex.Observable
/**
 * Created by Mojtaba on 5/26/2019.
 */
class DataSource(internal var imageList:List<String>)
{
    fun getImageLinkFromList(index:Int):Observable<String>
    {
        return Observable.just(imageList[index])
    }
}