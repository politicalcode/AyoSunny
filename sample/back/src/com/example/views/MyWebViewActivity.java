package com.example.views;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.webkit.ConsoleMessage;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.TextSize;
import android.webkit.WebSettings.ZoomDensity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MyWebViewActivity extends Activity 
{
	
	private WebView webView;
	private WebSettings webSet;
	
	private Button btn_go;
	private EditText editText;
	
//	protected String DEFAULT_URL = "http://10.0.2.2:8080";
//	protected String DEFAULT_URL = "f ile:///android_asset/webview/test.html";
	protected String DEFAULT_URL = "file:///android_asset/webview/js2java.html";
//	protected String DEFAULT_URL = "file:///android_asset/headmap.html";
	
	private ProgressDialog progDlg;
	
	private Context ctx;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        ctx = this;
        webView = (WebView) findViewById(R.id.webView);
        btn_go = (Button) findViewById(R.id.btn_go);
        editText = (EditText) findViewById(R.id.editText);
        
        regListener();
        initSet();
    }
	private void regListener() {
		btn_go.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String url = editText.getText().toString();
				if("".equals(url)){
					url = DEFAULT_URL;
				}else if(!url.startsWith("http")){
					url = "http://" + url;
				}
				webView.loadUrl(url);
			}
		});
		
	}
	

	private void initSet()
	{
		/**
		 *  WebView的设置选项太多了，所以都抽取到 WebSettings中，并且谷歌
		 *  给了默认的设置，当创建WebView时，就会自动创建 WebSettings对象
		 *
		 *	如果想要对WebView进行设置，就需要使用这个类
		 *
		 *  获取WebSettings对象：webView.getSettings();
		 */
		webSet = webView.getSettings();
		webSet.setSupportZoom(true);//是否支持缩放
		webSet.setDefaultZoom(ZoomDensity.MEDIUM);//设置默认的缩放级别
		webSet.setJavaScriptEnabled(true);//是否支持javaScript
		webSet.setTextSize(TextSize.SMALLER); // 设置文字的大小
		
		
//		webSet.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);//设置布局样式
		
//		webSet.setBuiltInZoomControls(true);//打开自带的缩放按钮
		
		webView.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				System.out.println("onTouch==========");
				return false;
			}
		});//  TestBug
		
		webView.setWebViewClient(webViewClient);
		webView.setWebChromeClient(webChromeClient);

		webView.addJavascriptInterface(new Object(){
			public void callFromJS(String msg){
				showToast(msg);
			}
		}, "heima");
		
	}

	
	///==========================================================//
	private WebViewClient webViewClient = new WebViewClient(){

		/**
		* 这个实现是保证在网页中点击超链接时，如果是本站，则在本WebView打开，
		* 注意，默认的设置是每次点击超链接，都会打开系统浏览器
		* 
		* 这里的作用：
		* 1、对URL的过滤
		* 2、对URL中内容的过滤：先想办法在某个地方加载url的内容，过滤后，
		*    把修改后的内容给webview
		* 3、
		*/
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			System.out.println("url::"+url);
			Uri uri = Uri.parse(url);
			if(uri.getHost().equals("10.0.2.2")){
				view.loadUrl(url);
			} else {
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_VIEW);
				intent.setData(uri);
				startActivity(intent);
			}
			
			//view.loadUrl(url);
			return true;
		}

		/**
		 * 开始加载页面
		 */
		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) 
		{
			if(progDlg==null)
			{
				progDlg = new ProgressDialog(ctx);
				progDlg.setMessage("正在加载，请稍候...");
			}
			progDlg.show();
		}

		/**
		 *  页面加载完毕
		 */
		@Override
		public void onPageFinished(WebView view, String url) 
		{
			progDlg.dismiss();
		}
		
	}; ///==========================================================///
	
	
	/**
	 * 负责和JS相关的东西
	 */
	private WebChromeClient webChromeClient = new WebChromeClient(){
	
		//网页的加载进度
		@Override
		public void onProgressChanged(WebView view, int newProgress) {
			progDlg.setMessage("已经加载 :"+newProgress+" % ,请稍候。。");
		}
	
		
		//javascript调用window.alert()时，触发此方法
		@Override
		public boolean onJsAlert(WebView view, String url, String message,
				final JsResult result) {
			AlertDialog.Builder dlg = new AlertDialog.Builder(ctx);
			dlg.setMessage(message);
			dlg.setTitle("提示");
			// dlg.setTitle(null);
			dlg.setCancelable(false);
			dlg.setPositiveButton(android.R.string.ok,
					new AlertDialog.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							result.confirm();
						}
					});
			dlg.create();
			dlg.show();
			return true;
		}
	
		@Override
		public boolean onJsConfirm(WebView view, String url,
				String message, final JsResult result) {
			AlertDialog.Builder dlg = new AlertDialog.Builder(ctx);
			dlg.setMessage(message);
			dlg.setTitle("提示");
			dlg.setCancelable(true);
			dlg.setPositiveButton(android.R.string.ok,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							result.confirm();
						}
					});
			dlg.setNegativeButton(android.R.string.cancel,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							result.cancel();
						}
					});
			dlg.setOnCancelListener(new DialogInterface.OnCancelListener() {
				public void onCancel(DialogInterface dialog) {
					result.cancel();
				}
			});
			dlg.setOnKeyListener(new DialogInterface.OnKeyListener() {
				// DO NOTHING
				public boolean onKey(DialogInterface dialog, int keyCode,
						KeyEvent event) {
					if (keyCode == KeyEvent.KEYCODE_BACK) {
						result.cancel();
						return false;
					} else
						return true;
				}
			});
			dlg.create();
			dlg.show();
			return true;
		}
	
		@Override
		public boolean onJsPrompt(WebView view, String url, String message,
				String defaultValue, JsPromptResult result) {
			final JsPromptResult res = result;
			AlertDialog.Builder dlg = new AlertDialog.Builder(ctx);
			dlg.setMessage(message);
			final EditText input = new EditText(ctx);
			if (defaultValue != null) {
				// input.setText(defaultValue);
			}
			dlg.setView(input);
			dlg.setCancelable(false);
			dlg.setPositiveButton(android.R.string.ok,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int which) {
							String usertext = input.getText().toString();
							res.confirm(usertext);
						}
					});
			dlg.setNegativeButton(android.R.string.cancel,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int which) {
							res.cancel();
						}
					});
			dlg.create();
			dlg.show();
			return true;
		}
	
		//调用cosole.log()方法时
		@Override
		public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
			// TODO Auto-generated method stub
			return super.onConsoleMessage(consoleMessage);
		}
	};///==========================================================//
	
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()){
			webView.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
//	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getOrder()) {
		case 0:
			if(webView.canGoForward()){
				webView.goForward();
			}else{
				showToast("不能前进了!");
			}
			break;
		case 1:
			webView.reload(); //刷新
			break;
		case 2:
			if(webView.canGoBack()){
				webView.goBack();
			}else{
				showToast("不能后退了!");
			}
			break;
		case 3: // java代码调用javascript代码
			webView.loadUrl("javascript:test();");
			break;
			
		default:
			break;
		}
		return true;
	}

	
	private void showToast(String string) {
		Toast.makeText(this, string, 0).show();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 0, 0, "前进");
		menu.add(0, 0, 1, "刷新");
		menu.add(0, 0, 2, "后退");
		menu.add(0, 0, 3, "测试");
		return true;
	}
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onPrepareOptionsMenu(menu);
	}
	
	@Override
	protected void onResume() {
		webView.loadUrl("http://www.baidu.com/");//要是打开http://www.163u.com/则会跳到别的浏览器，为何？？
		super.onResume();
	}
}