/**
 *
 * Copyright 2019 paolo mococci
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package local.example.wildflower;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static local.example.wildflower.R.id.main_edit_text;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TypedInstrumentedTests {

    /* remember to switch on the screen on testing device */

    private String toBeTyped;

    @Rule
    public ActivityTestRule<MainActivity>
            mainActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {
        toBeTyped = "Welcome to Eden.";
        mainActivityTestRule.getActivity().getSupportFragmentManager().beginTransaction();
    }

    @Test
    public void clearedAndTypedTest() {
        onView(withId(main_edit_text)).check(matches(isDisplayed()));
        onView(withId(main_edit_text)).perform(clearText(),
                typeText("Welcome."), closeSoftKeyboard());
    }

    @Test
    public void anotherTypedTest() {
        onView(withId(main_edit_text)).perform(clearText(), typeText(toBeTyped),
                closeSoftKeyboard());
        onView(withId(main_edit_text)).perform(click());
        onView(withId(main_edit_text)).check(matches(withText(toBeTyped)));
    }
}
