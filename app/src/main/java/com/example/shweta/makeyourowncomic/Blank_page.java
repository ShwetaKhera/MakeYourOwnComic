package com.example.shweta.makeyourowncomic;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;
import static com.example.shweta.makeyourowncomic.Add_Text.textView;
import static com.example.shweta.makeyourowncomic.Characters.Tag_Charac;
import static com.example.shweta.makeyourowncomic.Dialogue.Tag_Dialog;
import static com.example.shweta.makeyourowncomic.Image_Adapters.Batman_Adapter.mThumbIds_Batman;
import static com.example.shweta.makeyourowncomic.Image_Adapters.Circle_Adapter.convertDpToPixels;
import static com.example.shweta.makeyourowncomic.Image_Adapters.Circle_Adapter.mThumbIds_Circle;
import static com.example.shweta.makeyourowncomic.Image_Adapters.Cloud_Adapter.mThumbIds_Cloud;
import static com.example.shweta.makeyourowncomic.Image_Adapters.Homer_Adapter.mThumbIds_Homer;
import static com.example.shweta.makeyourowncomic.Image_Adapters.Rectangle_Adapter.mThumbIds_Rectangle;
import static com.example.shweta.makeyourowncomic.Image_Adapters.Short_Adapter.mThumbIds_Short;

/**
 * Created by shweta on 10-06-2017.
 */

public class Blank_page extends AppCompatActivity{

    RoundedImageView add_dialog, add_charac;
    static Button background_btn, add_text_button, edit_text_button;
    ImageView h_button, w_button, r_button, b_button;
    ImageView imageView;
    static SeekBar h_seekbar, w_seekbar, r_seekbar, b_seekbar;
    static RelativeLayout ABP, DrawingArea;
    ImageButton final_done;
    static public View VIEW = null, Change_View;
    String frag_open = "NULL",path;
    float x, y;
    static int POS;
    int i = 1;

    Bitmap source, rotatedImage;
    float angle = 0;
    static Matrix matrix;

    Bitmap mbitmap;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blank_page);

        textView = null;


        add_charac = (RoundedImageView) findViewById(R.id.add_charac);
        add_dialog = (RoundedImageView) findViewById(R.id.add_dialog);
        add_text_button = (Button) findViewById(R.id.add_text_button);
        edit_text_button = (Button) findViewById(R.id.edit_text_button);

        h_button = (ImageView) findViewById(R.id.height_button);
        w_button = (ImageView) findViewById(R.id.width_button);
        r_button = (ImageView) findViewById(R.id.rotation_button);
        b_button = (ImageView) findViewById(R.id.brightness_button);

        final_done = (ImageButton) findViewById(R.id.final_done);

        h_seekbar = (SeekBar) findViewById(R.id.height_seekbar);
        h_seekbar.setMax(convertDpToPixels(150, getApplicationContext()));
        if (imageView == null) {
            h_seekbar.setProgress(convertDpToPixels(50, getApplicationContext()));
        } else {
            h_seekbar.setProgress(imageView.getHeight());
        }

        w_seekbar = (SeekBar) findViewById(R.id.width_seekbar);
        w_seekbar.setMax(convertDpToPixels(150, getApplicationContext()));
        if (imageView == null) {
            w_seekbar.setProgress(convertDpToPixels(50, getApplicationContext()));
        } else {
            w_seekbar.setProgress(imageView.getWidth());
        }

        r_seekbar = (SeekBar) findViewById(R.id.rotation_seekbar);
        r_seekbar.setMax(360);
        r_seekbar.setProgress(0);

        b_seekbar = (SeekBar) findViewById(R.id.brightness_seekbar);
        b_seekbar.setProgress(125);
        b_seekbar.setMax(255);


        background_btn = (Button) findViewById(R.id.background_btn);

        ABP = (RelativeLayout) findViewById(R.id.activity_blank_page);
        DrawingArea = (RelativeLayout) findViewById(R.id.DrawingArea);

        DrawingArea.setOnDragListener(new MyDragListener());

        add_charac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectFrag(v);
            }
        });

        add_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectFrag(v);
            }
        });

        ABP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectFrag(v);
                h_button.setVisibility(INVISIBLE);
                w_button.setVisibility(INVISIBLE);
                r_button.setVisibility(INVISIBLE);
                b_button.setVisibility(INVISIBLE);
                h_seekbar.setVisibility(INVISIBLE);
                w_seekbar.setVisibility(INVISIBLE);
                r_seekbar.setVisibility(INVISIBLE);
                b_seekbar.setVisibility(INVISIBLE);
                add_text_button.setVisibility(INVISIBLE);

                h_button.setImageResource(R.drawable.capital_h_before);
                w_button.setImageResource(R.drawable.capital_w_before);
                r_button.setImageResource(R.drawable.capital_r_before);
                b_button.setImageResource(R.drawable.capital_b_before);

            }
        });

        background_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectFrag(v);
            }
        });

        h_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                h_button.setImageResource(R.drawable.capital_h_after);
                w_button.setImageResource(R.drawable.capital_w_before);
                r_button.setImageResource(R.drawable.capital_r_before);
                b_button.setImageResource(R.drawable.capital_b_before);
                h_seekbar.setVisibility(VISIBLE);
                w_seekbar.setVisibility(INVISIBLE);
                r_seekbar.setVisibility(INVISIBLE);
                b_seekbar.setVisibility(INVISIBLE);
            }
        });
        w_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                h_button.setImageResource(R.drawable.capital_h_before);
                w_button.setImageResource(R.drawable.capital_w_after);
                r_button.setImageResource(R.drawable.capital_r_before);
                b_button.setImageResource(R.drawable.capital_b_before);

                w_seekbar.setVisibility(VISIBLE);
                h_seekbar.setVisibility(INVISIBLE);
                r_seekbar.setVisibility(INVISIBLE);
                b_seekbar.setVisibility(INVISIBLE);
            }
        });
        r_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                h_button.setImageResource(R.drawable.capital_h_before);
                w_button.setImageResource(R.drawable.capital_w_before);
                r_button.setImageResource(R.drawable.capital_r_after);
                b_button.setImageResource(R.drawable.capital_b_before);

                r_seekbar.setVisibility(VISIBLE);
                h_seekbar.setVisibility(INVISIBLE);
                w_seekbar.setVisibility(INVISIBLE);
                b_seekbar.setVisibility(INVISIBLE);
            }
        });
        b_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                h_button.setImageResource(R.drawable.capital_h_before);
                w_button.setImageResource(R.drawable.capital_w_before);
                r_button.setImageResource(R.drawable.capital_r_before);
                b_button.setImageResource(R.drawable.capital_b_after);

                b_seekbar.setVisibility(VISIBLE);
                h_seekbar.setVisibility(INVISIBLE);
                w_seekbar.setVisibility(INVISIBLE);
                r_seekbar.setVisibility(INVISIBLE);
            }
        });


        h_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                Change_View.getLayoutParams().height = progress;
                Change_View.requestLayout();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        w_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                Change_View.getLayoutParams().width = progress;
                Change_View.requestLayout();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        add_text_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.fragment_place, new Add_Text()).commit();
                add_text_button.setVisibility(INVISIBLE);
                b_seekbar.setVisibility(INVISIBLE);
                h_seekbar.setVisibility(INVISIBLE);
                w_seekbar.setVisibility(INVISIBLE);
                r_seekbar.setVisibility(INVISIBLE);
            }
        });

        edit_text_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_text_button.setVisibility(INVISIBLE);

                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.fragment_place, new Add_Text()).commit();
                add_text_button.setVisibility(INVISIBLE);
                b_seekbar.setVisibility(INVISIBLE);
                h_seekbar.setVisibility(INVISIBLE);
                w_seekbar.setVisibility(INVISIBLE);
                r_seekbar.setVisibility(INVISIBLE);
            }
        });

        final_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final_done.setVisibility(INVISIBLE);
                background_btn.setVisibility(INVISIBLE);
                b_button.setVisibility(INVISIBLE);
                h_button.setVisibility(INVISIBLE);
                w_button.setVisibility(INVISIBLE);
                r_button.setVisibility(INVISIBLE);
                b_seekbar.setVisibility(INVISIBLE);
                h_seekbar.setVisibility(INVISIBLE);
                w_seekbar.setVisibility(INVISIBLE);
                r_seekbar.setVisibility(INVISIBLE);
                add_dialog.setVisibility(INVISIBLE);
                add_charac.setVisibility(INVISIBLE);
                add_text_button.setVisibility(INVISIBLE);

                screenShot(v);

                final_done.setVisibility(VISIBLE);
                background_btn.setVisibility(VISIBLE);

                add_dialog.setVisibility(VISIBLE);
                add_charac.setVisibility(VISIBLE);


            }
        });

    }

    public void screenShot(View view) {
        mbitmap = getBitmapOFRootView(view);
        createImage(mbitmap);
    }

    public Bitmap getBitmapOFRootView(View v) {
        View rootview = v.getRootView();
        rootview.setDrawingCacheEnabled(true);
        Bitmap bitmap1 = rootview.getDrawingCache();
        return bitmap1;
    }

    public void createImage(Bitmap bmp) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 40, bytes);
       // File file = new File(getApplicationContext().getFilesDir() + "capturedscreenandroid.jpg");


       // bmp.compress(Bitmap.CompressFormat.JPEG, 40, bytes);
        File f = new File(Environment.getExternalStorageDirectory()
                + File.separator + "test.jpg");

        try {

            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            fo.close();
            MediaScannerConnection.scanFile(this, new String[]{f.getPath()}, new String[]{"image/jpeg"}, null);
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://" + Environment.getExternalStorageDirectory())));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void selectFrag(View view) {
        Fragment fr = null;

        if (view == findViewById(R.id.add_dialog)) {
            frag_open = "D";
            fr = new Dialogue();

        } else if (view == findViewById(R.id.add_charac)) {

            frag_open = "C";
            fr = new Characters();
        } else if (view == findViewById(R.id.background_btn)) {

            fr = new Background();
        } else {

            fr = new Plain();
        }

        FragmentManager fm = getFragmentManager();

        FragmentTransaction fragmentTransaction = fm.beginTransaction();

        fragmentTransaction.replace(R.id.fragment_place, fr);

        fragmentTransaction.commit();

    }

    class MyDragListener implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {


            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:

                    break;
                case DragEvent.ACTION_DRAG_EXITED:

                    break;
                case DragEvent.ACTION_DROP:

                    x = event.getX() - 10;
                    y = event.getY() - 10;

                    break;
                case DragEvent.ACTION_DRAG_ENDED:

                    if (VIEW == null) {
                        create_new_image();
                        set_tag();
                    } else {
                        SET();
                    }

                    break;
                default:
                    break;
            }
            return true;
        }
    }

    void create_new_image() {
        imageView = new ImageView(getApplicationContext());
        imageView.setLayoutParams(new ViewGroup.LayoutParams(convertDpToPixels(50, getApplicationContext()), convertDpToPixels(50, getApplicationContext())));
        imageView.setX(x);
        imageView.setY(y);
        if (frag_open.equals("D")) {
            imageView.setTag("D_" + i);
        } else if (frag_open.equals("C")) {
            imageView.setTag("C_" + i);
        }
        i++;
        imageView.setImageResource(Image_Resource(set_tag()));
        DrawingArea.addView(imageView);

        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                ClipData.Item item = new ClipData.Item((CharSequence) view.getTag());

                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
                ClipData data = new ClipData(view.getTag().toString(), mimeTypes, item);
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);

                view.startDrag(data, shadowBuilder, view, 0);

                view.setVisibility(INVISIBLE);
                VIEW = view;

                return false;
            }
        });

        imageView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                h_button.setVisibility(VISIBLE);
                w_button.setVisibility(VISIBLE);
                r_button.setVisibility(VISIBLE);
                b_button.setVisibility(VISIBLE);
                Change_View = v;


                final String tag = Change_View.getTag().toString();

                r_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if (imageView.getTag() == tag) {
                            angle = progress;
                            imageView.buildDrawingCache();
                            source = imageView.getDrawingCache();
                            rotatedImage = rotateImage(source, angle);
                            imageView.setImageMatrix(null);
                            imageView.setImageBitmap(rotatedImage);
                        }
                    }

                    public void onStartTrackingTouch(SeekBar arg0) {
                    }

                    public void onStopTrackingTouch(SeekBar arg0) {
                    }
                });
                b_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                    @Override
                    public void onStopTrackingTouch(SeekBar arg0) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar arg0) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onProgressChanged(SeekBar arg0, int progress, boolean arg2) {

                        // TODO Auto-generated method stub

                        if (imageView.getTag() == tag) {
                          /*  brightness = progress;
                            Bitmap finalBitmap ;

                             if(rotatedImage == null)
                                 finalBitmap = Bitmap.createScaledBitmap(imageView.getDrawingCache(), imageView.getWidth(), imageView.getHeight(), false);
                            else
                             finalBitmap = rotatedImage;

                            Bitmap bitmap = doBrightness(finalBitmap, brightness);
                            imageView.setImageBitmap(bitmap);*/
                            imageView.setColorFilter(setBrightness(progress));
                        }
                    }
                });

                if (imageView.getTag().toString().contains("D"))
                    add_text_button.setVisibility(VISIBLE);

                if(event.getPointerCount()==2){
                    x = event.getX();
                    y = event.getY();

                   // imageView.setLayoutParams(new ViewGroup.LayoutParams((int)x,(int)y));
                 //   VIEW = Change_View;
                  //  SET();
                }
                return false;

            }
        });

    }

    void SET() {
        VIEW.setX(x);
        VIEW.setY(y);
        VIEW.setVisibility(VISIBLE);
        if (textView != null) {
            textView.findViewWithTag(VIEW.getTag()).setX(x);
            textView.findViewWithTag(VIEW.getTag()).setY(y);
        }
        VIEW = null;
    }

    int Image_Resource(String tag) {
        switch (tag) {
            case "CIRCLE":
                return mThumbIds_Circle[POS];
            case "CLOUD":
                return mThumbIds_Cloud[POS];
            case "RECTANGLE":
                return mThumbIds_Rectangle[POS];
            case "SHORT":
                return mThumbIds_Short[POS];


            case "HOMER":
                return mThumbIds_Homer[POS];
            case "BATMAN":
                return mThumbIds_Batman[POS];
        }
        return 0;
    }

    String set_tag() {
        switch (frag_open) {
            case "D":
                return Tag_Dialog;
            case "C":
                return Tag_Charac;
            default:
                return null;
        }
    }

    public static Bitmap rotateImage(Bitmap sourceImage, float angle) {
        matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(sourceImage, 0, 0, sourceImage.getWidth(),
                sourceImage.getHeight(), matrix, true);

    }

    public static PorterDuffColorFilter setBrightness(int progress) {
        if (progress >= 100) {
            int value = (int) (progress - 100) * 255 / 100;

            return new PorterDuffColorFilter(Color.argb(value, 255, 255, 255), PorterDuff.Mode.SRC_OVER);

        } else {
            int value = (int) (100 - progress) * 255 / 100;
            return new PorterDuffColorFilter(Color.argb(value, 0, 0, 0), PorterDuff.Mode.SRC_ATOP);


        }
    }
}