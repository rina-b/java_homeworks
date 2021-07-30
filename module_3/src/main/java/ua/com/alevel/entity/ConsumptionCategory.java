package ua.com.alevel.entity;

import javax.persistence.*;

@Entity
@DiscriminatorValue("consumption")
public class ConsumptionCategory extends Operation {
}

