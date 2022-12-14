package com.github.SobolevRoman.restaurantvoting.util.validation;

import com.github.SobolevRoman.restaurantvoting.HasId;
import com.github.SobolevRoman.restaurantvoting.error.IllegalRequestDataException;
import lombok.experimental.UtilityClass;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.lang.NonNull;

import java.time.Clock;
import java.time.LocalTime;

@UtilityClass
public class ValidationUtil {
    public static final LocalTime VOTE_TIME_BORDER = LocalTime.of(11, 0);

    public static void checkTime() {
        if (!LocalTime.now(Clock.systemDefaultZone()).isBefore(VOTE_TIME_BORDER)){
             throw new IllegalRequestDataException("Too late for create or change vote today! Try tomorrow before " + VOTE_TIME_BORDER);
        }
    }

    public static void checkNew(HasId bean) {
        if (!bean.isNew()) {
            throw new IllegalRequestDataException(bean.getClass().getSimpleName() + " must be new (id=null)");
        }
    }

    //  Conservative when you reply, but accept liberally (http://stackoverflow.com/a/32728226/548473)
    public static void assureIdConsistent(HasId bean, int id) {
        if (bean.isNew()) {
            bean.setId(id);
        } else if (bean.id() != id) {
            throw new IllegalRequestDataException(bean.getClass().getSimpleName() + " must has id=" + id);
        }
    }

    public static void checkModification(int count, int id) {
        if (count == 0) {
            throw new IllegalRequestDataException("Entity with id=" + id + " not found");
        }
    }

    public static <T> T checkExisted(T obj, int id) {
        if (obj == null) {
            throw new IllegalRequestDataException("Entity with id=" + id + " not found");
        }
        return obj;
    }

    //  https://stackoverflow.com/a/65442410/548473
    @NonNull
    public static Throwable getRootCause(@NonNull Throwable t) {
        Throwable rootCause = NestedExceptionUtils.getRootCause(t);
        return rootCause != null ? rootCause : t;
    }
}