package mangomilk.marumaruviewer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private String htmlPageUrl = "http://www.daum.net/";
    private ImageView textviewHtmlDocument;
    private String htmlContentInStringFormat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textviewHtmlDocument = (ImageView)findViewById(R.id.textView);
        //textviewHtmlDocument.setMovementMethod(new ScrollingMovementMethod());

        Button htmlTitleButton = (Button)findViewById(R.id.button);
        htmlTitleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JsoupAsyncTask jsoupAsyncTask = new JsoupAsyncTask();
                jsoupAsyncTask.execute();
            }
        });
    }

    private class JsoupAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {

                Document doc = Jsoup.connect("http://wasabisyrup.com/archives/412625").get();
                Elements links = doc.select("#lz-lazyloaded");
                String imgSrc = links.attr("src");
                InputStream input = new java.net.URL("http://wasabisyrup.com/" + imgSrc).openStream();
                Bitmap bitmap = BitmapFactory.decodeStream(input);
                textviewHtmlDocument.setImageBitmap(bitmap);
                /*Document document = Jsoup.connect(url).get();
                Elements img = document.select("div.col-sm-12.A4DZP4C3.well.leaderboard-A4DZP4C3.genericContainer.js-A4DZP4C3.js-leaderboard-A4DZP4C3");
                // Locate the src attribute
                String imgSrc = img.attr("src");
                // Download image from URL
                InputStream input = new java.net.URL(imgSrc).openStream();
                // Decode Bitmap
                BitmapFactory
                bitmap = BitmapFactory.decodeStream(input);*/

                /*for (Element link : links) {
                    htmlContentInStringFormat += (link.attr("abs:href")
                            + "("+link.text().trim() + ")\n");
                }*/

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            //textviewHtmlDocument.setText(htmlContentInStringFormat);
        }
    }
}
