package pro.games_box.sqlitemodel.db;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by admin on 21.03.2017.
 */

public class MyProvider extends ContentProvider {
    private MyDatabase mMyDatabase;
    private UriMatcher mUriMatcher;

    private static final int MYTABLE = 10;
    private static final int MYTABLE_ID = 20;
    private static final String BASE_PATH = "mytables";

    private static final String AUTHORITY = "pro.games_box.sqlitemodel";

    @Override
    public boolean onCreate() {
        mMyDatabase = new MyDatabase(getContext());
        mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mUriMatcher.addURI(MyDatabase.CONTENT_AUTHORITY, BASE_PATH, MYTABLE);
        mUriMatcher.addURI(MyDatabase.CONTENT_AUTHORITY, BASE_PATH + "/#", MYTABLE_ID);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase database = mMyDatabase.getReadableDatabase();


        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
