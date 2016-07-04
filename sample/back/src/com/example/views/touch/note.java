package com.example.views.touch;

public class note {
/*
触摸事件传递机制：
1、onInterceptTouchEvent：从最外层开始拦截，即父控件有拦截权
2、onTouchEvent：从最低层开始处理，返回false表示不处理，则交给上一层父控件处理

大体流程是：
从dispatchTouchEvent开始走，对于View，基本上就是调用onTouchEvent，而对于ViewGroup，会在dispatch里
先检查allowIntercept标志位和onInterceptTouchEvent方法，基本思路也就是如果拦截了，就调用当前ViewGroup的
onTouchEvent，否则就向下传递。其中，allowIntercept就是由requestDisallowInterceptTouchEvent设置的，对于
这个函数，实际上是子控件告诉父控件的，所以一般由子控件调用，并且这个标志位会从调用的View开始，向上一直传递，
即将上面所有的父控件都设置为不拦截状态。

这条路径上涉及到的View，应该是由dispatchTouchEvent决定，即没摸到的控件，也不会在这条路径上

要使用：getParent().requestDisallowInterceptTouchEvent(true);
这个的意思是一个View告诉自己的父控件：这个消息我感兴趣，你不要拦截
前提是：父控件不是直接在onInterceptTouchEven里返回了true，即没有直接无条件拦截掉，因为
一旦拦截了，子控件根本调用不到onTouchEvent和onInterceptTouchEven，也就无法告诉父控件getParent().requestDisallowInterceptTouchEvent(true);

其实这不重要，主要是当子控件的滑动事件处理到一定程序后，得告诉父控件，我对滑动不感兴趣了，你可以接手了，此时父控件的拦截机制就生效，

这里的一个问题是：
理论上ViewGroup不应该拦截滑动事件，而是优先让子控件处理，但对于PhotoView这样的控件，如果你只写死了，永远消费掉touch事件，则
拖动边缘时，其实是想激活父ViewPager的滑动，就做不到了，所以还不能写死，总该知道自己在什么时候不需要消费滑动了，
然后让出touch权

注意：通过requestDisallowInterceptTouchEvent只能告诉父控件：你别拦截，或者你按照你的意思去拦截吧，即按照onIntercept来，所以你没法
告诉父控件，你一定要拦截啊，所以即使你调用了这个函数，父控件也可能没有拦截，这个表达的意思只是，允不允许父控件按照onINtercept的意思来，
或者直接别拦截，就是没法让父控件直接拦截。

一切问题的根源就是：如果父和子都对touch感兴趣，应该谁处理？
1、父直接拦截，即onIntercept返回true，则父总是拦截，没法禁止
2、父直接不拦截，即onIntercept返回false，则父总是拦截，子总是优先onTouchEvent()
3、至此，requestDisallowInterceptTouchEvent都不起作用

所以父最好是按照一定条件，决定intercept与否

到这里未知，还看不出requestDisallowInterceptTouchEvent有什么用，父必须放行才能给子机会调用这个函数，
但如果子在down中不返回false，父就拿不到down事件，还是没法处理，问题就在于：对于ViewPager+可横向滑动的
子控件组合，子是怎么把滑动权让给父的，转了一圈又回来了，当初就是因为这个问题才研究touch事件的
所以：
问题1：可横向滑动的子控件滑动到右边缘时，再滑动就得让给ViewPager，怎么让的
问题2：可纵向滑动的子控件接收到横向滑动时，其实已经消耗了down事件，再怎么发现时横向时传给父，让父横向滑动？

down的时候还不知道是往哪个方向滑动，而是否让出滑动全，基本就取决于往哪个方向滑动，怎么解决？

 */
}
