package com.devj.prioritiesapp.models;

import com.devj.prioritiesapp.exceptions.NumberOrderOutOfBoundsException;

import javax.persistence.*;
import java.io.Serializable;

@SuppressWarnings("all")
@Entity
@Table(name = "Tb_priority")
public class ItemPriority implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public String name;
    private double value;
    private int priorityOrderNumber;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getPriorityOrderNumber() {
        return priorityOrderNumber;
    }

    public void setPriorityOrderNumber(int priorityOrderNumber) {
        this.priorityOrderNumber = priorityOrderNumber;
    }

    public void upOrder() throws NumberOrderOutOfBoundsException {
        if (canUpOrder())
        setPriorityOrderNumber((getPriorityOrderNumber() + 1));
        else throw new NumberOrderOutOfBoundsException("Não é possível alterar a ordem " +
                getPriorityOrderNumber());
    }

    public void downOrder() throws NumberOrderOutOfBoundsException {
        if (canDownOrder())
        setPriorityOrderNumber((getPriorityOrderNumber() - 1));
        else throw new NumberOrderOutOfBoundsException("Não é possível alterar a ordem " +
                getPriorityOrderNumber());
    }

    public boolean canUpOrder(){
        return getPriorityOrderNumber() < 2;
    }

    public boolean canDownOrder(){
        return getPriorityOrderNumber() > 0;
    }

}
