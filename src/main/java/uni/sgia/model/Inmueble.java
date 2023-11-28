package uni.sgia.model;

//import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "inmueble")
public class Inmueble {
    private String inmuebles;
    private String direccion;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int numero_inmueble; //Cantidad de inmuebles;
    private Date fecha_registro;
    @Id
    private String id_inmueble; //Codigo del inmueble


    @Override
    public String toString(){
        return "Inmuebele{"  + inmuebles + ", " + " ," + direccion + " , " + fecha_registro + " ," + numero_inmueble + " }" ;
    }

}
