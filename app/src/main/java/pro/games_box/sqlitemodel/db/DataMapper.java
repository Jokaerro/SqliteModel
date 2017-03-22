package pro.games_box.sqlitemodel.db;

import android.database.Cursor;

import java.sql.Date;

import pro.games_box.sqlitemodel.model.MyModel;

/**
 * Created by Tesla on 21.03.2017.
 */

public class DataMapper {

    public MyModel fromCursorMyModel(Cursor cursor){
        MyModel myModel = new MyModel();
        myModel.setTitle(cursor.getString(cursor.getColumnIndex(MyTableContract.MyTable.TITLE)));
        myModel.setDescription(cursor.getString(cursor.getColumnIndex(MyTableContract.MyTable.DESCRIPTION)));
        myModel.setLink(cursor.getString(cursor.getColumnIndex(MyTableContract.MyTable.LINK)));

        return myModel;
    }

    public Date getDateMapper(Cursor cursor, String columnName) {
        int index = cursor.getColumnIndex(columnName);
        if (index < 0) {
            return null;
        }
        return new Date(cursor.getLong(index));
    }
}
