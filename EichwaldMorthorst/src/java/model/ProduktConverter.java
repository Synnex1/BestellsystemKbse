/*;;
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Produkt;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;



@FacesConverter("pConverter")
public class ProduktConverter implements Converter {
    private Produkt Produkt;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        
        Produkt produkt = new Produkt();
        value = value.trim();                           // "ID:" + id + "\\Name:" + name + "\\Anzahl:" + anzahl;
        String backslashString = "\"";
        value = value.concat(backslashString);
        char backslash = '\'' ;
        int index = value.indexOf(backslash);
        String id = value.substring(3, index);
        int index2 = value.indexOf(backslash, index+2);
        String name = value.substring(index+7, index2);
        index = index2 + 9;
        index2 = value.indexOf(backslash, index);
        String anzahl = value.substring(index, index2);
        
        produkt.setId(id);
        /*ctrl = (CategoryController) context.getApplication().getELResolver().getValue(
                context.getELContext(), null, "categoryController");        

        Category category = ctrl.findById(Integer.valueOf(value));
        return category; */
        return produkt;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        return value.toString();
    }

}