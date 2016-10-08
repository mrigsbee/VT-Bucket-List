package mrigsbee.vtbucketlist;

public class TableEntry {

    int _id;
    String checkbox;
    String entry;

    public TableEntry(){
        super();
    }

    public TableEntry(String entry){
        this.checkbox = "false";
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