package ms.appcenter.sampleapp.android;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Rule;
import org.junit.After;
import com.microsoft.appcenter.espresso.Factory;
import com.microsoft.appcenter.espresso.ReportHelper;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.rule.ActivityTestRule;
import android.content.Intent;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Rule
    public ReportHelper reportHelper = Factory.getReportHelper();

    @After
    public void TearDown() {
        reportHelper.label("Stopping App");
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("ms.appcenter.sampleapp.android", appContext.getPackageName());
    }

    @Test
    public void checkTextView1() {
        activityRule.launchActivity(new Intent());
        onView(withId(R.id.editText1)).perform(typeText("12345678"));
        try {
            Thread.sleep(5000);
        }
        catch(InterruptedException e){}
        onView(withId(R.id.textView1)).check(matches(withText("12345678")));
    }

    @Test
    public void checkTextView2() {
        activityRule.launchActivity(new Intent());
        int[] buttons = { R.id.radioButton1, R.id.radioButton2, R.id.radioButton3, R.id.radioButton4 };

        int index = 0;
        for(int btn : buttons) {
            onView(withId(btn)).perform(click()).check(matches(isChecked()));
            onView(withId(R.id.textView2)).check(matches(withText((index + 1))));
            try {
                Thread.sleep(1000);
            }
            catch(InterruptedException e){}
            index++;
        }
    }

    @Test
    public void checkFibonacciInTextView3() {
        activityRule.launchActivity(new Intent());
        int[] fibonacciSample = { 1, 1, 2, 3, 5, 8, 13, 21, 34, 55 };
        String fibonacciString = Arrays.toString(fibonacciSample);
        onView(withId(R.id.fibbutton)).perform(click());
        onView(withId(R.id.textView3)).check(matches(withText(fibonacciString)));
    }
}
