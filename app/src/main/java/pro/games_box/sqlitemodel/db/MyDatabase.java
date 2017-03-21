package pro.games_box.sqlitemodel.db;

import android.content.ContentResolver;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

/**
 * Created by admin on 21.03.2017.
 */

public class MyDatabase extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "my.db";
    private static final int VERSION_DB = 1;

    public static final String CONTENT_AUTHORITY = "pro.games_box.sqlitemodel";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String CONTENT_TYPE_BASE = "vnd.android.cursor.dir/vnd.mymodel.";
    public static final String CONTENT_ITEM_TYPE_BASE = "vnd.android.cursor.item/vnd.mymodel.";

    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, VERSION_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MyTableContract.MyTable.CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MyTableContract.TABLE);
        onCreate(db);
    }
}
