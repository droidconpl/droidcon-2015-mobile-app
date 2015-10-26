package pl.droidcon.app;


import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import pl.droidcon.app.ui.activity.MainActivity;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by piotr on 26.10.15.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MainActivityTest {

    @Test
    public void testSetMainActivityActionBar() throws Exception {
        MainActivity activity = Robolectric.setupActivity(MainActivity.class);
        assertThat(activity.getSupportActionBar().getTitle() == activity.getText(R.string.agenda));
    }
}