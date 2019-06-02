package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

public class Controller implements Initializable {




    @FXML private AnchorPane principal;
    @FXML private Label lblHoras;
    @FXML private Label lblMinutos;
    @FXML private Label lblSegundos;
    @FXML private Label prueba;
    private int horas, minutos,segundos;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.initComponents();
    }


    public void initComponents(){
        Calendar calendar = new GregorianCalendar();

        this.horas = calendar.get(Calendar.HOUR_OF_DAY);
        this.minutos = calendar.get(Calendar.MINUTE);
        this.segundos = calendar.get(Calendar.SECOND);

        this.lblHoras.setText(this.horas+"");
        this.lblMinutos.setText(this.minutos+"");
        this.lblSegundos.setText(this.segundos+"");
        this.hilo().start();
    }



    public Thread hilo(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while(true){
                    Calendar calendar = new GregorianCalendar();
                    String pmam = "";
                    if(calendar.get(Calendar.AM_PM) == Calendar.AM){
                        pmam = "AM";
                    } else {
                        pmam = "PM";
                    }


                    segundos += 1;

                    String finalPmam = pmam;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            lblHoras.setText(horas+"");
                            lblMinutos.setText(minutos+"");
                            lblSegundos.setText(segundos+"");
                            prueba.setText(finalPmam);
                        }
                    });

                    if(segundos>59){
                        minutos += 1;
                        segundos = 0;
                    }

                    if(minutos>59){
                        horas += 1;
                        minutos = 0;
                    }
                    if(horas > 23){
                        horas = 0;
                    }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.setDaemon(true);

        return thread;


    }




}
