package com.android.sqlite;

import android.content.Context;

import androidx.room.Room;

import java.util.List;

public class AccessData {

    private static AccessData sAccessData;

    private ExampleElementDao exampleElementDao;

    private AccessData (Context ctx){
        Context appContext = ctx.getApplicationContext();
        ExerciseDatabase database = Room.databaseBuilder(appContext, ExerciseDatabase.class, "Exercise")
                .allowMainThreadQueries().build();
        exampleElementDao = database.getExampleElementDao();
    }

    public static AccessData getAccessData(Context ctx){
        if(sAccessData == null) sAccessData = new AccessData(ctx);
        return sAccessData;
    }

    public List<ExampleElement> getExampleElements(){ return exampleElementDao.getExampleElements(); }
    public ExampleElement getExampleElement(String id) { return exampleElementDao.getExampleElement(id); }
    public void saveExampleElement(ExampleElement exampleElement){ exampleElementDao.addExampleElement(exampleElement); }
    public void updateExampleElement(ExampleElement exampleElement){ exampleElementDao.updateExampleElement(exampleElement); }
    public void deleteExampleElement(ExampleElement exampleElement){ exampleElementDao.deleteExampleElement(exampleElement); }
}
