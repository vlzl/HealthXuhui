# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\android-sdk-windows/tools/proguard/proguard-android.txt
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

-keepattributes Signature

#baidu map
-keep class com.baidu.** {*;}
-keep class vi.com.** {*;}
-dontwarn com.baidu.**

#保留所有实体类,gson时用到反射
-keep class com.wondersgroup.healthxuhui.data.entity.**{*;}

#保留support库
-keep class android.support.** {
    *;
}

#okio
-dontwarn okio.**
-keep class okio.**{*;}
-keep interface okio.**{*;}

#okhttp
-dontwarn okhttp3.**
-keep class okhttp3.**{*;}
-keep interface okhttp3.**{*;}

#fresco
-dontwarn com.facebook.**
-keep class com.facebook.**{*;}
-keep interface com.facebook.**{*;}

# Gson
-keep class com.google.gson.**{ *; }
-keep interface com.google.gson.**{*;}



##Retrofit2
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
#-keepattributes Signature
-keepattributes Exceptions


##RxJava
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
   long producerIndex;
   long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

