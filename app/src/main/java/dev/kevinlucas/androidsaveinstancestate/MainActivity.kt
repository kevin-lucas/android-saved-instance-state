package dev.kevinlucas.androidsaveinstancestate

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import java.io.IOException
import java.net.URL

class MainActivity : AppCompatActivity() {

    private val handler = Handler()
    private var imgs: ArrayList<Bitmap>? = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            val li = savedInstanceState.getSerializable(ListaImgs.KEY) as ListaImgs?
            imgs = li!!.imgs
        }

        if (imgs == null || imgs!!.size == 0) {
            Log.i("Script", "ENTREI 1")
            loadImg()
        } else {
            Log.i("Script", "ENTREI 2")
            buildImgs()
        }
    }

    fun loadImg() {

        Thread(Runnable {

            try {

                for (i in 1..30) {
                    val url =
                        URL("https://www.thiengo.com.br/img/system/logo/logo-thiengo-calopsita-70x70.png")
                    val conexao = url.openConnection()
                    val input = conexao.getInputStream()
                    imgs!!.add(BitmapFactory.decodeStream(input))
                }

            } catch (e: IOException) {
            }

            handler.post {
                run {
                    buildImgs()
                }
            }

        }).start()
    }

    fun buildImgs() {
        val linearLayout = findViewById<LinearLayout>(R.id.linerLayout1)
        for (i in 0 until imgs!!.size) {
            val iv = ImageView(baseContext)
            iv.setImageBitmap(imgs!![i])
            linearLayout.addView(iv)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putSerializable(ListaImgs.KEY, ListaImgs(imgs!!))
    }
}