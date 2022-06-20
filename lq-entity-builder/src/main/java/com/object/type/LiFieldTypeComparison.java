package com.object.type;

public class LiFieldTypeComparison {

    public static LiFieldType typeComparison(String type) {
        LiFieldType[] enumConstants = LiFieldType.class.getEnumConstants();
        for (LiFieldType enumConstant : enumConstants) {
            type=type.toUpperCase();
            if (type.equals(enumConstant.name())) {
                return enumConstant;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(typeComparison("int"));
    }
}
