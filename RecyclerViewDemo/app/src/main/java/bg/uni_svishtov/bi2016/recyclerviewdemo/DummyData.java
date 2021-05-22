package bg.uni_svishtov.bi2016.recyclerviewdemo;

import java.util.ArrayList;
import java.util.List;

public class DummyData {
    private String sip;
    private String name;

    public String getSip() {
        return sip;
    }

    public void setSip(String sip) {
        this.sip = sip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DummyData(String sip, String name) {
        this.sip = sip;
        this.name = name;
    }

    static List<DummyData> generate() {
        ArrayList<DummyData> result = new ArrayList<DummyData>();
        result.add(new DummyData("sip:1-233-1413@uni-svishtov.bg", "Angelin Lalev"));
        result.add(new DummyData("sip:33-433-1413@uni-svishtov.bg", "Ivan Andreev"));
        result.add(new DummyData("sip:611-233-3113@uni-svishtov.bg", "Miroslav Lalev"));
        result.add(new DummyData("sip:63-2133-1113@uni-svishtov.bg", "Nikolai Lalev"));
        result.add(new DummyData("sip:11-2133-134413@uni-svishtov.bg", "Petyr Ivanov"));
        result.add(new DummyData("sip:11-233-1413@uni-svishtov.bg", "Blagovest Vasilev"));
        result.add(new DummyData("sip:1-233-1413@uni-svishtov.bg", "Mirena Tashkova"));
        result.add(new DummyData("sip:1-233-1413@uni-svishtov.bg", "Petyr Petrov"));
        result.add(new DummyData("sip:1-233-1413@uni-svishtov.bg", "Nikolai Viktorov"));
        result.add(new DummyData("sip:1-233-1413@uni-svishtov.bg", "Alina Lukanova"));
        result.add(new DummyData("sip:1-233-1413@uni-svishtov.bg", "Vjara Todorova"));
        return result;
    }
}
