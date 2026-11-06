package vn.git.roosee.mobile;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebChromeClient;
import android.widget.Toast;

public class MainActivity extends Activity {
 private WebView web;
 private static final String HOME = "https://git-96.github.io/GIT-ROOSEE-Mobile/";
 @Override public void onCreate(Bundle state) {
  super.onCreate(state);
  getWindow().getDecorView().setSystemUiVisibility(5894 | 1024 | 512);
  web = new WebView(this);
  web.setBackgroundColor(0xfff0faff);
  web.setVerticalScrollBarEnabled(false);
  web.setHorizontalScrollBarEnabled(false);
  web.setOverScrollMode(View.OVER_SCROLL_NEVER);
  web.getSettings().setJavaScriptEnabled(true);
  web.getSettings().setDomStorageEnabled(true);
  web.getSettings().setAllowFileAccess(false);
  web.getSettings().setAllowContentAccess(false);
  web.setWebChromeClient(new WebChromeClient());
  web.setWebViewClient(new WebViewClient(){
   @Override public boolean shouldOverrideUrlLoading(WebView v, WebResourceRequest r){return route(r.getUrl());}
   @Override public void onReceivedError(WebView v, android.webkit.WebResourceRequest r, android.webkit.WebResourceError e) {
    if(r.isForMainFrame()) Toast.makeText(MainActivity.this,"Không tải được giao diện. Kiểm tra kết nối Internet.",Toast.LENGTH_LONG).show();
   }
  });
  setContentView(web);
  web.loadUrl(HOME);
 }
 private boolean route(Uri uri){
  String host=uri.getHost(); String scheme=uri.getScheme();
  if("https".equals(scheme) && "git-96.github.io".equalsIgnoreCase(host))return false;
  try { startActivity(new Intent(Intent.ACTION_VIEW,uri)); }
  catch(Exception e){Toast.makeText(this,"Không mở được liên kết. Hãy cài Zalo.",Toast.LENGTH_LONG).show();}
  return true;
 }
 @Override public void onBackPressed(){if(web!=null && web.canGoBack())web.goBack();else super.onBackPressed();}
 @Override protected void onDestroy(){if(web!=null)web.destroy();super.onDestroy();}
}