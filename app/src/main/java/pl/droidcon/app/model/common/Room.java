package pl.droidcon.app.model.common;


import android.support.annotation.StringRes;

import pl.droidcon.app.R;

public enum Room {

    ROOM_1(R.string.room_1, 1),
    ROOM_2(R.string.room_2, 2);

    @StringRes
    private int stringRes;

    private int id;

    Room(int stringRes, int id) {
        this.stringRes = stringRes;
        this.id = id;
    }

    @StringRes
    public int getStringRes() {
        return stringRes;
    }

    public static Room valueOfRoomId(int roomId) {
        for (Room room : values()) {
            if (roomId == room.id) {
                return room;
            }
        }

        throw new IllegalArgumentException("Not supported room id");
    }
}
