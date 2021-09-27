package by.a_makarevich.rsschooltask5catsretrofit

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import coil.api.load
import kotlinx.android.synthetic.main.layout_view_image.*


class CatViewActivity : AppCompatActivity(R.layout.layout_view_image) {

 private val REQUEST_CODE_PERMISSION_WRITE_EXTERNAL_STORAGE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = intent
        if (intent.hasExtra("IMAGE_URL")) {
            image.load(intent.getStringExtra("IMAGE_URL"))

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
              R.id.menu_save_image -> {
                  getPermissionAndSaveImage()
              }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun saveImageToGallery(){
        image.buildDrawingCache()
        val b: Bitmap = image.getDrawingCache()
        val s = MediaStore.Images.Media.insertImage(this.contentResolver, b, "myImage", "")
        Toast.makeText(this, "The Image Was Saved", Toast.LENGTH_SHORT).show()
    }

    private fun getPermissionAndSaveImage(){
        val permissionStatus =
            ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)

        if (permissionStatus == PackageManager.PERMISSION_GRANTED) {
            saveImageToGallery()
        } else {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                REQUEST_CODE_PERMISSION_WRITE_EXTERNAL_STORAGE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE_PERMISSION_WRITE_EXTERNAL_STORAGE -> {
                if (grantResults.isNotEmpty()
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                    // permission granted
                    saveImageToGallery()
                } else {
                    // permission denied
                    Toast.makeText(this, "PERMISSIONS IS DENIDED!!!", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }

}