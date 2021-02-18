package dev.kevinlucas.androidsaveinstancestate

import android.graphics.Bitmap
import java.io.Serializable

class ListaImgs(var imgs: ArrayList<Bitmap>) : Serializable {

    companion object IMAGENS {
        val KEY = "imagens"
    }

}