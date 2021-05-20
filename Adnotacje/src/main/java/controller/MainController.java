package controller;

import table.Table;
import annotations.MyAnnotation;
import enums.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDate;

public class MainController {

    Object object = null;

    @FXML
    private TextField ClassName;

    @FXML
    private TextField FieldValue;

    @FXML
    private TableView<Table> ObjectTable;


    @FXML
    @MyAnnotation("Name")
    private TableColumn<Table, String> col_Name;

    @FXML
    @MyAnnotation("Value")
    private TableColumn<Table, String> col_Value;

    @FXML
    @MyAnnotation("Type")
    private TableColumn<Table, String> col_Type;

    @FXML
    private ComboBox<Object> ObjectsList;

    @FXML
    private ComboBox<String> FieldList;

    private ObservableList<Table> observableList = FXCollections.observableArrayList();
    private ObservableList<Object> observableObjects = FXCollections.observableArrayList();
    private ObservableList<String> observableFields = FXCollections.observableArrayList();

    public void Clear(){
        observableList.clear();
        observableFields.clear();
    }

    public void Start() throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        MakeObject();
        AddDataToTable();
        AddObjectsToCombo();
        AddFieldsToCombo();
        useAnnotation();
    }

    public void MakeObject() throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        String className = ClassName.getText();
        Class cls = Class.forName(className);

        object = (Object) cls.newInstance();
        observableObjects.add(object);
        System.out.println("Instance: " + object.getClass());
    }

    public void AddDataToTable() throws IllegalAccessException {
        Clear();
        Field fields[] = object.getClass().getDeclaredFields();
        for(Field field : fields){
          field.setAccessible(true);
          col_Name.setCellValueFactory(new PropertyValueFactory<>("col_name"));
          col_Value.setCellValueFactory(new PropertyValueFactory<>("col_value"));
          col_Type.setCellValueFactory(new PropertyValueFactory<>("col_type"));
          observableList.add(new Table(field.getName(), field.get(object).toString(), field.getGenericType().getTypeName()));
          observableFields.add(field.getName());
        }
        ObjectTable.setItems(observableList);
        System.out.println(object.getClass());
    }

    public void AddObjectsToCombo(){
        ObjectsList.setItems(observableObjects);
    }

    public void AddFieldsToCombo() {
        FieldList.setItems(observableFields);
    }

    public void ChooseObject() throws IllegalAccessException {
        object = ObjectsList.getSelectionModel().getSelectedItem();
        Clear();
        AddDataToTable();
    }

    public void deleteObject() throws IllegalAccessException {
        System.out.println("1");
        Object DeleteObject = ObjectsList.getSelectionModel().getSelectedItem();
        observableObjects.remove(DeleteObject);
        AddObjectsToCombo();
        Clear();
        AddDataToTable();
        System.out.println("2");
    }

    public void EditFieldValue() throws IllegalAccessException {
        String string = "java.lang.String";
        String Int = "Int";
        String enumValue = "java.lang.Enum";
        String Date = "java.time.LocalDate";
        String className = ClassName.getText();
        String selectedField = FieldList.getSelectionModel().getSelectedItem();
        String methodName = selectedField.substring(0,1).toUpperCase()+selectedField.substring(1);
        String fieldValue = FieldValue.getText();
        try {
            Field field = object.getClass().getDeclaredField(selectedField);
            if (field.getGenericType().getTypeName() == string) {
                Method m = object.getClass().getMethod("set" + methodName, String.class);
                m.invoke(object, fieldValue);
            } else if (field.getGenericType().getTypeName() == Int) {
                Method m = object.getClass().getMethod("set" + methodName, Integer.class);
                m.invoke(object, Integer.parseInt(fieldValue));
            } else if (field.getGenericType().getTypeName() == enumValue) {
                //System.out.println(className);
                if(className.equals("Car")){
                    Method m = object.getClass().getMethod("set" + methodName, Enum.class);
                    m.invoke(object, Enum.valueOf(CarType.class,fieldValue));
                }else if(className.equals("Club")){
                    Method m = object.getClass().getMethod("set" + methodName, Enum.class);
                    m.invoke(object, Enum.valueOf(ClubType.class, fieldValue));
                }
            } else if (field.getGenericType().getTypeName() == Date) {
                Method m = object.getClass().getMethod("set" + methodName, LocalDate.class);
                m.invoke(object, LocalDate.parse(fieldValue));
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        Clear();
        AddDataToTable();
    }

    public void useAnnotation() throws IllegalAccessException {
        Field[] fields = this.getClass().getDeclaredFields();
        for(Field field : fields) {
            MyAnnotation annotation = field.getAnnotation(MyAnnotation.class);
            if (field.isAnnotationPresent(MyAnnotation.class) && field.getType().equals(TableColumn.class)) {
                TableColumn tableColumn = (TableColumn) field.get(this);
                tableColumn.setText(annotation.value());
            }
        }
    }
}