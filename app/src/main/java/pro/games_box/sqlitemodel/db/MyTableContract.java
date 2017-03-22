package pro.games_box.sqlitemodel.db;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by admin on 21.03.2017.
 */

public class MyTableContract {
    public static final String TABLE = "mytable";

    private MyTableContract(){

    }

    private interface MyTableColumns{
        String TITLE = "title";
        String DESCRIPTION = "description";
        String LINK = "link";
    }

    public static class MyTable implements BaseColumns, MyTableColumns{
        public static final String CREATE = "CREATE TABLE " + TABLE + " ( "
                + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TITLE + " TEXT, "
                + DESCRIPTION + " TEXT, "
                + LINK + " LINK )";
    }

    public static Uri buildQuerySymbolUri(String symbol){
        return MyDatabase.BASE_CONTENT_URI.buildUpon().appendPath(TABLE).appendPath(symbol).build();
    }

    public static Uri buildBaseUri(){
        return MyDatabase.BASE_CONTENT_URI.buildUpon().appendPath(TABLE).build();
    }
}
