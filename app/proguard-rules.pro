# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-include proguard/moshi.pro
-include proguard/okhttp.pro
-include proguard/retrofit2.pro
-include proguard/glide.pro

-android

-repackageclasses ''
-flattenpackagehierarchy ''
-allowaccessmodification
-verbose
-optimizationpasses 10
-dontpreverify
-printseeds

# Keep SourceFile attribute. Required to line number to be preserved.
-keepattributes SourceFile, LineNumberTable
# Rename keeped SourceFile attribute to make deobfuscation harder.
-renamesourcefileattribute SourceFile

-keepattributes Exceptions

-keepnames class * implements android.os.Parcelable
-keepclassmembers class * implements android.os.Parcelable {
  public static final *** CREATOR;
}

# Specifies not to ignore non-public library classes.
-dontskipnonpubliclibraryclasses

# Specifies not to generate mixed-case class names while obfuscating.
-dontusemixedcaseclassnames