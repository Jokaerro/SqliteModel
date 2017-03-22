package pro.games_box.sqlitemodel.db;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
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

//    private static final int MYTABLE = 10;
//    private static final int MYTABLE_ID = 20;
    private static final String BASE_PATH = "mytables";

    private static final String AUTHORITY = "pro.games_box.sqlitemodel";

    @Override
    public boolean onCreate() {
        mMyDatabase = new MyDatabase(getContext());
        mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mUriMatcher.addURI(MyDatabase.CONTENT_AUTHORITY, UriPathEnum.MYTABLE.path, UriPathEnum.MYTABLE.code);
        mUriMatcher.addURI(MyDatabase.CONTENT_AUTHORITY, UriPathEnum.MYTABLE_ID.path, UriPathEnum.MYTABLE_ID.code);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase database = mMyDatabase.getReadableDatabase();
        UriPathEnum pathEnum = findPathEnum(uri);
        Cursor cursor = null;
        switch (pathEnum){
            case MYTABLE:
                cursor = database.query(pathEnum.table, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case MYTABLE_ID:
                cursor = database.query(pathEnum.table, projection,
                        onMergeSelection(selection, MyTableContract.MyTable.TITLE + " =? "),
                        onMergeSelectionArgs(selectionArgs, new String[]{uri.getLastPathSegment()}),
                        null, null, sortOrder);
                break;
            default:
                throw new UnsupportedOperationException("No such table to query");
        }

        Context context = getContext();
        if (context!=null && cursor!=null) {
            cursor.setNotificationUri(context.getContentResolver(), uri);
        }

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        UriPathEnum pathEnum = findPathEnum(uri);
        return pathEnum.contentType;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String whereClause, @Nullable String[] whereArgs) {
        UriPathEnum pathEnum = findPathEnum(uri);
        SQLiteDatabase database = mMyDatabase.getWritableDatabase();
        return database.delete(pathEnum.table, whereClause, whereArgs);
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    private UriPathEnum findPathEnum(Uri uri) {
        int code = mUriMatcher.match(uri);
        UriPathEnum pathEnum = UriPathEnum.findPathEnum(code);
        if (pathEnum == null) {
            throw new UnsupportedOperationException("Unknown uri with code " + code);
        }
        return pathEnum;
    }

    private String onMergeSelection(String selection, String addSelection) {
        if (selection == null || selection == "") {
            return addSelection;
        }
        return "(" +
                selection + ") and (" +
                addSelection + ")";

    }

    static String[] onMergeSelectionArgs(String[]... arrays) {
        int length = 0;
        for (String[] array : arrays) {
            if(array == null){
                continue;
            }
            length += array.length;
        }

        final String[] result = new String[length];

        int offset = 0;
        for (String[] array : arrays) {
            if(array == null){
                continue;
            }
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
        }

        return result;
    }
}
