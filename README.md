AyoSunny
========================

##前言：这是什么

* AyoSunny，哎呦我日，包含：
    * AyoSdk：常用工具类
    * AyoViewLib：控件库
    * Log，ImageLoad，Http，EventBus，DB，Inject，Json & xml，Log，IO，Thread，Cache，Crypt等常用模块的封装及研究
    * 一堆尽量保持与时俱进的第三方库demo及分析，牛人博客拜读和整合。。。。
    * 面儿相当广，每个知识点分析的都特别深
    * 面面俱到的文档，用时，查的快，学时，懂的深
    * 从不满足于浅尝辄止，从不说会用就行，千言万语汇成一句，得搞透
    * 每个模块都做到完全模块化，常用功能放入AyoSdk或者AyoViewLib，不常用的直接扔到sample里
    * 本版本力求做到更加简洁，更加模块化，以轻量级可配置化的姿态重新进入技术界

	
##目录

* 基础设施:
    * [Ayo库怎么用](./doc/doc-doc.md)
    * [常用工具类](./doc/doc-common.md)
    * [控件注入](./doc/doc-inject.md)
        * [ButterKnife研究]
        * [dagger研究]
    * [日志系统](./doc/doc-log.md)
        * [JLog：小众化]
        * [Logger：大众化]
        * [崩溃日志]
        * [用户行为日志]
    * [IO操作](./doc/doc-io.md)
	    * [SharedPrefernce]
		* [安卓文件操作：SD卡，上下文目录，assets，raw，res资源]
		* [流：原生]
		* [流：Okio]
    * [http请求](./doc/doc-http.md)
	    * [AyoHttp框架]
		* [UrlConnection，OKHttp]
		* [XUtils：http请求，下载文件，上传文件]
        * [Volly教程和源码解析](./doc/doc-volly.md)
	    * [retrofit教程和源码解析]
	* [json解析]
	    * [原生json和泛型问题]
		* [Bean嵌套，Map，List，"1,2,3,4"的解析]
	    * [FastJson]
	    * [Gson]
	    * [AyoJson：顶层框架]
	* [xml解析](./doc/doc-xml.md)
	    * [原生]
	    * [第三方库，orm方式解析]
    * [网络图片加载](./doc/doc-onlineimage.md)
	    * [两层缓存]
	    * [Ayo-Vangoph]
	    * [UniversalImageDownloader]
		* [Fresco]
	    * [Picasso]
	    * [Glide]
    * [多线程](./doc/doc-async.md)
		* [安卓消息队列机制]
	    * [java多线程]
		* [RxJava]
	* [多进程]
	    * [怎么用，何时用，怎么证明开了多个进程，多个进程之间的关系]
		* [极光，友盟等的单进程是咋回事，有何意义]
		* [我们啥时能用上]
		* [不死服务]
    * [缓存](./doc/doc-cache.md)
        * [SharedPreference封装]
        * [简单版：Json配合SharedPreference]
        * [LruCache]
    * [数据库](./doc/doc-database.md)
        * [原生SqlHelper用法]
        * [原生 + RxJava：square的sqlbrite]
        * [orm：XUtils]
        * [orm：greendao]
        * [orm：ormlite]
    * [事件总线：EventBus](./doc/doc-eventbus.md)
        * [EventBus]
        * [otto]
    * [推送]
        * [mqtt：自己搭个服务器，自己封装sdk]
        * [极光等第三方推送]
    * [binder]
        * [binder机制]
        * [aidl:http://android.blog.51cto.com/268543/537684/]
    * [IM通信]
        * [openfire]
        * [蘑菇街开源IM框架]
        * [自定义聊天组件：基于融云]
    * [加密解密](./doc/doc-crypt.md)
    * [手机功能接口]
        * [读取联系人，短信]
        * [拦截短信]
        * [解锁屏幕，保持屏幕点亮等]
        * [各种广播receiver]
    * [多模块开发研究--加载，资源文件的加载]
        * [插件化发布研究]
        * [热补丁]

####
* UIFramework：控件
    * [Activity研究]
        * 生命周期
        * onSaveInstance
        * 连续打开多个Activity
        * SingleTask：不能作为splash使用
        * 横竖屏切换
        * ActionBar
        * manifest配置研究
        * 主题研究
    * [Fragment应用及其通信](./doc/v_fragment.md)
    * [Activity免声明框架：ActivityAttacher]
    * [状态栏控制](./doc/README-ayo.md)
    * [Drawable系列](./doc/v_drawable.md)
    * [TextView系列](./doc/v_textview.md)
    * [EditText系列](./doc/v_textview.md)
    * [ImageView系列](./doc/v_imageview.md)
	    * 这部分应该基于Fresco的SimpleDraweeView构建，如果你信任fresco的话
		* PhotoView
		* Gif
		* webP
		* 圆角，圆形
    * [ProgressView系列](./doc/README-ayo.md)
    * [View切换系列](./doc/README-ayo.md)
    * [ListView手册和源码分析](./doc/README-ayo.md)
        * 普通
        * 上下拉及定制
        * 滑动删除和动画
        * sticky或者pinned
        * Item Type
        * drag
    * [RecyclerView和ListView](./doc/README-ayo.md)
	    * 性能，这就不知道怎么弄了
	    * 转屏时是否自动加载
		* scrollToPostion(), scrollTo()
    * [DrawerLayout](./doc/README-ayo.md)
    * [关于ActionBar](./doc/README-ayo.md)
    * [表单系列](./doc/README-ayo.md)
        * 输入框
        * 下拉框：原地，底部，中间
        * WheelView
        * 各种picker
        * 单选
        * 复选
        * Lable + Input 组合
        * 校验
    * [布局系列](./doc/README-ayo.md)
        * LinearLayout，Relativelayout，FrameLayout
        * AutoLayout
        * PercentageLayout
        * FlowLayout
        * WaterFallLayout
        * DrawerLayout
        * SwipeLayout
        * SwipeBackLayout
        * ScrollView
        * SwipeRefreshLayout
        * PullLayout
	* 模板：
		* 模板基础框架：
			* AyoActivity框架，基于AppCompatActivity，原生版，需声明
				* AyoActivity
				* AyoSwipeBackActivity：自带滑动返回
					* FragmentContainerActivity：只能放一个Fragment的模板Activity
				* AyoFragment
			* Attacher框架，基于事先声明的Activity，免声明
				* AyoActivityAttacher
				* AyoSwipeBackActivityAttacher：继承SwipeBackActivityAttacherr
					* FragmentContainerActivityAttacher
				* AyoFragment：支持获取Attacher实例，如果fragment不是处于attacher下，会抛出异常
			* 每个应用应该有一套自己的BaseActivity，参考ayoweibo的ui.base
		* 列表：
			* ListView的列表模板：BaseListViewFragment，过时，基于PullToRefresh
			* GridView的列表模板：BaseGridViewFragment，过时，基于PullToRefresh
			* RecylerView的模板：AyoRecyclerViewFragment，基于ultra pullrefresh，上拉加载效果需完善
		* 其他：
			* PageGroupView：主页框架，适合主页展示N个Fragment的形式
			* AyoWebViewFragment：带一个进度条，setJsInfomation回调支持js调用java
		
			
####
* UIFramework：弹框
    * [原生Dialog](./doc/n_dialog_origin.md)
    * [Dialog：Alert系列](./doc/README-ayo.md)
    * [WheelPicker：列表选择](./doc/README-ayo.md)
    * [DatePicker：日期选择](./doc/README-ayo.md)
    * [ActionSheet](./doc/README-ayo.md)
    * [Popup](./doc/README-ayo.md)
    * [Toast](./doc/README-ayo.md)
    * [Snackbar](./doc/README-ayo.md)
    * [Headup](./doc/README-ayo.md)
    * [Notification](./doc/README-ayo.md)
    * [声音，LED，震动，亮屏](./doc/README-ayo.md)
    * [其他](./doc/README-ayo.md)

####
* UIFramework：动画
	* Tween动画
		* Activity切换动画
		* Fragment切换动画
		* Dialog出入动画
		* Popup出入动画
		* Rotate3D
		* 动画库，工具类
    * [属性动画](./doc/README-ayo.md)
		* 用法
		* 封装：daimajia
		* nineold的兼容，改进，怎么去掉对nineold的依赖
    * [缓动函数--daimajia ease](./doc/README-ayo.md)
    * [spring rebound](./doc/README-ayo.md)
    * [Transition](./doc/README-ayo.md)
    * [布局](./doc/README-ayo.md)
    * [path动画]
	* Scroll，滑动，手势

####
* UIFramework：绘图
    * [OpenGL基础](./doc/README-ayo.md)
    * [onDraw里都能干什么](./doc/README-ayo.md)

####
* UIFramework：自定义控件
    * [控件增强](./doc/README-ayo.md)
    * [控件组合](./doc/README-ayo.md)
    * [自定义布局](./doc/README-ayo.md)

####
* UIFramework：onTouch详解
    * [onTouch基础](./doc/README-ayo.md)
    * [手势应用](./doc/README-ayo.md)
    * [ScrollView里的onTouch](./doc/README-ayo.md)
    * [ViewPager里的onTouch](./doc/README-ayo.md)
    * [常见冲突](./doc/README-ayo.md)
    * [应用场景](./doc/README-ayo.md)

####
* 安卓res详解
    * [res下几个目录到底怎么回事](./doc/README-2016.md)
    * [values目录](./doc/README-2016.md)
    * [主题和style](./doc/README-2016.md)
    * [适配问题](./doc/README-2016.md)
    * [资源文件切换问题：换肤](./doc/README-2016.md)

####
* 单元测试
    * [单元测试怎么写](./doc/README-2016.md)

####
* [常见问题和代码段](./doc/README-issue.md)
    * [ScrollView嵌套ViewPager冲突](./doc/README-issue.md)
    * [ScrollView嵌套ListView或GridView](./doc/README-issue.md)
    * [小键盘管理](./doc/README-issue.md)
    * [三星手机拍照问题](./doc/README-issue.md)
    * [魅族的UI适配](./doc/README-issue.md)
    * [windowIsTranlusent问题](./doc/README-issue.md)
    * [windowIsTranslusent为true导致Activity无法横竖切换](./doc/README-issue.md)
	* [Java单例模式]

####
* 基于git的work flow
    * [git教程](./doc/README-2016.md)

####
* 打包编译
    * 从gradle说起
    * 库管理，上传module的jcenter
    * 多渠道打包
    * [热补丁]

####
* 其他第三方常用库介绍
    * 统计：友盟统计
    * 更新：友盟自动更新
    * 反馈：友盟反馈
    * 第三方登录
    * 第三方分享
    * 七牛：图片云服务
    * 图片选择器
    * 自定义相机
    * 视频播放：第三方平台依赖，优酷
    * 视频播放：第三方平台依赖，乐视
    * 视频播放：视频自己管理
    * 录像
    * 音频播放器
    * 自定义app内置浏览器
    * 支付宝
