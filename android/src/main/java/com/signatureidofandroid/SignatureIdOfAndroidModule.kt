package com.signatureidofandroid

import java.security.MessageDigest

import android.content.pm.PackageManager
import android.content.pm.Signature
import android.util.Base64

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.Promise

class SignatureIdOfAndroidModule(reactContext: ReactApplicationContext) :
  ReactContextBaseJavaModule(reactContext) {

  override fun getName(): String {
    return NAME
  }

  @ReactMethod
  fun getSignatureIdAsync(promise: Promise) {
      try {
          val packageName = reactApplicationContext.packageName
          val packageManager = reactApplicationContext.packageManager
          val packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
          val signatures = packageInfo.signatures
          val signature = signatures[0]

          val messageDigest = MessageDigest.getInstance("SHA-256")
          val digest = messageDigest.digest(signature.toByteArray())
          val signatureId = Base64.encodeToString(digest, Base64.DEFAULT)

          promise.resolve(signatureId)
      } catch (e: Exception) {
          promise.reject("ERROR", e.message)
      }
  }

  companion object {
    const val NAME = "SignatureIdOfAndroid"
  }
}
