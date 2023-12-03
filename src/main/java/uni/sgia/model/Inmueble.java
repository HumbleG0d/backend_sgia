package uni.sgia.model;

//import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Data //Anotacion de Lombok para generar automaticamente getters , setters
@Entity //Anotacion de JPA para indicar que esta clase es una entidad persistente
@Table(name = "inmueble") //Anotacion de JPA para especificar el nombre de la tabla en la base de datos
public class Inmueble {
    @Id //Anotacion de JPA para indicar que este campo es la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Generacion automatica para la clave primaria
    private Long id; //Identificador unico generado automaticamente
    
    private String id_inmueble; //Codigo del inmueble
    private String inmuebles; //Tipo de inmueble
    private String direccion; //Direccion del inmueble
    private Date fecha_registro = new Date(); //Fecha del registro del inmueble
    


    @Override
    public String toString(){ // Metodo toString personalizado para representar la instacia de la clase como una cadena de texto
        return "Inmuebele{"  + id + ", "  + id_inmueble + " , " + direccion + " ," + inmuebles + ", " +fecha_registro + " }" ;
    }

}
