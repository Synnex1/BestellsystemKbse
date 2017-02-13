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
    private Produkt produkt;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        
        produkt = new Produkt();
        value = value.trim();                           // "ID:" + id + "\\Name:" + name + "\\Anzahl:" + anzahl;
        String paragraphString = "§";
        value = value.concat(paragraphString);
        char paragraph = '§' ;
        int index = value.indexOf(paragraph);
        String id = value.substring(3, index);
        int index2 = value.indexOf(paragraph, index+2);
        String name = value.substring(index+7, index2);
        index = index2 + 9;
        index2 = value.indexOf(paragraph, index);
        String anzahl = value.substring(index, index2);
        Long longId = Long.parseLong(id, 10);
        int anzahlInt = Integer.parseInt(anzahl);
        produkt.setId(longId);
        produkt.setName(name);
        produkt.setAnzahl(anzahlInt);
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