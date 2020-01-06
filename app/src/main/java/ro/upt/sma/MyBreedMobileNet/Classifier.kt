package ro.upt.sma.MyBreedMobileNet

import android.graphics.Bitmap

interface Classifier {
    fun recognize(bitmap : Bitmap) : List<Recognition>
}