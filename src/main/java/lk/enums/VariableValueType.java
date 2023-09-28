package lk.enums;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public enum VariableValueType {
    STRING {
        @Override
        public Object getValidValue(Object input) {
            return input == null ? "NaN" : input;
        }

        @Override
        public List<Operator> operator2() {
            return Arrays.asList(Operator.INCLUDE, Operator.NOT_INCLUDE, Operator.IS_SUBSET, Operator.CONTAINS_AT_LEAST_ONE);
        }
    },
    NUMBER {
        @Override
        public Object getValidValue(Object input) {
            if (Objects.nonNull(input)) {
                try {
                    return Double.valueOf(input.toString());
                }
                catch (NumberFormatException ex) {
                    return -999;
                }
            }
            return -999;
        }

        @Override
        public List<Operator> operator2() {
            return null;
        }
    },
    BOOLEAN {
        @Override
        public Object getValidValue(Object input) {
            return null;
        }

        @Override
        public List<Operator> operator2() {
            return null;
        }
    };
    public abstract Object getValidValue(Object input);
    public abstract List<Operator> operator2();
};
