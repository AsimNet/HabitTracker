package one.group.habittracker.data;

import android.provider.BaseColumns;

/**
 * Created by asimaltwijry on 4/28/17.
 *
 */

public final class HabitContract {

    private HabitContract() {
    }


    public final static class HabitEntry implements BaseColumns {


        public static final String TABLE_NAME = "habits";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_HABIT_NAME = "name";
        public static final String COLUMN_HABIT_STATUS = "status";


        /**
         * Possible values for the style of the COLUMN_HABIT_STATUS.
         */
        public static final int STATUS_NONE = 0;
        public static final int STATUS_DONE = 1;
        public static final int STATUS_CANCELED = 2;

    }


}
