package com.example.hoaht.roomexample;

import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.hoaht.roomexample.databases.AppDatabase;
import com.example.hoaht.roomexample.databases.models.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnTurnOff).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_OFF_TIMEOUT, 1);
            }
        });
        appDatabase = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "data_name.db").build();
        Database db = new Database();
        db.execute();
    }

    private class Database extends AsyncTask<Void, Void, List<User>> {

        @Override
        protected List<User> doInBackground(Void... voids) {
            User user = new User(1, "Hoang", "Hoa");
            appDatabase.userDao().insertAll(user);
            return appDatabase.userDao().getAll();
        }

        @Override
        protected void onPostExecute(List<User> users) {
            super.onPostExecute(users);
            User user = users.get(0);
            Log.d(TAG, "onPostExecute: " + user.getId() + "-->" + user.getFirstName() + "-->" + user.getLastName());
        }
    }
}
