package table;

public class Table {
    String col_name;
    String col_value;
    String col_type;

    public Table(String col_name, String col_value, String col_type) {
        this.col_name = col_name;
        this.col_value = col_value;
        this.col_type = col_type;
    }

    public String getCol_name() {
        return col_name;
    }

    public void setCol_name(String col_name) {
        this.col_name = col_name;
    }

    public String getCol_value() {
        return col_value;
    }

    public void setCol_value(String col_value) {
        this.col_value = col_value;
    }

    public String getCol_type() {
        return col_type;
    }

    public void setCol_type(String col_type) {
        this.col_type = col_type;
    }
}
