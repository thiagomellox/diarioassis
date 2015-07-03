package util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;

public class DecimalConverter {
	
	
	private static final DecimalFormat format = new DecimalFormat("##,###,###,##0.00", new DecimalFormatSymbols (new Locale ("pt", "BR")));  
	
	public DecimalConverter(){
		format.setMinimumFractionDigits(2);   
		format.setParseBigDecimal (true); 
	}
	
	public static String format(Double valor){
		if(valor != null){
			return format.format(valor);
		}
		return "";
	}
	
	public static Double parse(String valor){
		if(valor != null){
			try {
				return format.parse(valor).doubleValue();
			} catch (ParseException e) {
				return null;
			}
		}
		return null;
	}
}
