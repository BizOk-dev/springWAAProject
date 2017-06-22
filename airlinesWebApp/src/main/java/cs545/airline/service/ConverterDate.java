package cs545.airline.service;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.DateTimeConverter;
import javax.inject.Named;

@Named
public class ConverterDate extends DateTimeConverter implements Converter{

	public ConverterDate() {
		 setPattern("MM/dd/yyyy");
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		 if (value != null && value.length() != getPattern().length()) {
	            throw new ConverterException("Invalid format");
	        }

	        return super.getAsObject(context, component, value);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		// TODO Auto-generated method stub
		return arg2.toString();
	}

}
