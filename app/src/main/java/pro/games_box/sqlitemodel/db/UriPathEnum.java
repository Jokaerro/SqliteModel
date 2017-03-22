package pro.games_box.sqlitemodel.db;

/**
 * Created by admin on 21.03.2017.
 */

public enum UriPathEnum {

    MYTABLE(1, "mytable", "mytable", false, MyTableContract.TABLE),
    MYTABLE_ID(2, "mytable/*", "mytable_id", true, MyTableContract.TABLE);

    public int code;
    public String path;
    public String contentType;
    public String table;

    UriPathEnum(int code, String path, String contentType, boolean item, String table) {
        this.code = code;
        this.path = path;
        this.contentType = (item ? MyDatabase.CONTENT_ITEM_TYPE_BASE : MyDatabase.CONTENT_TYPE_BASE) + contentType;
        this.table = table;
    }

    public static UriPathEnum findPathEnum(int code) {
        for (UriPathEnum pathEnum : values()) {
            if (pathEnum.code == code) {
                return pathEnum;
            }
        }
        return null;
    }

    public static UriPathEnum findPathEnum(String tableName) {
        for (UriPathEnum pathEnum : values()) {
            if (pathEnum.table.equals(tableName)) {
                return pathEnum;
            }
        }
        return null;
    }
}
