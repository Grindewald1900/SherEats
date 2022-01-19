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

<h2 align="center">SherEats</h2>

  <p align="center">
    This is an Android App based on Jetpack, Firebase, Retrofit2 and MVVM architecture :)
    <br />
    <a href="https://github.com/github_username/repo_name"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://www.youtube.com/watch?v=NbUv4j0EZss">View Demo</a>
    ·
    <a href="https://github.com/Grindewald1900/SherEats/issues">Report Bug</a>
    ·
    <a href="https://github.com/Grindewald1900/SherEats/issues">Request Feature</a>
  </p>
</div>



<!-- TABLE OF CONTENTS -->
## Table of Contents
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
      <ul>
        <li><a href="#project-file-structure">Project file structure</a></li>
      </ul>
    </li>
    <li>
      <a href="#implementation">Implementation</a>
      <ul>
        <li><a href="#dependencies">Dependencies</a></li>
      </ul>
      <ul>
        <li><a href="#mvvm">MVVM</a></li>
      </ul>
      <ul>
        <li><a href="#retrofit">Retrofit</a></li>
      </ul>
      <ul>
        <li><a href="#firebase-storage">Firebase Storage</a></li>
      </ul>
      <ul>
        <li><a href="#firebase-realtime-database">Firebase Realtime Database</a></li>
      </ul>
      <ul>
        <li><a href="#custom-view">Custom View</a></li>
      </ul>
      <ul>
        <li><a href="#transformers">Transformers</a></li>
      </ul>
    </li>
    <li>
      <a href="#demo">Demo</a>
        <ul>
        <li><a href="#video-demo">Video demo</a></li>
        </ul>
        <ul>
        <li><a href="#demo">Image demo</a></li>
        </ul>     
    </li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>


<!-- ABOUT THE PROJECT -->
## About The Project

This is a Android Native project based on Kotlin, Jetpack and MVVM architecture. Basically we have two branches which are implemented with different backend and network frameworks, `Restful API` and `Firebase`. 
* [Branch with Firebase](https://github.com/Grindewald1900/SherEats) *- current master branch*
* [Branch with Firebase and Restful API](https://github.com/Grindewald1900/SherEats/tree/Retrofit2) *- only for test on local host*

### Get Early Access
<p align="center">
  <img src="https://github.com/Grindewald1900/Notebook/blob/master/Image/SherEats/My_App_3.png?raw=true" alt="Logo" width="300" >
</p>


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
As mentioned above, this project contains two main branches  
* **master**: a branch with backend totally based on `Firebase`.
* **retrofit2**: a branch with hybrid backend, Login/Register, user/restaurant/badge/ related interfaces are based on `Retrofit` and HTTP `Web server`. Others like image and instant message are based on `Firebase`.
<p align="center">
  <img src="https://i.ibb.co/NsbPcDb/Architecture.png" alt="Logo" width="600" >
</p>



### Dependencies
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
```

```Bash
    // Network
    implementation 'com.google.code.gson:gson:2.8.7'
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
    implementation 'com.squareup.okhttp3:logging-interceptor:3.5.0'
    implementation 'com.squareup.retrofit2:converter-scalars:2.1.0'
```

```Bash
    // Firebase
    implementation platform('com.google.firebase:firebase-bom:29.0.2')
    implementation 'com.google.firebase:firebase-storage-ktx:20.0.0'
    implementation 'com.google.firebase:firebase-database-ktx'
    implementation 'com.google.firebase:firebase-analytics-ktx:20.0.2'
```

```Bash
    // Glide
    implementation 'com.github.bumptech.glide:glide:4.12.0'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.12.0'
```

```Bash
    // Google Map
    implementation 'com.google.android.gms:play-services-maps:18.0.1'
```

```Bash
    // Permission
    implementation 'com.yanzhenjie:permission:2.0.3'
```

```Bash
    testImplementation 'junit:junit:4.+'
    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
```


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
1. Build `OkHttpClient` with Client Builder.
2. Create Retrofit `instance`.
3. Create `Endpoint` interface.
4. Make `request` and deal with `callback`.

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
**CounterLayout** 
<p align="center"> <img src="https://i.ibb.co/6NbpVjn/12.gif" alt="Logo" width="300" > </p>

<details> 

```Kotlin
/**
 * A layout for cart adapter, including plusButton, countView, minusButton.
 */
class CounterLayout(private val mContext: Context, private val attrs: AttributeSet): ConstraintLayout(mContext, attrs){
    private lateinit var mHolder: RefreshCart
    private lateinit var mOrderItem: FirebaseOrderItem
    private var ivPlus: ImageView
    private var ivMinus: ImageView
    private var tvCount: TextView
    private var mCount: Long = 0

    init {
        val view: View = LayoutInflater.from(mContext).inflate(R.layout.layout_counter, this, true)
        ivPlus = view.findViewById(R.id.iv_layout_counter_plus)
        ivMinus = view.findViewById(R.id.iv_layout_counter_minus)
        tvCount = view.findViewById(R.id.tv_layout_counter)
        setListener()
    }

    private fun setListener(){
        val animatorSet = AnimatorInflater.loadAnimator(mContext, R.animator.animator_counter_button).setDuration(500) as AnimatorSet
        animatorSet.addListener(object: Animator.AnimatorListener{
            override fun onAnimationStart(p0: Animator?) {}
            override fun onAnimationRepeat(p0: Animator?) {}
            override fun onAnimationCancel(p0: Animator?) {}
            override fun onAnimationEnd(p0: Animator?) {
                updateCount(null)
            }
        })
        ivPlus.setOnClickListener {
            animatorSet.setTarget(it)
            animatorSet.start()
            mCount ++
        }
        ivMinus.setOnClickListener {
            animatorSet.setTarget(it)
            animatorSet.start()
            mCount --
        }
    }


    /**
     * If set null as parameter, update the textview according to {@see mCount}
     */
    private fun updateCount(count: Long?){
        if(count != null){
            mCount = count
        }
        tvCount.text = mCount.toString()
        mOrderItem.itemAmount = mCount
        SingletonUtil.updateCart(mOrderItem)
        if (mCount <= 0){
            mHolder.refreshData()
        }
        mHolder.refreshPrice()
    }

    fun setHolder(holder: RefreshCart){
        mHolder = holder
    }

    fun setOrderItem(orderItem: FirebaseOrderItem){
        mOrderItem = orderItem
        mCount = mOrderItem.itemAmount!!
        tvCount.text = mCount.toString()
    }
}
```
</details> 

**FavoriteButton** 
<p align="center"> <img src="https://i.ibb.co/7yGtvJ9/1.gif" alt="Logo" width="300" > </p>
<details> 

```Kotlin
class FavoriteButton(private val mContext: Context, private val attrs: AttributeSet) : AppCompatImageView(mContext, attrs), View.OnClickListener {
    private var isFavorite: Boolean = false
    private var drawableTrue: Drawable?
    private var drawableFalse: Drawable?
    private var mPaint: Paint
    private var mHolder: RefreshData? = null
    init {
        val attr = mContext.obtainStyledAttributes(attrs, R.styleable.FavoriteButton)
        drawableTrue  = ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_favorite_48, null)
        drawableFalse  = ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_favorite_border_48, null)
        mPaint = Paint()
        setOnClickListener(this)
    }

    /**
     * Switch the image according to isFav.
     * This method will be called from Activity/Fragment/Adapter where the ViewGroup hold this view
     */
    fun setImage(isFav: Boolean){
        isFavorite = isFav
        if (isFavorite){
            setImageResource(R.drawable.ic_baseline_favorite_48)
        }else{
            setImageResource(R.drawable.ic_baseline_favorite_border_48)
        }
    }

    fun setHolder(holder: RefreshData){
        mHolder = holder
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

    /**
     * Perform click, when clicked, switch the image and start a fade animation
     */
    override fun onClick(view: View?) {
        mHolder?.refreshData(!isFavorite)
        if (isFavorite){
            val animation = animate().alpha(0f).scaleX(0f).scaleY(0f).setDuration(300)
            animation.setListener(object: Animator.AnimatorListener{
                override fun onAnimationStart(p0: Animator?) {
                }
                override fun onAnimationEnd(p0: Animator?) {
                    ToastUtil.showShortMessage("Dislike", mContext)
                    // Remove the listener, or this method could be called multi times
                    animation.setListener(null)
                    setImage(!isFavorite)
                    animate().alpha(1f).scaleX(1f).scaleY(1f).setDuration(300).start()
                }
                override fun onAnimationCancel(p0: Animator?) {
                }
                override fun onAnimationRepeat(p0: Animator?) {
                }
            }).start()
        }else{
            val animation = animate().alpha(0f).scaleX(0f).scaleY(0f).setDuration(300)
            animation.setListener(object: Animator.AnimatorListener{
                override fun onAnimationStart(p0: Animator?) {
                }
                override fun onAnimationEnd(p0: Animator?) {
                    ToastUtil.showShortMessage("Like", mContext)
                    animation.setListener(null)
                    setImage(!isFavorite)
                    animate().alpha(1f).scaleX(1f).scaleY(1f).setDuration(300).start()
                }
                override fun onAnimationCancel(p0: Animator?) {
                }
                override fun onAnimationRepeat(p0: Animator?) {
                }
            }).start()
        }
    }
}
```
</details> 


**RoundFab**   
The floating action bar, which is draggable and back to edge when finger is up. Also click listener is supported. It'll start a new activity when clicked.  

<p align="center"> 
<img src="https://i.ibb.co/9v6WcDV/42.gif" alt="Logo" width="300" > 
<img src="https://i.ibb.co/1rWvZ4C/43.gif" alt="Logo" width="300" ></p>
<p align="center"> <img src="https://i.ibb.co/HVF1ZsJ/44.gif" alt="Logo" width="300" > </p>

<details> 

```Kotlin

/**
 * A draggable floating action button
 */
class RoundFab : androidx.appcompat.widget.AppCompatImageView{
    companion object {
        const val MODE_SQUARE = 0
        const val MODE_CIRCLE = 1
        const val MODE_ROUND_CORNER = 2
    }

    // Set FAB as circle by default
    private lateinit var mContext: Context
    private var currentMode = MODE_CIRCLE
    private var cornerRadius = 0f
    private lateinit var mPaint: Paint

    // The size of screen
    private var screenWidth = 0
    private var screenHeight = 0

    // The size of Fab
    private var fabWidth = 0
    private var fabHeight = 0
    private var fabSize = 0

    // The position when event down
    private var downX = 0f
    private var downY = 0f

    private var destination: Int = ConstantUtil.ACTIVITY_SEARCH_DISH


    constructor(context: Context) : super(context) {
        initView(null, 0, context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView(attrs, 0, context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        obtainAttributes(context, attrs, defStyle)
        initView(attrs, defStyle, context)
    }

    /**
     * Retrieve the custom attributes
     */
    private fun obtainAttributes(context: Context, attrs: AttributeSet, defStyle: Int) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundFab, defStyle, 0)
        if (typedArray.hasValue(R.styleable.RoundFab_fab_type)) {
            currentMode = typedArray.getInt(R.styleable.RoundFab_fab_type, MODE_CIRCLE)
        }
        if (typedArray.hasValue(R.styleable.RoundFab_fab_corner_radius)) {
            cornerRadius = typedArray.getFloat(R.styleable.RoundFab_fab_corner_radius, 20f)
        }
        typedArray.recycle()
    }

    private fun initView(attrs: AttributeSet?, defStyle: Int, context: Context) {
        mContext = context
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    }


    /**
     * Set the width and height of the view
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        // requested width and mode
        if (currentMode == MODE_CIRCLE) {
            val size = measuredHeight.coerceAtMost(measuredWidth)
            setMeasuredDimension(size, size)
        }
        screenWidth = ImageUtil.getScreenWidth(mContext)
        screenHeight = ImageUtil.getScreenHeight(context)
        fabWidth = measuredWidth
        fabHeight = measuredHeight
        if (fabHeight == fabWidth) {
            fabSize = fabWidth/2
        }
    }


    /**
     * Deal with different motion event
     */
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event == null) return true

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                downX = event.rawX
                downY = event.rawY
                if (ConstantUtil.DEBUG_MODE){
                    Log.d("LogRoundFab", "ACTION_DOWN")
                    Log.d("LogRoundFab", "x ${event.rawX.toString()}")
                    Log.d("LogRoundFab", "y ${event.rawY.toString()}")
                }
            }
            MotionEvent.ACTION_UP -> {
                if(ConstantUtil.DEBUG_MODE){
                    Log.d("LogRoundFab", "ACTION_UP")
                    Log.d("LogRoundFab", "x ${event.rawX.toString()}")
                    Log.d("LogRoundFab", "y ${event.rawY.toString()}")
                }
                // Move the FAB to the proper position, according to the Mode
                val position = ImageUtil.getPositionByMode(
                    event.rawX.toInt(),
                    event.rawY.toInt(),
                    fabWidth,
                    fabHeight,
                    ConstantUtil.MODE_EDGE,
                    mContext
                )
                this.layout(position[0], position[1], position[2], position[3])
                if(abs(downX - event.rawX) < 10 && abs(downY - event.rawY) < 10){
                    doClick()
                }

            }
            MotionEvent.ACTION_MOVE -> {
                var left: Int; var right: Int; var top: Int; var bottom: Int
                left = event.rawX.toInt()
                right = left + fabWidth
                top = event.rawY.toInt()
                bottom = top + fabHeight
                // Avoid FAB out of the screen area
                if (left < 0) {
                    left = 0
                    right = left + fabWidth
                } else if (right > screenWidth + fabWidth) {
                    right = screenWidth
                    left = right - fabWidth
                }
                if (top < 0) {
                    top = 0
                    bottom = top + fabHeight
                } else if (bottom > screenHeight + fabHeight) {
                    bottom = screenHeight
                    top = bottom - fabHeight
                }
                // Move the view a little bit to top left, which means the touching point is at the center of FAB
                this.layout(left-fabSize , top-fabHeight, right-fabSize, bottom-fabHeight)
            }
        }
        return true
    }

    override fun onDraw(canvas: Canvas) {
        if (drawable.intrinsicWidth == 0 || drawable.intrinsicHeight == 0 || drawable == null) {
            return
        }
        if (imageMatrix == null && paddingTop == 0 && paddingLeft == 0) {
            drawable.draw(canvas)
        } else {
            val saveCount = canvas.saveCount
            canvas.save()
            canvas.translate(paddingLeft.toFloat(), paddingTop.toFloat())
            if (currentMode == MODE_CIRCLE) {
                val bitmap = ImageUtil.drawableToBitmap(drawable, imageMatrix, width, height)
                mPaint.shader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
                canvas.drawCircle(width / 2f, height / 2f, width / 2f, mPaint)
            } else if (currentMode == MODE_ROUND_CORNER) {
                val bitmap = ImageUtil.drawableToBitmap(drawable, imageMatrix, width, height)
                mPaint.shader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
                canvas.drawRoundRect(RectF(paddingLeft.toFloat(), paddingTop.toFloat(), (width - paddingRight).toFloat(), (height - paddingBottom).toFloat()), cornerRadius, cornerRadius, mPaint)
            } else {
                if (imageMatrix != null) {
                    canvas.concat(imageMatrix)
                    drawable.draw(canvas)
                }
            }
            canvas.restoreToCount(saveCount)

        }

    }

    fun setDestination(dest: Int){
        destination = dest
    }

    private fun doClick(){
        val animator = this.animate().alpha(0f).scaleX(0f).scaleY(0f).setDuration(300)
        animator.setListener(object: Animator.AnimatorListener{
            override fun onAnimationEnd(p0: Animator?) {
                val intent: Intent = when(destination){
                    ConstantUtil.ACTIVITY_SEARCH_DISH -> {
                        Intent(mContext, SearchActivity::class.java)
                    }
                    ConstantUtil.ACTIVITY_SEARCH_FRIEND -> {
                        Intent(mContext, SearchFriendActivity::class.java)
                    }
                    else -> {
                        Intent(mContext, SearchActivity::class.java)
                    }
                }
                mContext.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(mContext as MainActivity).toBundle())
                animator.setListener(null)
                animator.alpha(1f).scaleX(1f).scaleY(1f).setDuration(100).start()
            }
            override fun onAnimationStart(p0: Animator?) {}
            override fun onAnimationCancel(p0: Animator?) {}
            override fun onAnimationRepeat(p0: Animator?) {}

        }).start()
    }

}
```
</details> 


**SearchFilterLayout** 
<p align="center"> <img src="https://i.ibb.co/Sf5W943/45.gif" alt="Logo" width="300" > </p>
<details> 

```Kotlin
/**
 * Created by Yee on 2021-12-24.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class SearchFilterLayout: ConstraintLayout {
    var mState = ConstantUtil.FILTER_STATE_NONE
    private var mTitle: String? = "text"
    private lateinit var mTextView: TextView
    private lateinit var mViewUp: ImageView
    private lateinit var mViewDown: ImageView

    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet): super(context, attrs){
        initView(context, attrs, 0)
    }
    constructor(context: Context, attrs: AttributeSet, defStyle: Int): super(context, attrs, defStyle){
        initView(context,attrs,defStyle)
    }

    private fun initView(context: Context, attrs: AttributeSet, defStyle: Int){
        val attr = context.obtainStyledAttributes(attrs, R.styleable.SearchFilterLayout)
        val view = LayoutInflater.from(context).inflate(R.layout.layout_search_filter, this, true)

        mTitle = attr.getString(R.styleable.SearchFilterLayout_search_filter_text)

        mTextView = view.findViewById(R.id.tv_layout_search_filter)
        mViewUp = view.findViewById(R.id.iv_layout_search_filter_up)
        mViewDown = view.findViewById(R.id.iv_layout_search_filter_down)
        if(!mTitle.isNullOrEmpty()){
            mTextView.text = mTitle
        }
    }

    // Highlight the up button
    fun setStateUp(){
        mViewUp.setBackgroundResource(R.drawable.ic_iconmonstr_triangle_up_color)
        mViewDown.setBackgroundResource(R.drawable.ic_iconmonstr_care_down_thin)
        this.setBackgroundResource(R.drawable.shape_text_view_selected)
        mState = ConstantUtil.FILTER_STATE_UP
    }

    // Highlight the down button
    fun setStateDown(){
        mViewUp.setBackgroundResource(R.drawable.ic_iconmonstr_care_up_thin)
        mViewDown.setBackgroundResource(R.drawable.ic_iconmonstr_triangle_down_color)
        this.setBackgroundResource(R.drawable.shape_text_view_selected)
        mState = ConstantUtil.FILTER_STATE_DOWN
    }

    // Set the view to unchecked
    fun setStateNone(){
        mViewUp.setBackgroundResource(R.drawable.ic_iconmonstr_care_up_thin)
        mViewDown.setBackgroundResource(R.drawable.ic_iconmonstr_care_down_thin)
        this.background = null
        mState = ConstantUtil.FILTER_STATE_NONE
    }

    fun getTitle(): String?{
        return mTitle
    }

    fun getState(): Int{
        return mState
    }
}
```
</details> 




### Transformers
**ZoomOutPageTransformer** 
<p align="center"> <img src="https://i.ibb.co/hDSgWK0/38.gif" alt="Logo" width="300" > </p>
<details> 

```Kotlin
class ZoomOutPageTransformer: ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        page.apply {
            when{
                position <= 2 -> {
                    // From right or left side, the view keep the scale of MIN_TRANSFORM_SCALE, unless it's near the center(position 0)
                    val scaleFactor = max(ConstantUtil.MIN_TRANSFORM_SCALE, 1 - abs(position))
                    val alphaFactor = max(ConstantUtil.MIN_TRANSFORM_ALPHA, 1 - abs(position))
                    scaleX = scaleFactor
                    scaleY = scaleFactor
                    alpha = alphaFactor
                }
                else -> {}
            }
        }
    }
}
```
</details> 


## Demo
### [Video Demo](https://www.youtube.com/watch?v=NbUv4j0EZss)  
  
### Home page
If user is not logged in, the shopping cart and order page should be empty.
<p align="center"> 
<img src="https://i.ibb.co/d4fwq1G/2.gif" alt="Logo" width="300" >   
<img src="https://i.ibb.co/fSLQfWx/9.gif" alt="Logo" width="300" >   
</p>


<!-- ### Friend page
<p align="center"> 
<img src="https://i.ibb.co/7yGwtz5/53.gif" alt="Logo" width="600" >
</p>

<p align="center"> 
<img src="https://i.ibb.co/JkhzVKW/54.gif" alt="Logo" width="600" >
</p> -->

### Add friend
<p align="center"> 
<img src="https://i.ibb.co/TKXhX5F/56.gif" alt="Logo" width="600" >
</p>

<p align="center"> 
<img src="https://i.ibb.co/ZfKg6JY/57.gif" alt="Logo" width="600" >
</p>

<p align="center"> 
<img src="https://i.ibb.co/SsNjvby/58.gif" alt="Logo" width="600" >
</p>

<p align="center"> 
<img src="https://i.ibb.co/6rvxMVg/59.gif" alt="Logo" width="600" >
</p>

### Chat page
<p align="center"> 
<img src="https://i.ibb.co/PQB4D0Q/51.gif" alt="Logo" width="600" >
</p>

<p align="center"> 
<img src="https://i.ibb.co/4VdmCBN/52-c.gif" alt="Logo" width="600" >
</p>

<p align="right">(<a href="#top">back to top</a>)</p>
  
### Search filters 
We provide 3 different filters
* **Price**: the price of food
* **Rate**: the average rate(0-5 stars)
* **Promotion**: if food is on sale

<p align="center"> 
<img src="https://i.ibb.co/9TkM56b/5.gif" alt="Logo" width="300" >  
<img src="https://i.ibb.co/kS0RzPQ/14.gif" alt="Logo" width="300" ></p>





### Add item to cart
Before we get it started, we need to login (or register if first time)
<p align="center"> 
<img src="https://i.ibb.co/mN3L876/22.gif" alt="Logo" width="300" >
<img src="https://i.ibb.co/zQZhxH1/24.gif" alt="Logo" width="300" >
</p> 
We can easily change the number of a certain item.
<p align="center"> 
<img src="https://i.ibb.co/Xy8DCKg/25.gif" alt="Logo" width="300" >
<img src="https://i.ibb.co/6NbpVjn/12.gif" alt="Logo" width="300" >
</p>

By default, any item with 0 will be removed from cart.
<p align="center">
<img src="https://i.ibb.co/vQFkR5S/26.gif" alt="Logo" width="300" > </p
>

Finally, click on 'Check out', the order will be sychronyzed at server end.
<p align="center"> 
<img src="https://i.ibb.co/VjCxwx8/13.gif" alt="Logo" width="300" >
</p>

Also, we can check the order page for more details. 
<p align="center"> 
<img src="https://i.ibb.co/2t8mNcr/27.gif" alt="Logo" width="300" >
</p>
<p align="right">(<a href="#top">back to top</a>)</p>




### Search page
We have a little floating action bar at home page, which is like a magnifying glass.   
You may click this action bar at:
* Home page/ dish page/ order page: go to search page.
* Friend page: go to friend search page  


Normally, we search a keyword without any filter, the feedback will include every piece of record that contains the keyword.
<p align="center"> 
<img src="https://i.ibb.co/SdGpPmK/31.gif" alt="Logo" width="300" >
</p>

Here we search the keyword 'sh'. Obviously the results are different with diffenrent filters. 

<p align="center"> 
<img src="https://i.ibb.co/CHMSRJ4/32.gif" alt="Logo" width="300" >
<img src="https://i.ibb.co/hgm2C8L/33.gif" alt="Logo" width="300" >
</p>
<br></br>

<p align="center"> 
<img src="https://i.ibb.co/wSz2K5x/34.gif" alt="Logo" width="300" >
<img src="https://i.ibb.co/f97s50j/35.gif" alt="Logo" width="300" >
</p>

<p align="right">(<a href="#top">back to top</a>)</p>




### Login and register
<p align="center"> 
<img src="https://i.ibb.co/PxMpKkP/16.gif" alt="Logo" width="300" >
<img src="https://i.ibb.co/DVk2dJ7/23.gif" alt="Logo" width="300" >
</p>
<br></br>

<p align="center"> 
<img src="https://i.ibb.co/C2R3YnH/18.gif" alt="Logo" width="300" >
<img src="https://i.ibb.co/0rYg0nK/6.gif" alt="Logo" width="300" >
</p>
<p align="right">(<a href="#top">back to top</a>)</p>




### User information page
Here a list of badges will be displayed. New users have nothing to show cause they have no badge. 
<p align="center"> 
<!-- <img src="https://i.ibb.co/26RwRK1/41.gif" alt="Logo" width="300" > -->
  <img src="https://i.ibb.co/TLsXvKb/36.gif" alt="Logo" width="300" ></p>


We could add badges at setting page.
<p align="center"> 
<img src="https://i.ibb.co/hDSgWK0/38.gif" alt="Logo" width="300" >
  <img src="https://i.ibb.co/f1CqFQn/39.gif" alt="Logo" width="300" ></p>

Also, we could set profile image from local image library. 
<p align="center"> 
<img src="https://i.ibb.co/0qFJBw6/21.gif" alt="Logo" width="300" >
<img src="https://i.ibb.co/5rMK171/37.gif" alt="Logo" width="300" >
</p>
<p align="right">(<a href="#top">back to top</a>)</p>








<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE.txt` for more information.

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- CONTACT -->
## Contact

Grindewald1900 - [Github](https://github.com/Grindewald1900) - grindewald1504@gmail.com

Project Link: [SherEats](https://github.com/Grindewald1900/SherEats)

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- ACKNOWLEDGMENTS -->
## Acknowledgments


<p align="right">(<a href="#top">back to top</a>)</p>
