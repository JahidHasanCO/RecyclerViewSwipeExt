![GitHub Cards Preview](https://github.com/JahidHasanCO/RecyclerViewSwipeExt/blob/master/ART/cover.png)

# RecyclerViewSwipeExt
RecyclerViewSwipeExt is an Android Studio library that simplifies the implementation of swipe-to-delete and swipe-to-action functionality in RecyclerViews. With just a few lines of code, you can add left and right swipe gestures to your RecyclerViews, allowing users to delete or perform custom actions on items with ease. The library is highly customizable, allowing you to modify the swipe animations, icons, and colors to fit your app's design. Get started quickly with the easy-to-follow documentation and sample code. Streamline your app's user experience with RecyclerViewSwipeExt. [![](https://jitpack.io/v/JahidHasanCO/RecyclerViewSwipeExt.svg)](https://jitpack.io/#JahidHasanCO/RecyclerViewSwipeExt)

## Preview 
<img src="https://github.com/JahidHasanCO/RecyclerViewSwipeExt/blob/master/ART/app1.jpg" width="270" height="585"> <img src="https://github.com/JahidHasanCO/RecyclerViewSwipeExt/blob/master/ART/app2.jpg" width="270" height="585"> <img src="https://github.com/JahidHasanCO/RecyclerViewSwipeExt/blob/master/ART/app3.jpg" width="270" height="585"> 

# Installation
**For Gradle:**

**Step 1:** Add the `JitPack` repository to your `build` file
Add it in your root `build.gradle` at the end of repositories:
```sh
	allprojects {
		repositories {
			maven { url 'https://jitpack.io' }
		}
	}
```

**Step 2:** Add the dependency
```sh
dependencies {
	        implementation 'com.github.JahidHasanCO:RecyclerViewSwipeExt:1.0.2'
	}
```

For `Maven` [check this](docs/maven.md) for your reference.

### Documentation
`RecyclerViewSwipeExt`
`Extension function of RecyclerView` 
Parameter | Details
---|---|
context: Context | Interface to global information about an application environment. This is an abstract class whose implementation is provided by the Android system. |

To initialize `RecyclerViewSwipeExt` you need to call this Extension function after `RecyclerView` `object` and pass as a parameter `Context` and `attrs` by default we provide an `null` to `attrs`. 

#### To Call this function

```kotlin
    recyclerview.makeLeftRightSwipeAble(this)
            .setListener(object : SwipeListener {
                override fun onSwipedLeft(position: Int) {
                    // do something
                }
                override fun onSwipedRight(position: Int) {
                    // do something
                }
            })
            .createSwipeAble()
```
<img src="https://github.com/JahidHasanCO/RecyclerViewSwipeExt/blob/master/ART/sample-code.png">

### Other Extension Methods
method | Details |
--------|--
`fun setRightBg(bg: Int)` | ` fun setRightBg(bg: Int)` set right side action background. It take `color resource id` as a Parameter. | 
`fun setLeftBg(bg: Int)` | `fun setLeftBg(bg: Int)` set left side action background. It take `color resource id` as a Parameter. | 
`fun setRightText(text: String)` | `fun setRightText(text: String)` set right side text label. It take `String` as a Parameter. | 
`fun setLeftText(text: String)` | `fun setLeftText(text: String)` set left side text label. It take `String` as a Parameter. | 
`fun setRightImage(imgRef: Int)` | `fun setRightImage(imgRef: Int)` set right side icon . It take `drawable resource id` as a Parameter. | 
`fun setLeftImage(imgRef: Int)` | `fun setLeftImage(imgRef: Int)` set left side icon. It take `drawable resource id` as a Parameter. | 
`fun setTextColor(color: Int)` | `fun setTextColor(color: Int)` set text color. It take `color resource id` as a Parameter. | 
`fun setTextSize(size: Int)` | `fun setTextSize(size: Int)` set text size. It take `int` as a Parameter. | 
`fun setListener(listener: SwipeListener)` | `fun setListener(listener: SwipeListener)` set swipe listener to track swipe. It take `SwipeListener` `object` as a Parameter. | 


### Examples
```koltin
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(this)

        val data = ArrayList<ItemsViewModel>()
        for (i in 1..20) {
            data.add(ItemsViewModel(R.drawable.baseline_outlet_24, "Item $i"))
        }

        val adapter = CustomAdapter(data)
        recyclerview.adapter = adapter
```

```kotlin
 recyclerview.makeLeftRightSwipeAble(this)
            .setListener(object : SwipeListener {
                override fun onSwipedLeft(position: Int) {
                    val deletedItem = data[position]
                    data.removeAt(position)
                    adapter.notifyItemRemoved(position)
                    Snackbar.make(
                        recyclerview,
                        "Item $position Archive",
                        Snackbar.LENGTH_LONG
                    ).setAction(
                        "Undo"
                    ) {
                        data.add(position, deletedItem)
                        adapter.notifyItemInserted(position)
                    }.show()
                }


                override fun onSwipedRight(position: Int) {
                    val deletedItem = data[position]
                    data.removeAt(position)
                    adapter.notifyItemRemoved(position)
                    Snackbar.make(
                        recyclerview,
                        "Item $position Deleted",
                        Snackbar.LENGTH_LONG
                    ).setAction(
                        "Undo"
                    ) {
                        data.add(position, deletedItem)
                        adapter.notifyItemInserted(position)
                    }.show()
                }

            })
            .createSwipeAble()
```

### License
RecyclerViewSwipeExt is [MIT licensed.](LICENSE)

### Contributing 💡
If you want to contribute to this project and make it better with new ideas, your pull request is very welcomed.
If you find any issue just put it in the repository issue section, thank you. 
