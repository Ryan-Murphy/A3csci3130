package com.acme.a3csci3130;

import android.os.SystemClock;
import android.support.test.rule.ActivityTestRule;
import android.widget.EditText;
import android.widget.ListView;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import android.view.View;


import org.junit.Rule;

/**
 * Used for testing the deleting and updating of contacts
 */

public class DetailViewActivityTests {
    // Start the MainActivity
    @Rule
    public final ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);
    /**
     * Test DELETE by creating a user -> verify it gets deleted by count
     * */
    @Test
    public void testContactDelete() throws Exception{
        int count;
        // Find our list view
        ListView listView = (ListView) mActivityRule.getActivity().findViewById(R.id.listView);
        // Define test credentials for a test user to create
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
        // Click on the submit button
        onView(withId(R.id.submitButton)).perform(click());
        // Wait to stop any race conditions
        SystemClock.sleep(1500);
        count = listView.getCount();
        // Now test deleting the user by selecting last entry in list (our newest one) and pressing delete
        onData(anything()).inAdapterView(allOf(withId(R.id.listView), isCompletelyDisplayed())).atPosition(listView.getCount()-1).perform(click());
        onView(withId(R.id.deleteButton)).perform(click());
        // assert that the list has in fact removed the created entry by checking if count is - 1
        assertEquals(listView.getCount(), count - 1);
    }

    @Test
    public void testUpdate(){

    }
}
