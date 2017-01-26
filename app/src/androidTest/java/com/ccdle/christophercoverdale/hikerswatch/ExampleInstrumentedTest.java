package com.ccdle.christophercoverdale.hikerswatch;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    private MainActivity mMainActivity;

    @Rule
    public ActivityTestRule<MainActivity> mainActivityTest = new ActivityTestRule<>(MainActivity.class, true, true);

    @Before
    public void setActivity() {
        mMainActivity = mainActivityTest.getActivity();
    }

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.ccdle.christophercoverdale.hikerswatch", appContext.getPackageName());
    }

    @Test
    public void testingFragmentAppearsInMain() {
        Context appContext = InstrumentationRegistry.getTargetContext();

        onView(withId(R.id.title_hikers_watch)).check(matches(withText("Hiker's Watch")));
        onView(withId(R.id.latitude)).check(matches(withText("Latitude:")));
        onView(withId(R.id.longitude)).check(matches(withText("Longitude:")));
        onView(withId(R.id.accuracy)).check(matches(withText("Accuracy:")));
        onView(withId(R.id.speed)).check(matches(withText("Speed:")));
        onView(withId(R.id.bearing)).check(matches(withText("Bearing:")));
        onView(withId(R.id.address)).check(matches(withText("Address:")));
    }
}
