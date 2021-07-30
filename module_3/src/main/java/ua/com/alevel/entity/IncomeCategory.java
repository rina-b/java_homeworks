package ua.com.alevel.entity;

import javax.persistence.*;

@Entity
@DiscriminatorValue("income")
public class IncomeCategory extends Operation {
}
