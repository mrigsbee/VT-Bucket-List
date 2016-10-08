package mrigsbee.vtbucketlist;

public class TableEntry {

    static final String CHECKBOX_OUTLINE = "ic_checkbox_outline";
    static final String CHECKBOX = "ic_checkbox";

    int _id;
    String checkbox;
    String entry;

    public TableEntry(){
        this.checkbox = CHECKBOX_OUTLINE;
    }

    public TableEntry(String entry){
        this.checkbox = CHECKBOX_OUTLINE;
        this.entry = entry;
    }

    public TableEntry(int id, String checkbox, String entry){
        this._id = id;
        this.checkbox = checkbox;
        this.entry = entry;
    }

    public int getId(){
        return this._id;
    }
    public void setId(int id){
        this._id = id;
    }
    public String getEntry(){
        return this.entry;
    }
    public void setEntry(String entry){
        this.entry = entry;
    }
    public String getCheckbox(){
        return this.checkbox;
    }
    public void setCheckbox(String checkbox){
        this.checkbox = checkbox;
    }
}