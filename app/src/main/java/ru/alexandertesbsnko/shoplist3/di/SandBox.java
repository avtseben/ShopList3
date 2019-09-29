package ru.alexandertesbsnko.shoplist3.di;

import java.util.HashMap;
import java.util.Map;

public class SandBox {

    public static SandBox INSTANCE = new SandBox();

    private final Map<Class,Object> beans = new HashMap<>();

    private SandBox() {
    }

    public Object get(Class clazz) {
         return beans.get(clazz);
    }

    public void set(Class clazz, Object bean) {
         beans.put(clazz,bean);
    }

}
