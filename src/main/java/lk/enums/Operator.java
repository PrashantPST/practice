package lk.enums;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum Operator {
    IN_RANGE {
        @Override
        public String parseMvelValue(String[] input, VariableValueType type) {
            return Double.valueOf(input[0]).toString();
        }
    },
    OUT_RANGE {
        @Override
        public String parseMvelValue(String[] input, VariableValueType type) {
            return Double.valueOf(input[0]).toString();
        }
    },
    INCLUDE {
        @Override
        public String parseMvelValue(String[] input, VariableValueType type) {
            switch (type) {
                case STRING:
                    return Arrays.stream(input).map(value -> "'" + value + ",").collect(Collectors.joining(",", "[", "]"));
                case NUMBER:
                    return Arrays.stream(input).map(value -> Double.valueOf(value).toString()).collect(Collectors.joining(",", "[", "]"));
                default:
                    throw new UnsupportedOperationException("");
            }
        }
    },
    NOT_INCLUDE {
        @Override
        public String parseMvelValue(String[] input, VariableValueType type) {
            return INCLUDE.parseMvelValue(input, type);
        }
    },
    GREATER_THAN {
        @Override
        public String parseMvelValue(String[] input, VariableValueType type) {
            return Double.valueOf(input[0]).toString();
        }
    },
    GREATER_THAN_OR_EQUAL {
        @Override
        public String parseMvelValue(String[] input, VariableValueType type) {
            return Double.valueOf(input[0]).toString();
        }
    },
    LESS_THAN {
        @Override
        public String parseMvelValue(String[] input, VariableValueType type) {
            return Double.valueOf(input[0]).toString();
        }
    },
    LESS_THAN_OR_EQUAL {
        @Override
        public String parseMvelValue(String[] input, VariableValueType type) {
            return Double.valueOf(input[0]).toString();
        }
    },
    EQUAL {
        @Override
        public String parseMvelValue(String[] input, VariableValueType type) {
            return input[0];
        }
    },
    IS_SUBSET {
        @Override
        public String parseMvelValue(String[] input, VariableValueType type) {
            return INCLUDE.parseMvelValue(input, type);
        }
    },
    CONTAINS_AT_LEAST_ONE {
        @Override
        public String parseMvelValue(String[] input, VariableValueType type) {
            return INCLUDE.parseMvelValue(input, type);
        }
    };

    public abstract String parseMvelValue(String[] input, VariableValueType type);
}
