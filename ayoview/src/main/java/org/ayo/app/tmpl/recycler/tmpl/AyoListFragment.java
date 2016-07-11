package org.ayo.app.tmpl.recycler.tmpl;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.ayo.app.LocalDisplay;
import org.ayo.app.tmpl.base.Condition;
import org.ayo.app.tmpl.base.ErrorReason;
import org.ayo.app.tmpl.base.StatusUIMgmr;
import org.ayo.app.tmpl.pagegroup.ISubPage;
import org.ayo.app.tmpl.recycler.adapter.AyoItemTemplate2;
import org.ayo.app.tmpl.recycler.adapter.AyoRecyclerAdapter;
import org.ayo.app.tmpl.recycler.adapter.AyoSoloAdapter;
import org.ayo.view.recycler.XRecyclerView;

import java.util.Collection;
import java.util.List;

import genius.android.view.R;

/**
 */
public abstract class AyoListFragment<T> extends Fragment implements ISubPage {

    public static final int RANGE_FROM_CACHE_TO_REFRESH = 300;

    private View root = null;
    protected XRecyclerView mXRecyclerView;
    protected AyoRecyclerAdapter<T> mAdapter;
    protected List<T> list;

    private boolean isTheFirstPage = true;
    private boolean isFirstCome = true;
    private boolean refreshEveryTimeUserSeeMe = false; //是否每次变成可见时，都刷新数据
    private String cacheKey = "";

    public AyoListFragment isFirstPage(boolean isTheFirstPage){
        this.isTheFirstPage = isTheFirstPage;
        return this;
    }

    public AyoListFragment refreshEveryTimeUserSeeMe(boolean refresh){
        this.refreshEveryTimeUserSeeMe = refresh;
        return this;
    }

    @Override
    public void setIsTheFirstPage(boolean isTheFirstPage) {
        this.isTheFirstPage = isTheFirstPage;
    }

    public AyoListFragment isTheFirstPage(boolean isTheFirstPage){
        this.isTheFirstPage = isTheFirstPage;
        return this;
    }

    public AyoListFragment cacheKey(String key){
        this.cacheKey = key;
        return this;
    }

    public String cacheKey(){
        return this.cacheKey;
    }

    protected abstract void onViewCreated(View root, XRecyclerView mXRecyclerView); //, FrameLayout container_top, FrameLayout container_bottom, FrameLayout container_cover);
    protected abstract void loadCache();
    protected abstract void loadData(Condition condition);
    protected abstract List<AyoItemTemplate2> getTemplate();
    public abstract Condition initCondition();

    protected AyoRecyclerAdapter newAdapter(){
//        List<AyoItemTemplate2> templates = new ArrayList<>();
//        templates.add(new OneSmallCoverTemplate(getActivity()));
//        templates.add(new OneBigCoverTemplate(getActivity()));
//        templates.add(new TrippleSmallCoverTemplate(getActivity()));
//        templates.add(new VedioTemplate(getActivity()));
//        templates.add(new TopADTemplate(getActivity()));
        AyoSoloAdapter ayoAdapter = new AyoSoloAdapter(getActivity(), getTemplate());
        return ayoAdapter;
    }

    //状态通知
    public void notifyError(boolean isUIChanged, int reason){}
    public void notifyNotAnyMore(){}
    //状态切换
    /**
     * 应该返回4个布局，按顺序是：loading，数据空，本地发生错误，服务器发生错误
     * 如果返回null，必须null，则使用默认样式
     * @return
     */
    public int[] getStatusLayoutId(){
        return null;
    }
    public StatusUIMgmr.OnStatusViewAddedCallback getStatusCallback(){
        return null;
    }


    protected void onRefresh(Condition cond){
        loadData(cond);
    }
    protected void onLoadMore(Condition cond){
        loadData(cond);
    }
    /**
     * 可以在这里跟XRecclerView添加header，滚动监听等
     * @param root
     * @param mXRecyclerView
     */
    protected void onCreateViewFinished(View root, XRecyclerView mXRecyclerView){
//        FrameLayout container_top = (FrameLayout) findViewById(R.id.container_top);
//        FrameLayout container_bottom = (FrameLayout) findViewById(R.id.container_bottom);
//        FrameLayout container_cover = (FrameLayout) findViewById(R.id.container_cover);
        onViewCreated(root, mXRecyclerView); //, container_top, container_bottom, container_cover);
        if(isTheFirstPage){
            firstLoad();
        }
    }


    //数据为空时，第一次进行数据加载
    private void firstLoad(){
        loadCache();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                autoRefresh();
            }
        }, RANGE_FROM_CACHE_TO_REFRESH);
    }

    @Override
    public void onPageVisibleChange(boolean isVisible) {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            if(isTheFirstPage){
                if(isFirstCome){
                    //对于首页来说，初次数据在onCreate里进行
                }else{
                    if(refreshEveryTimeUserSeeMe){
                        autoRefresh();
                    }
                }
            }else{
                if(isFirstCome){
                   firstLoad();
                }else{
                    //非第一次进来，就不用主动加载数据了
                    //也可以只调用autoRefresh()
                    if(refreshEveryTimeUserSeeMe){
                        autoRefresh();
                    }
                }
            }

            //无论如何，在第一次可见之后，isFirstCome都成false了
            isFirstCome = false;
        }else{
            //不可见了
        }
    }

    //样式和加载
    protected RecyclerView.LayoutManager getLayoutManager(){
        return new LinearLayoutManager(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LocalDisplay.init(getActivity());
        root = View.inflate(getActivity(), R.layout.ayo_tmpl_frag_recycler_x, null);
        mXRecyclerView = (XRecyclerView) root.findViewById(R.id.ptr_frame);

        mHandler = new Handler();
        initRecyclerView();
        initXRecyclerView();
        initStatusManager();
        condition = initCondition();

        onCreateViewFinished(root, mXRecyclerView);
        return root;
    }

    protected <T extends View> View findViewById(int id){
        return root.findViewById(id);
    }

    protected List<T> getList(){
        return list;
    }

    private void initRecyclerView() {
        mXRecyclerView = (XRecyclerView) root.findViewById(R.id.ptr_frame);
        //使RecyclerView保持固定的大小,这样会提高RecyclerView的性能
        mXRecyclerView.setHasFixedSize(true);
        //设置LayoutManager
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        mXRecyclerView.setLayoutManager(layoutManager);

        //设置Adapter
        mAdapter = newAdapter();
        mXRecyclerView.setAdapter(mAdapter);

        //adapter刷新列表的方法
        /*
        public final void notifyDataSetChanged()
        public final void notifyItemChanged(int position)
        public final void notifyItemRangeChanged(int positionStart, int itemCount)
        public final void notifyItemInserted(int position)
        public final void notifyItemMoved(int fromPosition, int toPosition)
        public final void notifyItemRangeInserted(int positionStart, int itemCount)
        public final void notifyItemRemoved(int position)
        public final void notifyItemRangeRemoved(int positionStart, int itemCount)
         */
    }

    private void onRefreshX(Condition c){ onRefresh(c); }
    private void onLoadMoreX(Condition c){ onLoadMore(c);}

    private void initXRecyclerView() {

        mXRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                isLoadMore = false;
                condition.onPullDown();
                onRefreshX(condition);
            }

            @Override
            public void onLoadMore() {
                isLoadMore = true;
                condition.onPullUp();
                Log.i("dd0", "onLoadMore--raw");
                onLoadMoreX(condition);
            }
        });
    }


    public void stopRefreshOrLoadMore(){
        try{
            if(isLoadMore){
                mXRecyclerView.loadMoreComplete();
            }else{
                mXRecyclerView.refreshComplete();
            }

        }catch (Exception e){

        }
    }

    ///----------状态相关
    protected boolean isLoadMore = false;
    private StatusUIMgmr statusMgmr;
    protected Handler mHandler;

    protected void initStatusManager(){
        ///状态切换
        int[] lays = getStatusLayoutId();
        if(lays == null){
            lays = new int[]{R.layout.genius_view_loading, R.layout.genius_view_empty, R.layout.genius_view_error_local, R.layout.genius_view_error_server};
        }else{
            if(lays.length != 4){
                throw new RuntimeException("必须返回4个布局，顺序是loading，empty，local error, serve error");
            }
        }

        statusMgmr = StatusUIMgmr.attach(mXRecyclerView, getStatusCallback() == null ? callback: getStatusCallback());
        statusMgmr.setLoadingLayout(lays[0]);
        statusMgmr.setEmptyLayout(lays[1]);
        statusMgmr.setErrorLayout(lays[2], lays[3]);

    }

    private StatusUIMgmr.OnStatusViewAddedCallback callback = new StatusUIMgmr.OnStatusViewAddedCallback() {

        @Override
        public void onLoadingViewAdded(View loadingView) {

        }

        public void onErrorViewAdded(View errorOfLocalView, View errorOfServerView) {
        }

        @Override
        public void onEmptyViewAdded(View emptyView) {

        }
    };

    public void onLoadFinish(){
        if(mHandler == null) mHandler = new Handler(Looper.getMainLooper());
        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                stopRefreshOrLoadMore();
            }
        }, 200);
    }




    /**
     * 错误提示：
     * 原则是：只要界面上有数据，就不会切换页面，
     * 但是如果页面上本来就没数据，那就得按照错误的分类来，
     * @param reason
     * @param forceChageUI  是否不管当前页面是什么，都强制切换到错误页，一般应该是false
     */
    public void onLoadFail(int reason, boolean forceChageUI){
        onLoadFinish();
        if(!(list == null || list.size() == 0 ) && !forceChageUI){
            //界面不是空，也不强制切换UI，则什么都不干
            //Toaster.toastLong(errorInfo);
            notifyError(false, reason);
        }else{
            if(reason == ErrorReason.LOCAL){
                statusMgmr.showErrorOfLocal();
            }else if(reason == ErrorReason.SERVER){
                statusMgmr.showErrorOfServer();
            }else{
                statusMgmr.showErrorOfServer();
            }
            notifyError(true, reason);
        }
    }

    public void onLoading(){
        statusMgmr.showLoading();
    }


    public void onLoadOk(List<T> data){
        onLoadFinish();
        if(isLoadMore && isEmpty(data)){
            ///没有更多页了，并且这一页也是空
            //Toaster.toastLong("没有下一页了");
            notifyNotAnyMore();
            return;
        }

        if(isLoadMore){
            this.list = (List<T>) combine(this.list, data);
        }else{
            this.list = data;
        }

        if(isEmpty(this.list)){
            mAdapter.notifyDataSetChanged(null);
            statusMgmr.showEmpty();
        }else{
            statusMgmr.clearStatus();

            if(isLoadMore){
                mAdapter.notifyDataSetChanged(list);
            }else{
                mAdapter = newAdapter();
                mXRecyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged(this.list);
            }
        }

    }

    public void autoRefresh(){
        mXRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mXRecyclerView.setRefresh();
            }
        }, 100);
    }

    private Condition condition;



    public Condition getCondition(){
        return this.condition;
    }

    private boolean isEmpty(List<T> data) {
        return data == null || data.size() == 0;
    }

    private <T> Collection<T> combine(Collection<T> c1,
                                      Collection<T> c2) {
        if (c1 == null && c2 == null)
            return null;
        if (c1 == null)
            return c2;
        if (c2 == null)
            return c1;
        c1.addAll(c2);
        return c1;
    }

    public static class AyoCondition extends Condition{

        public int page;
        public int pageStart = 0;

        public AyoCondition(int pageStart){
            this.pageStart = pageStart;
            page = pageStart;
        }

        @Override
        public void onPullDown() {
            page = pageStart;
        }

        @Override
        public void onPullUp() {
            page++;
        }

        @Override
        public void reset() {
            page = pageStart;
        }
    }
}
