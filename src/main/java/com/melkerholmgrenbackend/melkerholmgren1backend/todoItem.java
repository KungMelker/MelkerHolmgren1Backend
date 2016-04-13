/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.melkerholmgrenbackend.melkerholmgren1backend;

/**
 *
 * @author Melker
 */
public class todoItem {
    
    
    private String todotext;
    private String tododate;
    private boolean status;

    public todoItem(String todotext, String tododate, boolean status) {
        this.todotext = todotext;
        this.tododate = tododate;
        this.status = status;
    }

    public String getTodotext() {
        return todotext;
    }

    public String getTododate() {
        return tododate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setTodotext(String todotext) {
        this.todotext = todotext;
    }

    public void setTododate(String tododate) {
        this.tododate = tododate;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "** todoItem{" + "todotext=" + todotext + ", tododate=" + tododate + ", status=" + status + '}' + " **";
    }
    
    
    
    
}
