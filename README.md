# HiltCoroutines

**HiltCoroutines** is a lightweight Android library designed to simplify the use of coroutine dispatchers and scopes in conjunction with Hilt dependency injection. It aims to reduce boilerplate code and provide a seamless way to inject coroutine dispatchers and scopes, streamlining asynchronous programming in your Android applications.

## Features

- **Effortless Injection:** Easily inject common coroutine dispatchers (IO, Default, Main) directly into your classes.
- **Pre-configured Scopes:** Access predefined coroutine scopes for various use cases, reducing the need for repetitive code.
- **Type-safe Injection:** Leverage custom qualifiers for safe and precise dispatcher injection.
- **Seamless Hilt Integration:** Built to work out-of-the-box with Hilt, following best practices for dependency injection.

## Installation

Add the following dependency to your `build.gradle` file:

```groovy
dependencies {
    implementation 'com.github.seanzor:hiltcoroutines:1.0.0'
}
```

## Usage

### Injecting Dispatchers

Inject specific dispatchers using the `@Dispatcher` qualifier:

```kotlin
class MyRepository @Inject constructor(
    @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun fetchData() = withContext(ioDispatcher) {
        // Perform I/O operations
    }
}
```

### Injecting Coroutine Scopes

Inject pre-configured coroutine scopes for different purposes using the provided qualifiers:

```kotlin
class MyViewModel @Inject constructor(
    @ApplicationScope private val appScope: CoroutineScope,
    @IOScope private val ioScope: CoroutineScope,
    @MainScope private val mainScope: CoroutineScope,
    @StrictScope private val strictScope: CoroutineScope
) {
    fun performBackgroundTask() {
        ioScope.launch {
            // Perform background task
        }
    }

    fun updateUI() {
        mainScope.launch {
            // Update UI
        }
    }

    fun performMakeOrBreakTask() {
        strictScope.launch {
            // Perform task that requires strict execution handling (A regular `Job` and not `SupervisorJob`)
        }
    }
}
```

### Scope Details

- **ApplicationScope, IOScope, MainScope:** These scopes use `SupervisorJob`, allowing child coroutines to fail independently without affecting siblings or the parent.
- **StrictScope:** This scope uses a regular `Job`, ensuring that any exception in a child coroutine cancels the parent and all its siblings, providing strict execution control.

## Best Practices

- **ApplicationScope:** Use for app-wide operations that need to survive configuration changes or span the entire application lifecycle.
- **IOScope:** Ideal for I/O-bound operations such as network requests or database transactions.
- **MainScope:** Best suited for UI-related tasks that interact with the main thread.
- **StrictScope:** Apply for critical tasks requiring all-or-nothing execution, ensuring that any failure in the scope halts the entire operation.

## Testing

The library includes unit tests to ensure comprehensive functionality. You can run these tests using standard Android testing commands.

## Contributing

Feel free to submit a pull request or raise an issue.

## Links

- [Kotlin Coroutines Documentation](https://kotlinlang.org/docs/coroutines-overview.html)
- [Hilt Dependency Injection Guide](https://developer.android.com/training/dependency-injection/hilt-android)
- [Android Jetpack Overview](https://developer.android.com/jetpack)