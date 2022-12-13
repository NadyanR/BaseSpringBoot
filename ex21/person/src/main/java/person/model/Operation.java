package person.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
public class Operation {

    @Column(name = "name")
    private String productLineName;

    @Column(name = "equipment_count")
    private Integer equipmentCount;

    private Integer square;//занимаемая прощадь производства
}
