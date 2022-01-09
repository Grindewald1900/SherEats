<p align="center">
  <a href="https://github.com/Grindewald1900/Notebook">
    <img src="https://github.com/Grindewald1900/Notebook/blob/master/Image/Others/Sher.jpg?raw=true" width="900" height="260">
  </a>
</p>

<div id="top"></div>

<!-- PROJECT SHIELDS -->
<p align="center">
    <a href="" alt="Visitors">
        <img src="https://visitor-badge.glitch.me/badge?page_id=Grindewald1900.SherEats" /></a>
    <a href="https://github.com/Grindewald1900/GoBishop/graphs/contributors" alt="Contributors">
        <img src="https://img.shields.io/github/contributors/Grindewald1900/GoBishop" /></a>
    <a href="https://github.com/Grindewald1900/GoBishop/pulse" alt="Activity">
        <img src="https://img.shields.io/github/commit-activity/m/Grindewald1900/GoBishop" /></a>
    <a href="https://github.com/Grindewald1900/GoBishop/issues">
        <img src="https://img.shields.io/github/languages/count/Grindewald1900/GoBishop"></a>
    <a href="https://github.com/Grindewald1900/GoBishop/commits?author=Grindewald1900">
        <img src="https://img.shields.io/github/last-commit/Grindewald1900/SherEats"></a>
    <a href="https://github.com/Grindewald1900/Notebook/blob/master/LICENSE.txt">
        <img src="https://img.shields.io/badge/license-MIT-green"/></a>
    <a href="https://wakatime.com/badge/github/Grindewald1900/SherEats">
        <img src="https://wakatime.com/badge/github/Grindewald1900/SherEats.svg" alt="wakatime"></a>
</p>

<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/Grindewald1900/SherEats/">
    <img src="https://github.com/Grindewald1900/Notebook/blob/master/Image/SherEats/ic_dining_room_48.png" alt="Logo" width="80" height="80">
  </a>

<h3 align="center">SherEats</h3>

  <p align="center">
    This is a Android App based on Jetpack, Firebase, Retrofit2 and MVVM architecture :)
    <br />
    <a href="https://github.com/github_username/repo_name"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/github_username/repo_name">View Demo</a>
    ·
    <a href="https://github.com/github_username/repo_name/issues">Report Bug</a>
    ·
    <a href="https://github.com/github_username/repo_name/issues">Request Feature</a>
  </p>
</div>

<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>

<!-- ABOUT THE PROJECT -->
## About The Project

[![Product Name Screen Shot][product-screenshot]](https://example.com)

This is a Android Native project based on Kotlin, Jetpack and MVVM architecture. Basically we have two branches which are implemented with different backend and network frameworks, `Restful API` and `Firebase`. 

<p align="right">(<a href="#top">back to top</a>)</p>

### Built With

#### Android App

* [Kotlin](https://kotlinlang.org/)
* [Jetpack](https://developer.android.com/jetpack)
* [Firebase](https://firebase.google.com/)
* [Retrofit2](https://square.github.io/retrofit/)
* [MVVM](https://developer.android.com/jetpack/guide)

#### Web Server

* [Java](https://www.java.com/en/)
* [Servlet](https://docs.oracle.com/javaee/7/api/javax/servlet/package-summary.html)
* [Tomcat](https://tomcat.apache.org/)
* [Java](https://www.java.com/en/)

#### Database

* [PostgreSQL](https://www.postgresql.org/)
* [Firebase Storage](https://firebase.google.com/docs/storage/android/upload-files)
* [Realtime Database](https://firebase.google.com/docs/database/android/read-and-write)


<p align="right">(<a href="#top">back to top</a>)</p>


### Project file structure

Root  
├─model  
│  ├─entity   
│  ├─interfaces  
│  └─viewmodel      
├─utils  
│  ├─firebase  
│  └─network  
└─view  
│  └─activity      
│  └─adapter  
│  └─custom  
│  └─fragment  
│  └─resource  
│  └─transformer  



<!-- GETTING STARTED -->
## Implementation
Here's some details and code blocks from the project.  

### Dependencies
<details>

```Bash
    //Common
    implementation "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"
    implementation 'androidx.core:core-ktx:1.6.0'
    implementation 'androidx.appcompat:appcompat:1.3.1'
    implementation 'com.google.android.material:material:1.4.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.1'
    implementation 'androidx.legacy:legacy-support-v4:1.0.0'
    implementation 'androidx.lifecycle:lifecycle-livedata-ktx:2.3.1'
    implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1'
    implementation 'androidx.navigation:navigation-fragment:2.3.5'
    implementation 'androidx.navigation:navigation-ui:2.3.5'
    implementation 'androidx.navigation:navigation-fragment-ktx:2.3.5'
    implementation 'androidx.navigation:navigation-ui-ktx:2.3.5'
    // Network
    implementation 'com.google.code.gson:gson:2.8.7'
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
    implementation 'com.squareup.okhttp3:logging-interceptor:3.5.0'
    implementation 'com.squareup.retrofit2:converter-scalars:2.1.0'
    // Firebase
    implementation platform('com.google.firebase:firebase-bom:29.0.2')
    implementation 'com.google.firebase:firebase-storage-ktx:20.0.0'
    implementation 'com.google.firebase:firebase-database-ktx'
    implementation 'com.google.firebase:firebase-analytics-ktx:20.0.2'
    // Glide
    implementation 'com.github.bumptech.glide:glide:4.12.0'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.12.0'
    // Google Map
    implementation 'com.google.android.gms:play-services-maps:18.0.1'

    // Permission
    implementation 'com.yanzhenjie:permission:2.0.3'

    testImplementation 'junit:junit:4.+'
    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
```
</details>


### MVVM
MVVM indicates `Model`, `View` and `ViewModel`, which facilitates the separation of UI and business logic.

<p align="center">
  <img src="https://i.ibb.co/j36RtKN/MVVM.png" alt="Logo" width="400" >
</p>

For example we have a `DishFragment` which shows a list of dishes  
1. Set Livedata observer `viewModel.getState().observe(viewLifecycleOwner)` in the UI thread.
2. Invoke `viewModel.setAllFirebaseDish()` in the UI thread.
3. `ViewModel` try to retrieve data from server in I/O thread.
4. When the listener(callback) is called, set `state` (STATE_FAIL, STATE_SUCCESS...) according to the result.
5. The callback of observer is invoked when the state got changed.
6. Refresh UI in the UI thread.  

* **Activity/Fragment**
<details>

```Kotlin
class DishFragment : Fragment() {
    private lateinit var binding: DishFragmentBinding
    private lateinit var viewModel: DishViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        
        binding = DataBindingUtil.inflate(inflater, R.layout.dish_fragment, container, false)
        return binding.root
    }

    private fun initView() {
        viewModel = ViewModelProvider(this).get(DishViewModel::class.java)
        viewModel.setAllFirebaseDish()
        viewModel.setState(ConstantUtil.STATE_NULL)
        viewModel.getFirebaseDish().observe(viewLifecycleOwner) {
            binding.rvFragmentDish.adapter = DishAdapter(it)
        }
        viewModel.getState().observe(viewLifecycleOwner){
            binding.swipeFragmentDish.isRefreshing = false
        }

        setScrollListener()
        setFilterListener()
        setOnCheckedListener()
    }


    private fun setScrollListener(){
      ...
    }

    private fun setFilterListener(){
      ...
    }

    private fun setOnCheckedListener(){
      ...
    }
}

```
</details>  


* **Layout**
<details>  

```xml
<?xml version="1.0" encoding="utf-8"?>
<layout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools">

    <androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".view.activity.SettingActivity">

        ......

    </androidx.constraintlayout.widget.ConstraintLayout>
</layout>
```
</details>  


* **ViewModel**
<details>  

```Kotlin
class DishViewModel : BaseViewModel() {
    private var state: MutableLiveData<Int> = MutableLiveData()
    private var dishes: MutableLiveData<List<FirebaseDish>> = MutableLiveData()
    // By default, we provide the dishes with promotion at first
    private var sort = ConstantUtil.SORT_BY_PRICE 

    fun resetDishes(data: List<FirebaseDish>){
        dishes.postValue(data)
    }

    fun getFirebaseDish(): LiveData<List<FirebaseDish>> {
        return dishes
    }

    // Retireve all data from server
    fun setAllFirebaseDish() {
        val firebaseDish: MutableList<FirebaseDish> = mutableListOf()

        RealtimeUtil.dishReference.get().addOnSuccessListener { it ->
            if(it.childrenCount > 0){
                it.children.forEach {
                    firebaseDish.add(it.getValue(FirebaseDish::class.java)!!)
                }
                setState(ConstantUtil.STATE_SUCCESS)
                dishes.postValue(firebaseDish)
            }
        }.addOnFailureListener {
            setState(ConstantUtil.STATE_FAIL)
            it.stackTrace
        }
    }

    fun getState(): LiveData<Int>{
        return state
    }

    // Set state {@see ConstantUtil.STATE_FAIL, ConstantUtil.STATE_SUCCESS}
    fun setState(state: Int){
        this.state.postValue(state)
    }
}
```
</details>  


* **Model**
<details>  

```Kotlin
data class FirebaseDish(
    val restaurantId: String? = null,
    val restaurantName: String? = null,
    val itemId: Long? = null,
    val itemName: String? = null,
    val itemGenre: String? = null,
    val itemPrice: Double? = null,
    val itemPic: String? = null,
    val itemDiscount: Double? = null,
    val itemTaste: Double? = null,
    val itemEnvironment: Double? = null,
    val itemService: Double? = null,
): Serializable
```
</details>   



### Retrofit
1. Build OkHttpClient with Client Builder.
2. Create Retrofit instance.
3. Create Endpoint interface.
4. Make request and deal with callback.

* **Client and instance**
<details> 

```Kotlin
        private val client = OkHttpClient.Builder()
            .addInterceptor(inspector)
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .build()

        fun<T> buildService(service: Class<T>): T{
            return retrofit.create(service)
        }
```
```Kotlin
    protected var request = ServiceBuilder.ServiceBuilder.buildService(EndPointInterface::class.java)

```
</details> 

* **Endpoint**
<details> 

```Kotlin
interface EndPointInterface {
    @POST(ConstantUtil.SERVLET_RESTAURANT)
    fun getRestaurants(@Query("id") id: Int, @Query("daoType") daoType: Int): Call<List<Restaurant>>

    @POST(ConstantUtil.SERVLET_ORDER)
    fun addOrder(@Body orderItem: List<OrderItem>, @Query("daoType") daoType: Int): Call<IntResult>
}
```
</details> 

* **Request and callback**
<details> 

```Kotlin
    fun setRestaurant(id: Int, daoType: Int) {
        call = request.getRestaurants(id, daoType)
        call.enqueue(object : Callback<List<Restaurant>> {
            override fun onResponse(
                call: Call<List<Restaurant>>, response: Response<List<Restaurant>>
            ) {
                if (response.isSuccessful) {
                    backgroundState.postValue(ConstantUtil.BACKGROUND_STATE_NORMAL)
                    restaurants.postValue(response.body())
                }else{
                    backgroundState.postValue(ConstantUtil.BACKGROUND_STATE_NETWORK_ERROR)
                }
            }
            override fun onFailure(call: Call<List<Restaurant>>, t: Throwable) {
                t.stackTrace
            }
        })
    }
```
</details> 


### Firebase Storage
Firebase Cloud Storage for Firebase is built for app developers who need to store and serve user-generated content, such as photos or videos.

* **Upload**
<details> 

```Kotlin
    /**
     *Upload image(local file) to Firebase
     */
    private fun upLoadImage(){
        if (null == imageUri){
            context?.let { ToastUtil.showShortMessage(getString(R.string.hint_no_image), it) }
            return
        }
        val format = ImageUtil.getImageFormat(imageUri!!, context).split("/").last()
        // Only jpg format is allowed
        if (format != "jpg" && format != "jpeg"){
            context?.let { ToastUtil.showShortMessage(getString(R.string.hint_no_jpg), it) }
            return
        }
        val name = LoginStatusUtil.getUserName()
        val childPath = "user/$name.jpg"
        val imageReference = StorageUtil.reference.child(childPath)
        val upLoadTask = imageReference.putFile(imageUri!!)
        // Upload image asynchronously
        upLoadTask.addOnFailureListener{
            it.stackTrace
        }.addOnSuccessListener {
            hideProgressBar()
            context?.let { ToastUtil.showShortMessage(getString(R.string.upload_success), it) }
            dismissDialog()
        }.addOnProgressListener {
            val progress = (100 * it.bytesTransferred) / it.totalByteCount
            showProgressBar(progress)
        }
    }
```
</details> 

* **Download**
<details> 

```Kotlin
    /**
     * Get image for the user profile picture(if login)
     */
    fun setProfileImage(name: String, view: RoundCornerImageView, context: Context){
        val childPath = "user/$name.jpg"
        val pathReference = StorageUtil.reference.child(childPath)
        pathReference.downloadUrl.addOnSuccessListener {
            Glide.with(context)
                .asBitmap()
                .load(it.toString())
                .into(object: CustomTarget<Bitmap>(){
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        view.setImage(resource)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                    }
                })
            imageUrl.postValue(it)
        }
    }
```
</details> 


### Firebase Realtime Database
Realtime database stores and sync data with NoSQL cloud database. Data is synced across all clients in realtime, and remains available when your app goes offline.  

* **Upload data**
<details> 

```Kotlin
    val userReference = database.getReference("users")

    fun addUser(.....){
        val user = FirebaseUser(id, name, userPassword, userGender, userTel, userMail)
        userReference.child(name).setValue(user)
}    
```

</details> 

* **Download data**
<details> 

```Kotlin
        fun getUser(name: String){
            var user: FirebaseUser?
            userReference.child(name).get().addOnSuccessListener {
                if(null != it.getValue(FirebaseUser::class.java)){
                    user = it.getValue(FirebaseUser::class.java)
                    if(null != user){
                        LoginStatusUtil.setUser(......)
                    }
                }
            }.addOnFailureListener {
                it.stackTrace
            }
        }
```
</details> 

* **Set Listener**
<details> 

```Kotlin
    private fun setChatUpdateListener(){
        if(friend == null) return
        RealtimeUtil.chatReference.child(LoginStatusUtil.getUserName()).child(friend!!.userName!!).addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val chat = snapshot.getValue(FirebaseChat::class.java)
                if(null != chat){
                    setChat(chat)
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
```
</details> 



### Custom View


### Transformers

### Animation


## Demo

### Home page
<p align="center"> 
<img src="https://i.ibb.co/d4fwq1G/2.gif" alt="Logo" width="300" >   

### Search filters 
<p align="center"> 
<img src="https://i.ibb.co/9TkM56b/5.gif" alt="Logo" width="300" >  
<img src="https://i.ibb.co/kS0RzPQ/14.gif" alt="Logo" width="300" >
<img src="https://i.ibb.co/WN9Cn70/15.gif" alt="Logo" width="300" ></p>


### Add item to cart
<p align="center"> 
<img src="https://i.ibb.co/9TkM56b/15.gif" alt="Logo" width="300" >
</p>

### Login and register


### User information page


### Add friend

### Chat page



<p align="center">
  <img src="" alt="Logo" width="400" ></p>












```Kotlin

```

```Kotlin

```










<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE.txt` for more information.

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- CONTACT -->
## Contact

Your Name - [@twitter_handle](https://twitter.com/twitter_handle) - email@email_client.com

Project Link: [https://github.com/github_username/repo_name](https://github.com/github_username/repo_name)

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- ACKNOWLEDGMENTS -->
## Acknowledgments

* []()
* []()
* []()

<p align="right">(<a href="#top">back to top</a>)</p>
