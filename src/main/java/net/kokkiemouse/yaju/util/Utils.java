package net.kokkiemouse.yaju.util;
import net.minecraft.util.Identifier;

import java.lang.reflect.Constructor;

public class Utils {
    private Utils() {
    }

    public static Identifier id(String resource) {
        return new Identifier("yajumod", resource);
    }

    public static <T> T construct(Class<T> klass, Object... args) {
        for (Constructor<?> constructor : klass.getConstructors()) {
            constructor.setAccessible(true);
            try {
                //noinspection unchecked
                return (T) constructor.newInstance(args);
            } catch (IllegalArgumentException ignored) {
            } catch (ReflectiveOperationException e) {
                throw new RuntimeException(e);
            }
        }
        throw new IllegalArgumentException("None of the constructors matched the provided args!");
    }
}
