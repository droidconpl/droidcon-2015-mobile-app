# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /home/jakub/Moje/Android/Sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}


# support design
-dontwarn android.support.design.**
-keep class android.support.design.** { *; }
-keep interface android.support.design.** { *; }
-keep public class android.support.design.R$* { *; }

#support v4
-dontwarn android.support.v4.**

#support v7
-dontwarn android.support.v7.**
-keep class android.support.v7.** { *; }
-keep interface android.support.v7.** { *; }

# butterknife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

# For RoboSpice
#Results classes that only extend a generic should be preserved as they will be pruned by Proguard
#as they are "empty", others are kept
-keep class pl.droidcon.app.model.api.**

-keepclassmembers class pl.droidcon.app.model.api.** {
      public <fields>;
}

#RoboSpice requests should be preserved in most cases
-keepclassmembers class pl.droidcon.app.service.api.** {
  public void set*(***);
  public *** get*();
  public *** is*();
}

#Warnings to be removed. Otherwise maven plugin stops, but not dangerous
-dontwarn android.support.**
-dontwarn com.sun.xml.internal.**
-dontwarn com.sun.istack.internal.**
-dontwarn org.codehaus.jackson.**
-dontwarn org.springframework.**
-dontwarn java.awt.**
-dontwarn javax.security.**
-dontwarn java.beans.**
-dontwarn javax.xml.**
-dontwarn java.util.**
-dontwarn org.w3c.dom.**
-dontwarn com.google.common.**
-dontwarn com.octo.android.robospice.persistence.**
-dontwarn com.octo.android.robospice.SpiceService
-dontwarn com.octo.android.robospice.retrofit.**

# Retrofit
-keep class com.octo.android.robospice.retrofit.** { *; }
-dontwarn rx.**
-dontwarn org.apache.http.**
-dontwarn android.net.http.AndroidHttpClient
-dontwarn retrofit.client.ApacheClient$GenericEntityHttpRequest
-dontwarn retrofit.client.ApacheClient$GenericHttpRequest
-dontwarn retrofit.client.ApacheClient$TypedOutputEntity

# Picasso
-dontwarn com.squareup.okhttp.**

-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
    **[] $VALUES;
    public *;
}