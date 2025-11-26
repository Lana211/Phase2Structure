/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package phase2structure;

public class PQElement<T>
 {
    public T data;
    float priority;
        
    public PQElement(T e, float pr){
                   data = e;
                   priority = pr;
   }
}