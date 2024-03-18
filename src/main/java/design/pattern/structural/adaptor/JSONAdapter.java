package design.pattern.structural.adaptor;

import org.json.JSONObject;
import org.json.XML;

// Adapter to convert XML to JSON
public class JSONAdapter {
  private XMLData xmlData;

  public JSONAdapter(XMLData xmlData) {
    this.xmlData = xmlData;
  }

  public JSONObject toJSON() {
    return XML.toJSONObject(xmlData.getXml());
  }
}

