package com.example.janpatrix.dailydiary.Database;

/**
 * Created by janpatrix on 02.01.2018.
 */

public class EventDbSchema {

    public static final class EventTable{

        public static final String NAME = "events";

        public static final class Cols {

            public static final String ID = "id";
            public static final String MESSAGE = "message";
            public static final String DATE = "date";
            public static final String END = "end";
            public static final String TYPE = "type";
        }
    }
}
