package sample;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import java.lang.Integer;
import java.lang.String;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        GridPane root = new GridPane();
        root.setPadding(new Insets(10));
        root.setHgap(20);
        root.setVgap(10);
        root.setBackground(new Background(new BackgroundFill(Color.DARKBLUE, null, null)));
        DBManager accesoBD = new DBManager();

        //Boton para el inicio del programa y que muestre el tiempo transcurrido
        Button btnIniciarTiempo = new Button("Iniciar");
        //Colar en la esquina superior izquierda del GridPane
        root.add(btnIniciarTiempo, 0, 0);

        //Boton para Mostrar las Tablas
        Button btnMostrarTablas = new Button("Mostrar Tablas");
        //Determinar fuente y tamaño del boton
        btnMostrarTablas.setFont(Font.font("Arial",18));
        //Determinar el color de la etiqueta
        btnMostrarTablas.setTextFill(Color.BLACK);
        //Colar
        root.add(btnMostrarTablas, 2, 1);

        //TexrField para mostrar el tiempo transcurrido en pantalla
        TextField txtTiempo = new TextField();
        //Agregar al lado del boton de inicio
        root.add(txtTiempo,1,0);

        //TextField para mostrar la hora actual en pantalla
        TextField txtHoraActual = new TextField();
        //Agregar al lado del tiempo transcurrido en pantalla
        root.add(txtHoraActual,2,0);

        //Label para ID del Usuario
        Label lblIdCte = new Label("ID Cliente: ");
        //Determinar fuente y tamaño de la etiqueta
        lblIdCte.setFont(Font.font("Arial",18));
        //Determinar el color de la etiqueta
        lblIdCte.setTextFill(Color.BLACK);
        //Agregar debajo del boton de inicio
        root.add(lblIdCte,0,1);

        //TextField para ingresar la ID del Usuario
        TextField txtIdCte = new TextField();
        //Agregar al lado del label cliente
        root.add(txtIdCte,1,1);

        //Label para Nombre del Usuario
        Label lblNomCte = new Label("Nombre: ");
        //Determinar fuente y tamaño de la etiqueta
        lblNomCte.setFont(Font.font("Arial",18));
        //Determinar el color de la etiqueta
        lblNomCte.setTextFill(Color.BLACK);
        //Agregar debajo del boton de inicio
        root.add(lblNomCte,0,2);

        //TextField para ingresar el Nombre del Usuario
        TextField txtNomCte = new TextField();
        //Agregar al lado del label cliente
        root.add(txtNomCte,1,2);

        //Label para Apellido del Usuario
        Label lblApeCte = new Label("Apellidos: ");
        //Determinar fuente y tamaño de la etiqueta
        lblApeCte.setFont(Font.font("Arial",18));
        //Determinar el color de la etiqueta
        lblApeCte.setTextFill(Color.BLACK);
        //Agregar debajo del boton de inicio
        root.add(lblApeCte,0,3);

        //TextField para ingresar Apellido del Usuario
        TextField txtApCte = new TextField();
        //Agregar al lado del label cliente
        root.add(txtApCte,1,3);

        //Label para Direccion del Usuario
        Label lblDirCte = new Label("Direccion: ");
        //Determinar fuente y tamaño de la etiqueta
        lblDirCte.setFont(Font.font("Arial",18));
        //Determinar el color de la etiqueta
        lblDirCte.setTextFill(Color.BLACK);
        //Agregar debajo del boton de inicio
        root.add(lblDirCte,0,4);

        //TextField para ingresar la Direccion del Usuario
        TextField txtCliente = new TextField();
        //Agregar al lado del label cliente
        root.add(txtCliente,1,4);

        //boton para agregar un usuario
        Button btnAgregar = new Button("Agregar");
        //Determinar fuente y tamaño del boton
        btnAgregar.setFont(Font.font("Arial",18));
        //Determinar el color
        btnAgregar.setTextFill(Color.BLACK);
        //Agregar
        root.add(btnAgregar,2,2);


        //boton para eliminar a un usuario
        Button btnEliminar = new Button("Eliminar");
        //Determinar fuente y tamaño del boton
        btnEliminar.setFont(Font.font("Arial",18));
        //Determinar el color de la etiqueta
        btnEliminar.setTextFill(Color.BLACK);
        //Agregar
        root.add(btnEliminar,2,3);


        //Boton para buscar a un usuario
        Button btnBuscar = new Button("Buscar: ");
        //Determinar el color de la etiqueta
        btnEliminar.setTextFill(Color.BLACK);
        //Determinar fuente y tamaño del boton
        btnBuscar.setFont(Font.font("Arial",18));
        //Agregar
        root.add(btnBuscar,2,4);


        //Boton para modificar un usuario
        Button btnModificar = new Button("Modificar");
        //Determinar el color de la etiqueta
        btnModificar.setTextFill(Color.BLACK);
        //Determinar fuente y tamaño del boton
        btnModificar.setFont(Font.font("Arial",18));
        //Agregar
        root.add(btnModificar,3,2);



        //Acciones para el boton Iniciar
        btnIniciarTiempo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                class TiempoCurso extends Thread {
                    public TiempoCurso() {
                        super();
                    }

                    public void run() {
                        int nuMin = 0;
                        int nuSeg = 0;

                        String tiemp = String.valueOf(nuMin + nuSeg);
                        txtTiempo.setText(tiemp);

                        try {
                            for (; ; ) {
                                if (nuSeg != 59) {
                                    nuSeg++;
                                } else {
                                    if (nuMin != 59) {
                                        nuSeg = 0;
                                        nuMin++;
                                    }
                                }
                                txtTiempo.setText("Tiempo : " + nuMin + ": " + nuSeg + "");
                                sleep(999);
                            }
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }

                    }
                }

                TiempoCurso t = new TiempoCurso();
                t.start();

                Calendar calendario = Calendar.getInstance();
                int hora, minutos, segundos;

                hora = calendario.get(Calendar.HOUR_OF_DAY);
                minutos = calendario.get(Calendar.MINUTE);
                segundos = calendario.get(Calendar.SECOND);

                txtHoraActual.setText("Hora actual: " + hora + ":" + minutos + ":" + segundos);
            }

        });

        //Acciones para el boton Mostrar Tablas
        btnMostrarTablas.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                OperacionesClientes opCliente;
                TableView<Cliente> TableViewCliente = new TableView<>();
                TableColumn<Cliente,String> clmnClienteId = new TableColumn<>("clienteId");
                TableColumn<Cliente,String>clmnnombre = new TableColumn<>("nombre");
                TableColumn<Cliente,String>clmnapellido = new TableColumn<>("apellidos");
                TableColumn<Cliente,String>clmndireccion  = new TableColumn<>("direccion");
                ObservableList<Cliente> ObvCliente = FXCollections.observableArrayList();
                Connection connection;
                //FilteredList<Cliente> filtroCliente = new FilteredList<>(ObvCliente,e->true);


                clmnClienteId.setCellValueFactory(new PropertyValueFactory<Cliente,String>("clienteId"));
                clmnnombre.setCellValueFactory(new PropertyValueFactory<Cliente,String>("nombre"));
                clmnapellido.setCellValueFactory(new PropertyValueFactory<Cliente,String>("apellidos"));
                clmndireccion.setCellValueFactory(new PropertyValueFactory<Cliente,String>("direccion"));
                TableViewCliente.getColumns().addAll(clmnClienteId,clmnnombre,clmnapellido,clmndireccion);


                    ObvCliente.clear();
                    try{
                        connection = DBManager.getConnection();
                        ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM cliente");
                        while(rs.next()){
                            ObvCliente.add(new Cliente(rs.getInt("clienteId"),rs.getString("nombre"),rs.getString("apellidos"),rs.getString("direccion")));
                        }
                    }catch(SQLException ex){
                        ex.printStackTrace();
                        System.out.println("SQLException:␣" + ex.getMessage());
                        System.out.println("SQLState:␣" + ex.getSQLState());
                        System.out.println("VendorError:␣" + ex.getErrorCode());
                    }
                    TableViewCliente.setItems(ObvCliente);
                    TableViewCliente.setMaxSize(550,250);
                    TableViewCliente.setMinSize(550,200);

                    root.add(TableViewCliente,0,5,3,3);

                    TableViewCliente.getSelectionModel().selectedItemProperty().addListener(
                            new ChangeListener<Cliente>() {
                                @Override
                                public void changed(ObservableValue<? extends Cliente> arg0,
                                                    Cliente valorAnterior, Cliente valorSeleccionado) {
                                    if (valorSeleccionado!=null){
                                        txtIdCte.setText(String.valueOf(valorSeleccionado.getClienteId()));
                                        txtNomCte.setText(valorSeleccionado.getNombre());
                                        txtApCte.setText(valorSeleccionado.getApellidos());
                                        txtCliente.setText(valorSeleccionado.getDireccion());


                                        //btnAgregar.setDisable(true);
                                        //btnEliminar.setDisable(false);
                                        btnMostrarTablas.setDisable(false);
                                    }
                                }

                            }
                    );


            }



        });

        //Acciones para el boton Agregar
        btnAgregar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                OperacionesClientes opCliente;
                ObservableList<Cliente> ObvCliente = FXCollections.observableArrayList();
                if(txtIdCte.getText().isEmpty() || txtNomCte.getText().isEmpty() || txtApCte.getText().isEmpty() || txtCliente.getText().isEmpty()){
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setContentText("Es necesario llenar todas los campos");
                    alert1.showAndWait();
                }else{
                    Cliente cliente = new Cliente(Integer.parseInt(txtIdCte.getText()),txtNomCte.getText(),txtApCte.getText(),txtCliente.getText());
                    opCliente = new OperacionesClientes(DBManager.getConnection());                           // llama a la clase                            |
                    opCliente.insertCliente(Integer.parseInt(txtIdCte.getText()),txtNomCte.getText(),txtApCte.getText(),txtCliente.getText());
                    ObvCliente.add(cliente);
                    txtIdCte.clear();
                    txtNomCte.clear();
                    txtApCte.clear();
                    txtCliente.clear();

                }

            }
        });

        //Acciones para el boton Eliminar
        btnEliminar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                OperacionesClientes opCliente;
                if(txtIdCte.getText().isEmpty() ){
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setContentText("Para eliminar un cliente es necesario llenar el campo ID ");
                }else{
                    opCliente = new OperacionesClientes(DBManager.getConnection());
                    opCliente.deleteCliente(Integer.parseInt(txtIdCte.getText()));
                    txtIdCte.clear();
                }

            }
        });


        primaryStage.setTitle("Hola");
        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }
}
