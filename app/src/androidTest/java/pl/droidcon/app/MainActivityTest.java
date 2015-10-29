package pl.droidcon.app;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import pl.droidcon.app.ui.activity.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by piotr on 26.10.15.
 */
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mRule = new ActivityTestRule<>(MainActivity.class);


    @Test
    public void check_001_ifAgendaTitleIsDisplayed() throws InterruptedException {
        onView(withId(R.id.toolbar)).check(matches(withChild(withText(R.string.agenda))));
    }

    @Test
    public void check_002_ifDayoneTitleIsDisplayed() throws InterruptedException {
        onView(withId(R.id.toolbar)).check(matches(withChild(withText(R.string.day_one))));
    }

    @Test
    public void check_003_iffDayTwoTitleIsDisplayed() throws InterruptedException {
        onView(withId(R.id.toolbar)).check(matches(withChild(withText(R.string.day_two))));
    }
}
