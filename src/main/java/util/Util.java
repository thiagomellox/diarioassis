package util;

import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class Util {
	private static final Locale BR_LOCALE = new Locale("pt", "BR");
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(
			"dd/MM/yyyy", BR_LOCALE);
	private static final SimpleDateFormat DATE_FORMAT_Test = new SimpleDateFormat(
			"yyyy-MM-dd");

	public static String maxLength(String entrada, int tamanho) {
		if (entrada.length() > tamanho) {
			entrada = entrada.substring(0, tamanho);
		}
		return entrada;
	}

	public static String soNumeros(String entrada, int tamanho) {
		if (entrada.length() > tamanho) {
			entrada = entrada.substring(0, tamanho);
		}
		Pattern numericos = Pattern.compile("[0-9]", 2);
		Matcher encaixe = numericos.matcher(entrada);
		StringBuffer saida = new StringBuffer();
		while (encaixe.find()) {
			saida.append(encaixe.group());
		}
		return saida.toString();
	}

	public static String numberFormat(Float valor) {
		String entrada = String.valueOf(valor);
		entrada = entrada.replace(".", "");
		if (entrada.length() > 2) {
			String start = entrada.substring(0, entrada.length() - 2);
			String end = "," + entrada.charAt(entrada.length() - 2)
					+ entrada.charAt(entrada.length() - 1);

			return start + end;
		}
		return entrada + ",00";
	}

	public static String moneyFormat(String entrada, int tamanho) {
		entrada = soNumeros(entrada, tamanho);
		if (entrada.length() > 2) {
			String start = entrada.substring(0, entrada.length() - 2);
			String end = "," + entrada.charAt(entrada.length() - 2)
					+ entrada.charAt(entrada.length() - 1);

			return start + end;
		}
		return entrada;
	}

	public static MaskFormatter mascara(String s) {
		try {
			return new MaskFormatter(s);
		} catch (ParseException e) {
		}
		return null;
	}

	public static void onlyNumbers(JTextField c, KeyEvent evt) {
		boolean validade = false;
		try {
			if ((Character.isDigit(evt.getKeyChar()))
					|| (".".equals(String.valueOf(evt.getKeyChar())))) {
				validade = true;
			}
			if (!validade) {
				String i = c.getText();
				i = i.substring(0, i.length() - 1);
				c.setText(i);
			}
		} catch (Exception localException) {
		}
	}

	public static Date parseDate(String strDate) {
		try {
			return (strDate == null) || (strDate.length() < 10) ? null
					: DATE_FORMAT.parse(strDate);
		} catch (ParseException e) {
		}
		return null;
	}

	public static Date somaDate(Date data, int mesAx) {
		String dataUsual = formatDate(data);

		int dia = Integer.parseInt(dataUsual.substring(0,
				dataUsual.indexOf('/')));
		int mes = Integer.parseInt(dataUsual.substring(
				dataUsual.indexOf('/') + 1, dataUsual.lastIndexOf('/'))) - 1;
		int ano = Integer.parseInt(dataUsual.substring(dataUsual
				.lastIndexOf('/') + 1));
		mes += mesAx;

		Date dataReal = new GregorianCalendar(ano, mes, dia).getTime();
		return dataReal;
	}

	public static String formatDate(Date date) {
		if (date == null) {
			return "";
		}
		Calendar calendar = getDataCalendar();
		calendar.setTime(date);

		StringBuffer buf = new StringBuffer();
		int day = calendar.get(5);
		if (day < 10) {
			buf.append("0");
		}
		buf.append(day);
		buf.append("/");
		int month = calendar.get(2) + 1;
		if (month < 10) {
			buf.append("0");
		}
		buf.append(month);
		buf.append("/");
		buf.append(calendar.get(1));
		return buf.toString();
	}

	public static String formatDateEUA(Date date) {
		if (date == null) {
			return "";
		}
		Calendar calendar = getDataCalendar();
		calendar.setTime(date);

		StringBuffer buf = new StringBuffer();
		int day = calendar.get(5);

		buf.append(calendar.get(1));
		buf.append("-");
		int month = calendar.get(2) + 1;
		if (month < 10) {
			buf.append("0");
		}
		buf.append(month);
		buf.append("-");
		if (day < 10) {
			buf.append("0");
		}
		buf.append(day);

		return buf.toString();
	}

	public static Calendar getDataCalendar() {
		Locale loc = new Locale("pt", "BR");

		Calendar cal = Calendar.getInstance(loc);
		return cal;
	}

	public static int pegarMeses(Date cadastro, Date validade) {
		String dataCadastro = formatDate(cadastro);
		String dataValidade = formatDate(validade);

		int diaC = Integer.parseInt(dataCadastro.substring(0,
				dataCadastro.indexOf('/')));
		int mesC = Integer.parseInt(dataCadastro.substring(
				dataCadastro.indexOf('/') + 1, dataCadastro.lastIndexOf('/'))) - 1;
		int anoC = Integer.parseInt(dataCadastro.substring(dataCadastro
				.lastIndexOf('/') + 1));

		int diaV = Integer.parseInt(dataValidade.substring(0,
				dataValidade.indexOf('/')));
		int mesV = Integer.parseInt(dataValidade.substring(
				dataValidade.indexOf('/') + 1, dataValidade.lastIndexOf('/'))) - 1;
		int anoV = Integer.parseInt(dataValidade.substring(dataValidade
				.lastIndexOf('/') + 1));

		int ano = anoV - anoC;
		if (ano > 0) {
			ano *= 12;
			mesV += ano;
		}
		int mes = mesV - mesC;
		return mes;
	}
}
