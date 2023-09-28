package design.lld.filterandsearch.model;

import lombok.Getter;

import java.util.List;


@Getter
public class Product {

    private List<Attribute> attributes;

    public Attribute getAttribute(String name) {
        return null;
    }
}
