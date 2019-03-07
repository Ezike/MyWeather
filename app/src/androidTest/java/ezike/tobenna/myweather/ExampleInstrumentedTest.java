package ezike.tobenna.myweather;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {

//      Use either Application Provider to get context or use Androidx InstrumentationRegistry below
        Context appContext = ApplicationProvider.getApplicationContext();

//      Context appContext = InstrumentationRegistry.getInstrumentation().getContext();

        assertEquals("ezike.tobenna.myweather", appContext.getPackageName());
    }
}
