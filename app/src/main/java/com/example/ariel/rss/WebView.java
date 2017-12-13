package com.example.ariel.rss;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.widget.ProgressBar;


public class WebView extends AppCompatActivity {

    private android.webkit.WebView webView1;
    private ProgressBar progressBar;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_web_view);
        webView1 = findViewById(R.id.WebVi);
        Bundle bundle = getIntent().getExtras();
        webView1.loadUrl(bundle.getString("url"));
        webView1.getSettings().setJavaScriptEnabled(true);
        webView1.getSettings().setBuiltInZoomControls(false);
        toolbar = findViewById(R.id.WebViewToolBar);
        setSupportActionBar(toolbar);


        progressBar = findViewById(R.id.progressBar);
        webView1.setWebChromeClient(new WebChromeClient() {

            public void onProgressChanged(WebView view, int progress) {
                progressBar.setProgress(0);
                WebView.this.setProgress(progress * 1000);
                progressBar.incrementProgressBy(progress);
                if (progress == 100) {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Bundle bundle = getIntent().getExtras();
        Intent Compartir = new Intent(Intent.ACTION_SEND);
        Compartir.setType("text/plain");
        String Sharebody = "your body here";
        String Sharesub = bundle.getString("url");
        Compartir.putExtra(Intent.EXTRA_SUBJECT,Sharebody);
        Compartir.putExtra(Intent.EXTRA_TEXT,Sharesub);
        startActivity(Intent.createChooser(Compartir,"Compartir con:"));
        return  true;

    }
}

