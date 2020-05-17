package com.android.sqlite;

import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@androidx.room.Database(version = 1, entities = {ExampleElement.class })
@TypeConverters({Converters.class})
public abstract class ExerciseDatabase extends RoomDatabase {
    abstract public ExampleElementDao getExampleElementDao();
}
