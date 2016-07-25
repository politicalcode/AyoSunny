package com.ayoview.sample.tmpl_listview;

import android.view.View;

import com.ayoview.sample.tmpl_listview.http.TestHttper;
import com.ayoview.sample.tmpl_listview.http.TestOrder;
import com.ayoview.sample.tmpl_listview.http.TestOrderList;
import com.cowthan.sample.Utils;

import org.ayo.app.tmpl.base.Condition;
import org.ayo.app.tmpl.recycler.adapter.AyoItemTemplate2;
import org.ayo.app.tmpl.recycler.tmpl.AyoListFragment;
import org.ayo.http.callback.BaseHttpCallback;
import org.ayo.http.callback.model.ResponseModel;
import org.ayo.http.utils.HttpProblem;
import org.ayo.view.recycler.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TmplFragment extends AyoListFragment<TmplBean> {

	public static final String CACHE_KEY = "dsfsdf";
	

	@Override
	protected void onViewCreated(View root, XRecyclerView mXRecyclerView) {

	}

	@Override
	protected void loadCache() {

	}

	@Override
	protected void loadData(Condition condition) {
		final TmplCondition cond = (TmplCondition) getCondition();
		BaseHttpCallback<TestOrderList> callback = new BaseHttpCallback<TestOrderList>() {

			@Override
			public void onFinish(boolean isSuccess, HttpProblem problem,
								 ResponseModel resp, TestOrderList t) {
				if(isSuccess){

					//--
					if(t == null){
						onLoadOk(null);
						return;
					}

					//--
					List<TmplBean> list = new ArrayList<TmplBean>();
					for(TestOrder o : t.artlist){
						TmplBean b = new TmplBean();
						b.title = o.title;
						b.cover_url = o.cover_url;
						list.add(b);
					}
					onLoadOk(list);

					//---
//					if(needCache()){
//						if(cond.pageNow == 0 && Lang.isNotEmpty(list)){
//							//是第一页，且有数据，缓存下来
//							Configer.putObject(TmplFragment.CACHE_KEY, list);
//						}
//					}

				}else{
					onLoadFail(Utils.parseErrorType(problem), true);
				}

			}

		};

		TestHttper.getArticle("haha", cond.page, callback, MyHttpResponse.class);
	}

	@Override
	protected List<AyoItemTemplate2> getTemplate() {
		List<AyoItemTemplate2> ts = new ArrayList<>();
		ts.add(new TmplDelegate(getActivity()));
		return ts;
	}

	@Override
	public Condition initCondition() {
		TmplCondition cnd = new TmplCondition(0);
		cnd.reset();
		return cnd;
	}


}
