package com.example.cse110project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import android.content.Context;

import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;

import com.example.cse110project.activity.CompassActivity;
import com.example.cse110project.model.User;
import com.example.cse110project.model.UserAPI;
import com.example.cse110project.model.UserDao;
import com.example.cse110project.model.UserDatabase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.time.Instant;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@RunWith(RobolectricTestRunner.class)
public class US3Tests {
    UserAPI api = new UserAPI();

    @Test
    public void getTimeTest() {
        String currTime = Instant.now().toString();
        assertNotNull(currTime);
        System.out.println("\n" + currTime + "\n");
    }

    @Test
    public void getRemoteUserLocationTest() throws ExecutionException, InterruptedException, TimeoutException {
        Future<User> userFuture = api.getUserLocationAsync("some private code");
        User userInfo = userFuture.get(1, TimeUnit.SECONDS);
        assertNotNull(userInfo);
        System.out.println("\n"+ userInfo.toJSON() + "\n");
    }

    @Test
    public void checkDaoInsert() throws ExecutionException, InterruptedException, TimeoutException {
        UserAPI api = UserAPI.provide();
        Future<User> futureUser = api.getUserLocationAsync("19");
        User user = futureUser.get(1, TimeUnit.SECONDS);

        Context context = ApplicationProvider.getApplicationContext();
        UserDatabase db = UserDatabase.provide(context);
        UserDao dao = db.getDao();
        dao.upsert(user);

        boolean isInserted = dao.exists("19");
        assertEquals(true, isInserted);
    }

    @Test
    public void checkDaoGet() throws ExecutionException, InterruptedException, TimeoutException {
        UserAPI api = new UserAPI();
        Future<User> futureUser = api.getUserLocationAsync("19");
        User user = futureUser.get(1, TimeUnit.SECONDS);

        Context context = ApplicationProvider.getApplicationContext();
        UserDatabase db = UserDatabase.provide(context);
        UserDao dao = db.getDao();
        dao.upsert(user);

        assertEquals(null, user.private_code);

        //LiveData<User> liveUser = dao.get("19");
    }

    @Test
    public void testJsonForPut() throws ExecutionException, InterruptedException, TimeoutException {
        User putUser = new User("l7har", "0000", "A place",35, 25);
        var executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            api.putUserLocation(putUser);
        });
        Future<User> future = api.getUserLocationAsync(putUser.public_code);
        User userFromCloud = future.get(1, TimeUnit.SECONDS);

        assertEquals(userFromCloud.public_code, putUser.public_code);
        assertEquals(userFromCloud.label, putUser.label);
        assertEquals(userFromCloud.latitude, putUser.latitude, .0001);
        assertEquals(userFromCloud.longitude, putUser.longitude, .0001);

    }

    @Test
    public void getThenPut() throws ExecutionException, InterruptedException, TimeoutException {
        Future<User> future = api.getUserLocationAsync("spirit");
        User user = future.get(1, TimeUnit.SECONDS);
        System.out.println("\n"+ user.toJSON() + "\n");
        user.private_code = "1234";
        user.latitude = 100;
        user.longitude = -50;
        //System.out.println("\n"+ user.toJSON() + "\n");
        var putFuture = api.putUserAsync(user);
        String put = putFuture.get(1,TimeUnit.SECONDS);
        future = api.getUserLocationAsync("spirit");
        user = future.get(1, TimeUnit.SECONDS);
        System.out.println("\n"+ user.toJSON() + "\n");

        assertEquals(100, user.latitude, .0001);
        assertEquals(-50, user.longitude, .0001);
    }

    @Test
    public void putLocationInCompassActivity() {
        try(ActivityScenario<CompassActivity> scenario = ActivityScenario.launch(CompassActivity.class)){
            scenario.onActivity(activity -> {

            });
        }
    }


}
