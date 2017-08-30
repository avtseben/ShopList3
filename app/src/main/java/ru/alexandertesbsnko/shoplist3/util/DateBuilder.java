package ru.alexandertesbsnko.shoplist3.util;

import android.content.Context;

        import java.text.DateFormat;
        import java.util.Date;

import ru.alexandertesbsnko.shoplist3.R;
import ru.alexandertesbsnko.shoplist3.SLApplication;


public class DateBuilder {

    private static final long MINUTE_IN_SECS = 60;
    private static final long HOUR_IN_SECS = 3600;

    public DateBuilder(){
    }

    /**
     * Статический метод построения красивого формата даты в стиле
     * "сегодня", "час назад" и тд. нужен контекст чтобы добраться до ресурсов
     * где лежат string переменные
     */
    public static String timeTitleBuilder(long someUnixTime) {
        Context context = SLApplication.getContext();
        long currentTime = System.currentTimeMillis();
        if(currentTime >= someUnixTime) {
            long timeDeltaInSecs = Math.round((currentTime - someUnixTime) / 1000);
            if (timeDeltaInSecs >= HOUR_IN_SECS * 24) {
                Date simpleDate = new Date(someUnixTime);
                String dateFormat = DateFormat.getInstance().format(simpleDate);
                return dateFormat;
            } else if (timeDeltaInSecs >= HOUR_IN_SECS) {
                return Math.round(timeDeltaInSecs / HOUR_IN_SECS) + context.getResources().getString(R.string.hour_ago);
            } else if (timeDeltaInSecs >= MINUTE_IN_SECS) {
                return Math.round(timeDeltaInSecs / MINUTE_IN_SECS) + context.getResources().getString(R.string.minutes_ago);
            }
            return timeDeltaInSecs + context.getResources().getString(R.string.seconds_ago);
        }
        return context.getResources().getString(R.string.note_made_in_future_humor);
    }
}
