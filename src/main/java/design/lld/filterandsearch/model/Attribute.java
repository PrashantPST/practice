package design.lld.filterandsearch.model;


import lombok.Getter;

@Getter
public class Attribute {

    private String key;
    private String value;
    private String operatorName;

    public boolean isEqual(Attribute obj) {
        return key.equals(obj.key);
    }
}
