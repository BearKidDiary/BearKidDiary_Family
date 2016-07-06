package bearkid.com.bearkiddiaryfamily.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import bearkid.com.bearkiddiaryfamily.R;
import bearkid.com.bearkiddiaryfamily.utils.ImageLoader;

public class ShowBigImageActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_big_image);
        findViewById(R.id.activity_show_big_image_root)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
        ImageLoader imageLoader = ImageLoader.build(this);
        ImageView imageView = (ImageView) findViewById(R.id.activity_show_big_image_img);
        String uri = getIntent().getExtras().getString("URI");
        imageLoader.bindBitmap(uri, imageView);
    }

    public static void startActivity(Context context, String uri) {
        Intent intent = new Intent(context, ShowBigImageActivity.class);
        intent.putExtra("URI", uri);
        context.startActivity(intent);
    }
}
