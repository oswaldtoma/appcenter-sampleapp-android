package ms.appcenter.sampleapp.android;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;
import com.microsoft.appcenter.distribute.Distribute;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.text.TextWatcher;
import android.text.Editable;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    private final Fragment[] views = {
            new WelcomeActivity(),
            new BuildActivity(),
            new TestActivity(),
            new DistributeActivity(),
            new CrashesActivity(),
            new AnalyticsActivity(),
            new PushActivity()
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root);

        // Initialize SDK
        if (!BuildConfig.APPCENTER_APP_SECRET.equals("")) {
            // Use APPCENTER_APP_SECRET environment variable if it exists
            AppCenter.start(getApplication(), BuildConfig.APPCENTER_APP_SECRET,
                    Analytics.class, Crashes.class, Distribute.class);
        } else {
            // Otherwise use the hardcoded string value here
            AppCenter.start(getApplication(), "<APP SECRET HERE>",
                    Analytics.class, Crashes.class, Distribute.class);
        }


        if (BuildConfig.DEBUG) {
            AppCenter.setLogLevel(Log.VERBOSE);
        }

        // UI Elements
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        Button btnClickArr[] = {
            findViewById(R.id.radioButton1),
            findViewById(R.id.radioButton2),
            findViewById(R.id.radioButton3),
            findViewById(R.id.radioButton4),
        };

        for(Button btn : btnClickArr) {
            btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    TextView textview = findViewById(R.id.textView2);
                    textview.setText(btn.getText().subSequence(6, 7).toString());
                }
            });
        }

        EditText editText = findViewById(R.id.editText1);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                TextView tv = findViewById(R.id.textView1);
                tv.setText(editText.getText());
            }
        });

        Button fibbutton = findViewById(R.id.fibbutton);
        fibbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Fibonacci fib = new Fibonacci();
                String resultString = "";
                for(int i = 1; i <= 10; i++) {
                    resultString = resultString + fib.getNthElement(i);
                    if(i != 10) {
                        resultString = resultString + ", ";
                    }
                }
                TextView tv = findViewById(R.id.textView3);
                tv.setText(resultString);
            }
        });
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(@IntRange(from = 0, to = 6) final int position) {
            return views[position];
        }

        @Override
        public int getCount() {
            return views.length;
        }

        @Override
        public CharSequence getPageTitle(@IntRange(from = 0, to = 6) final int position) {
            if (views[position] instanceof WelcomeActivity) {
                return "Welcome";
            } else if (views[position] instanceof BuildActivity) {
                return "Build";
            } else if (views[position] instanceof TestActivity) {
                return "Test";
            } else if (views[position] instanceof DistributeActivity) {
                return "Distribute";
            } else if (views[position] instanceof CrashesActivity) {
                return "Crashes";
            } else if (views[position] instanceof AnalyticsActivity) {
                return "Analytics";
            } else if (views[position] instanceof PushActivity) {
                return "Push";
            }

            return views[position].getClass().getSimpleName().trim().replace("Activity", "");
        }
    }
}