package com.udacity.gradle.builditbigger;

import android.support.test.espresso.core.deps.guava.util.concurrent.Service;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import static org.hamcrest.Matchers.greaterThan;

@RunWith(AndroidJUnit4.class)
public class GetJokeAsyncTaskTest {

    @Test
    public void testReceiveJoke() throws InterruptedException {
        final CountDownLatch signal = new CountDownLatch(1);

        GetJokeAsyncTask.JokeListener mockedJokeListener = new GetJokeAsyncTask.JokeListener(){


            @Override
            public void receivedJoke(String joke) {

                Assert.assertNotNull(joke);
                Assert.assertThat(joke.length(), greaterThan(0));
                signal.countDown();
            }
        };
        new GetJokeAsyncTask().execute(mockedJokeListener);

        signal.await();
    }
}