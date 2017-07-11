package com.acme.a3csci3130;

import android.os.SystemClock;
import android.support.test.rule.ActivityTestRule;
import android.widget.ListView;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

/**
 * Test class for testing creation of a contact
 * */
public class CreateContactActivityTests {
    // Start the MainActivity
    @Rule
    public final ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    /**
     * Test whether the create function is working properly.
     * If it executes without any exception we know it properly finished.
     * */
    @Test
    public void testCreateContact() throws Exception{
        // Define test credentials
        String name = "TestCreate";
        String email = "TestEmail@Test.com";
        String businessNumber = "123456789";
        String primaryBusiness = "Fisher";
        String address = "Cole Harbour";
        String province = "NS";

        // Enter into the create menu by pressing on submit on main screen
        onView(withId(R.id.submitButton)).perform(click());
        // Enter test credentials and make sure to close keyboard to avoid any view errors
        onView(withId(R.id.name)).perform(typeText(name)).perform(closeSoftKeyboard());
        onView(withId(R.id.email)).perform(typeText(email)).perform(closeSoftKeyboard());
        onView(withId(R.id.primaryBusiness)).perform(typeText(primaryBusiness)).perform(closeSoftKeyboard());
        onView(withId(R.id.address)).perform(typeText(address)).perform(closeSoftKeyboard());
        onView(withId(R.id.businessNumber)).perform(typeText(businessNumber)).perform(closeSoftKeyboard());
        onView(withId(R.id.province)).perform(typeText(province)).perform(closeSoftKeyboard());

        // Find our list view
        ListView listView = (ListView) mActivityRule.getActivity().findViewById(R.id.listView);
        // save count before we create to make sure if we create it's +1
        int count = listView.getCount();

        // Click on the submit button
        onView(withId(R.id.submitButton)).perform(click());

        // wait to avoid race condition
        SystemClock.sleep(1500);

        // assert the list count has gone up one
        assertEquals(listView.getCount(), count + 1);
    }

}
