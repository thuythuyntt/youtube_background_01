package com.youtu.sleep.youtubebackground.data.source.local.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.youtu.sleep.youtubebackground.data.model.popularvideo.Video;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static com.youtu.sleep.youtubebackground.utils.Contants.TRUE;

/**
 * Created by thuy on 08/08/2018.
 */
public class DatabaseManager extends SQLiteOpenHelper {

    /**
     * Database path
     */
//    private static final String PACKAGE_PATH = "/data/com.youtu.sleep.youtubebackground/databases/database";
//    private static final String DATABASE_PATH = Environment.getDataDirectory().getAbsolutePath()
//            + PACKAGE_PATH;

    private static String DATABASE_PATH;

    /**
     * Database information includes table and columns information
     */

    private static final String DATABASE_NAME = "youtubedb.sqlite";
    private static final String VIDEO_TABLE_NAME = "video";

    private static final String COLUMN_VIDEO_ID = "videoId";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_THUMBNAILS_URL = "thumbnailsUrl";
    private static final String COLUMN_CHANNEL_TITLE = "channelTitle";
    private static final String COLUMN_IS_RECENT = "isRecent";
    private static final String COLUMN_IS_FAVOURITE = "isFavourite";

    private static final int DATABASE_VERSION = 1;

    /**
     * SQL query
     */
    private static final String SQL_GET_ALL_VIDEOS_STRING = "SELECT * FROM " + VIDEO_TABLE_NAME;
    private static final String SQL_GET_FAVOURITE_VIDEOS_STRING = "SELECT * FROM " + VIDEO_TABLE_NAME
            + " WHERE " + COLUMN_IS_FAVOURITE + "=" + TRUE;
    private static final String SQL_GET_RECENT_VIDEOS_STRING = "SELECT * FROM " + VIDEO_TABLE_NAME
            + " WHERE " + COLUMN_IS_RECENT + "=" + TRUE;

    private SQLiteDatabase mSQLiteDatabase;
    private Context mContext;

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
        DATABASE_PATH = mContext.getFilesDir().getAbsolutePath()+ "/";
        copyDatabase();
    }

    private void copyDatabase() {
        try {
            InputStream inputStream = mContext.getAssets().open(DATABASE_NAME);
            String path = DATABASE_PATH + DATABASE_NAME;
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
                FileOutputStream fos = new FileOutputStream(file);
                byte[] bytes = new byte[1024];
                int length = 0;
                while ((length = inputStream.read(bytes)) > 0) {
                    fos.write(bytes, 0, length);
                }
                inputStream.close();
                fos.flush();
                fos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openDatabase() {
        if (mSQLiteDatabase == null || !mSQLiteDatabase.isOpen()) {
            mSQLiteDatabase = SQLiteDatabase.openDatabase(DATABASE_PATH + DATABASE_NAME,
                    null, SQLiteDatabase.OPEN_READWRITE);
        }
    }

    private void closeDatabase() {
        if (mSQLiteDatabase != null && mSQLiteDatabase.isOpen()) {
            mSQLiteDatabase.close();
        }
    }

    /**
     * get general videos
     */

    public ArrayList<Video> getVideos(String query) {
        openDatabase();
        Cursor cursor = mSQLiteDatabase.rawQuery(query, null);

        /**
         * if query's unsuccessful or
         *    query's successful but there isn't no record
         */

        if (cursor == null || cursor.getCount() == 0) {
            closeDatabase();
            return new ArrayList<>();
        }

        /**
         * if query's successful and there're some records
         */

        ArrayList<Video> videos = new ArrayList<>();
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Video v = getEachRecord(cursor);
            videos.add(v);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return videos;
    }

    /**
     * get all favourite videos
     */

    public ArrayList<Video> getFavouriteVideos() {
        return getVideos(SQL_GET_FAVOURITE_VIDEOS_STRING);
    }

    /**
     * get all recent videos
     */

    public ArrayList<Video> getRecentVideos() {
        return getVideos(SQL_GET_RECENT_VIDEOS_STRING);
    }

    /**
     * adding a favourite into table
     */

    public boolean insertAFavouriteVideo(Video video) {
        openDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_VIDEO_ID, video.getVideoId());
        values.put(COLUMN_TITLE, video.getTitle());
        values.put(COLUMN_DESCRIPTION, video.getDescription());
        values.put(COLUMN_THUMBNAILS_URL, video.getUrlThumbnail());
        values.put(COLUMN_CHANNEL_TITLE, video.getChannelTitle());
        values.put(COLUMN_IS_RECENT, video.getIsRecent());
        values.put(COLUMN_IS_FAVOURITE, video.getIsFavourite());

        return mSQLiteDatabase.insert(VIDEO_TABLE_NAME, null, values) != -1;
    }

    private Video getEachRecord(Cursor cursor) {
        String id = cursor.getString(cursor.getColumnIndex(COLUMN_VIDEO_ID));
        String title = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE));
        String description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));
        String thumbUrl = cursor.getString(cursor.getColumnIndex(COLUMN_THUMBNAILS_URL));
        String channelTitle = cursor.getString(cursor.getColumnIndex(COLUMN_CHANNEL_TITLE));
        int isRecent = cursor.getInt(cursor.getColumnIndex(COLUMN_IS_RECENT));
        int isFavourite = cursor.getInt(cursor.getColumnIndex(COLUMN_IS_FAVOURITE));

        return new Video(id, title, channelTitle, description, thumbUrl, isRecent, isFavourite);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
