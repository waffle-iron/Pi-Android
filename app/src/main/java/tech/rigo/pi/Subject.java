package tech.rigo.pi;

/**
 * Created by rigo on 8/30/16.
 */
public class Subject {
    String name;
    int formulaCount;
    int colorHex;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public int getFormulaCount() {
        return formulaCount;
    }
    
    public void setFormulaCount(int formulaCount) {
        this.formulaCount = formulaCount;
    }
    
    public int getColorHex() {
        return colorHex;
    }
    
    public void setColorHex(int value) {
        this.colorHex = value;
    }
}
