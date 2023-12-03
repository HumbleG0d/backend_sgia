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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //Cantidad de inmuebles;
    
    private String id_inmueble; //Codigo del inmueble
    private String inmuebles;
    private String direccion;
    private Date fecha_registro = new Date();
    


    @Override
    public String toString(){
        return "Inmuebele{"  + inmuebles + ", "  + direccion + " , " + fecha_registro + " ," + id + " }" ;
    }

}
