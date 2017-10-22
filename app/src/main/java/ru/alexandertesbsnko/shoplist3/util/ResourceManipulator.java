package ru.alexandertesbsnko.shoplist3.util;

import android.content.Context;
import android.content.res.Resources;

import java.lang.reflect.Field;

public class ResourceManipulator {
    public static int getResIdByStringName(String resName, Class<?> c) {

        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    public static int getResIdByStringName(Context context, String resName,String resType) {
        Resources r = context.getResources();
        return r.getIdentifier(resName,resType,context.getPackageName());
    }
}
