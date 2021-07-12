package com.hakansarac.photoalbum.views

import android.Manifest
import android.app.Activity
import android.content.ClipData
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.hakansarac.photoalbum.R
import java.io.InputStream
import java.lang.Exception

class ProductDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
    }

    fun buttonNewPhotos(view : View){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),1)
        }
        else{
            var intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type = "image/*"
            startActivityForResult(intent, 2);
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,permissions: Array<out String>,grantResults: IntArray) {
        if(requestCode==1){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                var intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
                intent.addCategory(Intent.CATEGORY_OPENABLE)
                intent.type = "image/*"
                startActivityForResult(intent, 2);
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == 2 && resultCode == Activity.RESULT_OK && data != null){
            var imageView : ImageView = findViewById(R.id.image_product_detail)
            var bitmaps : ArrayList<Bitmap> = ArrayList()
            var clipData : ClipData? = data.clipData
            if(clipData!=null){
                for(i in 0 until clipData.itemCount){
                    var imageUri : Uri = clipData.getItemAt(i).uri
                    try{
                        var inputStream : InputStream? = contentResolver.openInputStream(imageUri)
                        var bitmap : Bitmap = BitmapFactory.decodeStream(inputStream)
                        bitmaps.add(bitmap)
                    } catch (e: Exception){
                        e.printStackTrace()
                    }
                }
            } else {
                try{
                    var imageUri : Uri = data.data!!
                    var inputStream : InputStream? = contentResolver.openInputStream(imageUri)
                    var bitmap : Bitmap = BitmapFactory.decodeStream(inputStream)
                    bitmaps.add(bitmap)
                } catch (e: Exception){
                    e.printStackTrace()
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}