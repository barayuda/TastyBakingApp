package webid.barayuda.tastybakingapp;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import webid.barayuda.tastybakingapp.features.recipelist.RecipeListActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * Created by BARAYUDA on 7/17/2017.
 */

@RunWith(AndroidJUnit4.class)
public class NavigationTest {

    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }

    private IdlingResource idlingResource;

    @Rule
    public ActivityTestRule<RecipeListActivity> testRule =
            new ActivityTestRule<>(RecipeListActivity.class);

    @Before
    public void registerIdlingResource(){
        idlingResource = testRule.getActivity().getIdlingResource();
        Espresso.registerIdlingResources(idlingResource);
    }

    @After
    public void unregisterIdlingResource(){
        if(idlingResource != null) Espresso.unregisterIdlingResources(idlingResource);
    }

    @Test
    public void selectRecipe_selectStep_checkContent(){
        //scroll to item position
        onView(withId(R.id.rv_recipe))
                .perform(scrollToPosition(0));

        //click item
        onView(withRecyclerView(R.id.rv_recipe).atPosition(0))
                .perform(click());

        //scroll to step
        onView(withId(R.id.rv_steps))
                .perform(scrollToPosition(1));

        //click step
        onView(withRecyclerView(R.id.rv_steps).atPosition(1))
                .perform(click());

        //validate content step detail
        try {
            onView(allOf(withId(R.id.tv_step_description), withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
                    .check(matches(isDisplayed()));
            //video visible, img gone
            onView(allOf(withId(R.id.player), withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
                    .check(matches(isDisplayed()));
            onView(allOf(withId(R.id.img_step), withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
                    .check(matches(not(isDisplayed())));
        }catch (NoMatchingViewException e){
            try {
                onView(allOf(withId(R.id.tv_step_description), withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
                        .check(matches(isDisplayed()));
                //img visible, video gone
                onView(allOf(withId(R.id.img_step), withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
                        .check(matches(isDisplayed()));
                onView(allOf(withId(R.id.player), withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
                        .check(matches(not(isDisplayed())));

            }catch (NoMatchingViewException er){
                onView(allOf(withId(R.id.tv_step_description), withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
                        .check(matches(isDisplayed()));
                //img and video gone
                onView(allOf(withId(R.id.img_step), withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
                        .check(matches(not(isDisplayed())));

                onView(allOf(withId(R.id.player), hasChildren(is(3)))) //got 3 child count after debug, stil wondering
                        .check(matches(not(isDisplayed())));
            }
        }
    }

    public static Matcher<View> hasChildren(final Matcher<Integer> numChildrenMatcher) {
        return new TypeSafeMatcher<View>() {

            /**
             * matching with viewgroup.getChildCount()
             */
            @Override
            public boolean matchesSafely(View view) {
                return view instanceof ViewGroup && numChildrenMatcher.matches(((ViewGroup)view).getChildCount());
            }

            /**
             * gets the description
             */
            @Override
            public void describeTo(Description description) {
                description.appendText(" a view with # children is ");
                numChildrenMatcher.describeTo(description);
            }
        };
    }
}
