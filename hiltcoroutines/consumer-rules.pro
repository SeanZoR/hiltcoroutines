# Preserve Hilt annotations
-keep @javax.inject.Inject class * { *; }
-keep @dagger.Module class * { *; }
-keep @dagger.hilt.InstallIn class * { *; }

# Preserve classes that use reflection
-keep class com.github.seanzor.hiltcoroutines.** { *; }