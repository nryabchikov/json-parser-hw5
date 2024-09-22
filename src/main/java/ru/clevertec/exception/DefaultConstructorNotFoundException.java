package ru.clevertec.exception;

public class DefaultConstructorNotFoundException extends RuntimeException {
    private DefaultConstructorNotFoundException(String message) {
        super(message);
    }

    public static DefaultConstructorNotFoundException byClass(Class<?> tclass) {
        return new DefaultConstructorNotFoundException(
                String.format("Class: %s doesn't have a default constructor.", tclass.getName()));
    }
}
