package org.meiocode.a1.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import org.meiocode.a1.R;
import org.meiocode.a1.adapter.ViewPagerPlayListNhac;
import org.meiocode.a1.fragment.Fragment_dia_nhac;
import org.meiocode.a1.fragment.Fragment_play_danh_sach_cac_bai;
import org.meiocode.a1.model.BaiHat;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class PlayNhacActivity extends AppCompatActivity {
    Toolbar toolbarplaynhac;
    TextView txtTimesong, txtTotaltimesong;
    SeekBar sktime;
    ImageButton imgplay, imgnext, imgprev, imgrepeat, imgrandom;
    ViewPager viewPagerplaynhac;

    Fragment_dia_nhac fragment_dia_nhac;
    Fragment_play_danh_sach_cac_bai fragment_play_danh_sach_cac_bai;
    MediaPlayer mediaPlayer;

    public static ArrayList<BaiHat> mangbaihat = new ArrayList<>();
    public static ViewPagerPlayListNhac adapternhac;
    boolean repeat = false;
    boolean random = false;
    int currentPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_nhac);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Init();
        GetDataFromIntent();
        EventClick();
    }

    private void EventClick() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (fragment_dia_nhac != null && mangbaihat.size() > 0) {
                    fragment_dia_nhac.Playnhac(mangbaihat.get(0).getHinhBaiHat());
                    handler.removeCallbacks(this);
                } else {
                    handler.postDelayed(this, 300);
                }
            }
        }, 500);

        imgplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    imgplay.setImageResource(R.drawable.iconplaymussic);
                } else if (mediaPlayer != null) {
                    mediaPlayer.start();
                    imgplay.setImageResource(R.drawable.iconpause);
                }
            }
        });

        imgnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Drawable originalBackground = imgnext.getBackground();
                imgnext.setBackgroundColor(Color.GRAY);
                NextSong();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgnext.setBackground(originalBackground);
                    }
                }, 500); // 1500 milliseconds = 1.5 giây
            }
        });

        imgprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Drawable originalBackground = imgprev.getBackground();
                imgprev.setBackgroundColor(Color.GRAY);
                PreviousSong();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgprev.setBackground(originalBackground);
                    }
                }, 500); // 1500 milliseconds = 1.5 giây
            }

        });

        imgrepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (repeat) {
                    imgrepeat.setImageResource(R.drawable.iconrepeat);
                    repeat = false;
                } else {
                    imgrepeat.setImageResource(R.drawable.iconrepeat2);
                    repeat = true;
                }
            }
        });

        imgrandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (random) {
                    imgrandom.setImageResource(R.drawable.iconrandommusic);
                    random = false;
                } else {
                    imgrandom.setImageResource(R.drawable.iconrandom2);
                    random = true;
                }
            }
        });

        sktime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    private void NextSong() {
        if (mangbaihat.size() > 0) {
            mediaPlayer.stop();
            mediaPlayer.reset();
            if (random) {
                Random random = new Random();
                int index = random.nextInt(mangbaihat.size());
                new PlayMp3().execute(mangbaihat.get(index).getLinkBaiHat());
                fragment_dia_nhac.Playnhac(mangbaihat.get(index).getHinhBaiHat());
                getSupportActionBar().setTitle(mangbaihat.get(index).getTenBaiHat());
            } else if (repeat) {
                new PlayMp3().execute(mangbaihat.get(currentPosition).getLinkBaiHat());
                fragment_dia_nhac.Playnhac(mangbaihat.get(currentPosition).getHinhBaiHat());
                getSupportActionBar().setTitle(mangbaihat.get(currentPosition).getTenBaiHat());
            } else {
                currentPosition++;
                if (currentPosition >= mangbaihat.size()) {
                    currentPosition = 0;
                }
                new PlayMp3().execute(mangbaihat.get(currentPosition).getLinkBaiHat());
                fragment_dia_nhac.Playnhac(mangbaihat.get(currentPosition).getHinhBaiHat());
                getSupportActionBar().setTitle(mangbaihat.get(currentPosition).getTenBaiHat());
            }
        }
    }

    private void PreviousSong() {
        if (mangbaihat.size() > 0) {
            mediaPlayer.stop();
            mediaPlayer.reset();
            if (random) {
                Random random = new Random();
                int index = random.nextInt(mangbaihat.size());
                new PlayMp3().execute(mangbaihat.get(index).getLinkBaiHat());
                fragment_dia_nhac.Playnhac(mangbaihat.get(index).getHinhBaiHat());
                getSupportActionBar().setTitle(mangbaihat.get(index).getTenBaiHat());
            } else if (repeat) {
                new PlayMp3().execute(mangbaihat.get(currentPosition).getLinkBaiHat());
                fragment_dia_nhac.Playnhac(mangbaihat.get(currentPosition).getHinhBaiHat());
                getSupportActionBar().setTitle(mangbaihat.get(currentPosition).getTenBaiHat());
            } else {
                currentPosition--;
                if (currentPosition < 0) {
                    currentPosition = mangbaihat.size() - 1;
                }
                new PlayMp3().execute(mangbaihat.get(currentPosition).getLinkBaiHat());
                fragment_dia_nhac.Playnhac(mangbaihat.get(currentPosition).getHinhBaiHat());
                getSupportActionBar().setTitle(mangbaihat.get(currentPosition).getTenBaiHat());
            }
        }
    }

    private void GetDataFromIntent() {
        Intent intent = getIntent();
        mangbaihat.clear();
        if (intent != null) {
            if (intent.hasExtra("cakhuc")) {
                BaiHat baihat = intent.getParcelableExtra("cakhuc");
                mangbaihat.add(baihat);
            }
            if (intent.hasExtra("cacbaihat")) {
                ArrayList<BaiHat> BaihatArrayList = intent.getParcelableArrayListExtra("cacbaihat");
                if (BaihatArrayList != null) {
                    mangbaihat.addAll(BaihatArrayList);
                }
            }
        }
        if (mangbaihat.size() > 0) {
            getSupportActionBar().setTitle(mangbaihat.get(0).getTenBaiHat());
            new PlayMp3().execute(mangbaihat.get(0).getLinkBaiHat());
            imgplay.setImageResource(R.drawable.iconpause);
        }
    }

    private void Init() {
        txtTimesong = findViewById(R.id.textviewtimesong);
        txtTotaltimesong = findViewById(R.id.textviewtitletimesong);
        sktime = findViewById(R.id.seekbarplaynhac);
        imgplay = findViewById(R.id.imagebuttonplay);
        imgnext = findViewById(R.id.iconnext);
        imgprev = findViewById(R.id.iconpre);
        imgrepeat = findViewById(R.id.iconrepeat);
        imgrandom = findViewById(R.id.imagebuttonrandom);
        viewPagerplaynhac = findViewById(R.id.viewpagerplaynhac);
        toolbarplaynhac = findViewById(R.id.toolbarplaynhac);

        setSupportActionBar(toolbarplaynhac);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarplaynhac.setNavigationOnClickListener(v -> {
            finish();
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
            }
            mangbaihat.clear();
        });

        adapternhac = new ViewPagerPlayListNhac(getSupportFragmentManager());
        fragment_dia_nhac = new Fragment_dia_nhac();
        fragment_play_danh_sach_cac_bai = new Fragment_play_danh_sach_cac_bai();

        adapternhac.addFragment(fragment_dia_nhac);
        adapternhac.addFragment(fragment_play_danh_sach_cac_bai);
        viewPagerplaynhac.setAdapter(adapternhac);
    }

    class PlayMp3 extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String baihat) {
            super.onPostExecute(baihat);
            try {
                if (mediaPlayer == null) {
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.setOnCompletionListener(mp -> {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                        NextSong();
                    });
                }
                mediaPlayer.setDataSource(baihat);
                mediaPlayer.prepare();
                mediaPlayer.start();
                TimeSong();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(PlayNhacActivity.this, "Error playing song", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        txtTotaltimesong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        sktime.setMax(mediaPlayer.getDuration());

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    sktime.setProgress(mediaPlayer.getCurrentPosition());
                    txtTimesong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this, 1000);
                }
            }
        }, 1000);

        mediaPlayer.setOnCompletionListener(mp -> {
            mediaPlayer.reset();
            NextSong();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
