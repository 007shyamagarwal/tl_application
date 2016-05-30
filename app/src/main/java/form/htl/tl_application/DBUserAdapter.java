package form.htl.tl_application;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;




/**
 * Created by shyam on 5/28/16.
 */
public class DBUserAdapter {

        public static final String KEY_ROWID = "_id";
        public static final String KEY_NAME= "name";
        public static final String KEY_PASSWORD = "password";
        private static final String TAG = "DBAdapter";
        private static final String Email_id="email";
        private static final String Mobile_no="mobile_no";

        private static final String DATABASE_NAME = "usersdb";
        private static final String DATABASE_TABLE = "users";
        private static final int DATABASE_VERSION = 1;

        private static final String DATABASE_CREATE =
                "create table users (_id integer primary key autoincrement, "
                        + "name text not null, "
                        + "password text not null ,"+"email text not null ,"+"mobile_no text not null ) ;";

        private Context context = null;
        private DatabaseHelper DBHelper;
        private SQLiteDatabase db;

        public DBUserAdapter(Context ctx)
        {
            this.context = ctx;
            DBHelper = new DatabaseHelper(context);
        }

        private static class DatabaseHelper extends SQLiteOpenHelper
        {
            DatabaseHelper(Context context)
            {
                super(context, DATABASE_NAME, null, DATABASE_VERSION);
            }

            @Override
            public void onCreate(SQLiteDatabase db)
            {
                db.execSQL(DATABASE_CREATE);
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
            {
//                Log.w(TAG, "Upgrading database from version " + oldVersion
  //                      + " to "
    //                    + newVersion + ", which will destroy all old data");
                db.execSQL("DROP TABLE IF EXISTS users");
                onCreate(db);
            }
        }


        public void open() throws SQLException
        {
            db = DBHelper.getWritableDatabase();
        }


        public void close()
        {
            DBHelper.close();
        }

        public long AddUser(String name, String password ,String email,String mobile_no)
        {
            ContentValues initialValues = new ContentValues();
            initialValues.put(KEY_NAME, name);
            initialValues.put(KEY_PASSWORD, password);
            initialValues.put(Email_id,email);
            initialValues.put(Mobile_no,mobile_no);
            return db.insert(DATABASE_TABLE, null, initialValues);

        }

        public boolean Login(String user_name, String pass_word) throws SQLException
        {
            Cursor mCursor = db.rawQuery("SELECT * FROM " + DATABASE_TABLE + " WHERE email = ? AND password = ?", new String[] {user_name, pass_word});
            if (mCursor != null) {
                if(mCursor.getCount() > 0)
                {
                    return true;
                }
            }
            return false;
        }

    }

