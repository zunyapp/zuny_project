package br.gov.anvisa.sgc.test.comum;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;


/**
 * Classe container de todos os matchers de testes unitários do OuvidorSUS.
 *
 * @author vinicius.pires
 */
public final class MatchersUtil {
	
	
	/**
	 * Instantiates a new ouvidor matchers.
	 */
	private MatchersUtil(){
	}
	
	/**
	 * Verifica se a data injetada está dentro do Mês/Ano informado.
	 *
	 * @param mesAno String
	 * @return the matcher
	 */
	public static Matcher<Object> temDataDentroDoMesAno(final String mesAno){
		return new TemDataDentroDoMesAno(mesAno);
	}
	
	/**
	 * Verifica se a data injetada está dentro do Mês/Ano informado.
	 *
	 * @param mesAno Long
	 * @return the matcher
	 */
	public static Matcher<Object> temDataDentroDoMesAno(final Long mesAno){
		return new TemDataDentroDoMesAno(mesAno);
	}
	
	/**
	 * Verifica se a data injetada está dentro do Mês/Ano informado.
	 *
	 * @param mesAno Date
	 * @return the matcher
	 */
	public static Matcher<Object> temDataDentroDoMesAno(final Date mesAno){
		return new TemDataDentroDoMesAno(mesAno);
	}
	
	/**
	 * Verifica se a data injetada está dentro do Mês/Ano informado.
	 *
	 * @param mesAno Calendar
	 * @return the matcher
	 */
	public static Matcher<Object> temDataDentroDoMesAno(final Calendar mesAno){
		return new TemDataDentroDoMesAno(mesAno);
	}
	
	/**
	 * Verifica se todos os itens da lista analisada passam no teste do Matcher informado.
	 *
	 * @param matcher Matcher
	 * @return the matcher
	 */
	public static Matcher<Object[]> todosOsItens(final Matcher<?> matcher){
		return new TodosOsItens(matcher);
	}
	
	/**
	 * Verifica se pelo menos um item da lista analisada passa no teste do Matcher informado.
	 *
	 * @param matcher Matcher
	 * @return the matcher
	 */
	public static Matcher<Object[]> peloMenosUmItem(final Matcher<?> matcher){
		return new PeloMenosUmItem(matcher);
	}
	
	// ------------ INNER CLASSES ------------
	/**
	 * Classe dso Matcher que verifica se a data injetada está dentro do Mês/Ano informado.
	 *
	 * @author vinicius.pires
	 */
	private static class TemDataDentroDoMesAno extends BaseMatcher<Object> {
		
		/** The esperado. */
		private final Calendar esperado;

		/**
		 * Instantiates a new tem data dentro do mes ano.
		 *
		 * @param mesano the mesano
		 */
		public TemDataDentroDoMesAno(Calendar mesano) {
			this.esperado = mesano;
		}
		
		/**
		 * Instantiates a new tem data dentro do mes ano.
		 *
		 * @param mesano the mesano
		 */
		public TemDataDentroDoMesAno(String mesano) {
			this(fromStringToCalendar(mesano));
		}

		/**
		 * Instantiates a new tem data dentro do mes ano.
		 *
		 * @param mesano the mesano
		 */
		public TemDataDentroDoMesAno(Long mesano) {
			this(fromStringToCalendar(mesano));
		}
		
		/**
		 * Instantiates a new tem data dentro do mes ano.
		 *
		 * @param mesano the mesano
		 */
		public TemDataDentroDoMesAno(Date mesano) {
			this(fromDateToCalendar(mesano));
		}

		/**
		 * From string to calendar.
		 *
		 * @param mesano the mesano
		 * @return the calendar
		 */
		private static Calendar fromStringToCalendar(String mesano) {
			Calendar c = Calendar.getInstance();
			String[] parts = StringUtils.split(mesano, '-');
			c.set(Calendar.YEAR, Integer.parseInt( parts[0] ) );
			c.set(Calendar.MONTH, Integer.parseInt( parts[1] ) );
			c.set(Calendar.DAY_OF_MONTH, 1 );
			return c;
		}
		
		private static Calendar fromStringToCalendar(Long mesano) {
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(mesano);
			return c;
		}
		
		/**
		 * From date to calendar.
		 *
		 * @param mesano the mesano
		 * @return the calendar
		 */
		private static Calendar fromDateToCalendar(Date mesano) {
			Calendar c = Calendar.getInstance();
			c.setTime(mesano);
			return c;
		}

		/* (non-Javadoc)
		 * @see org.hamcrest.SelfDescribing#describeTo(org.hamcrest.Description)
		 */
		@Override
		public void describeTo(Description description) {
			description.appendText(esperado.toString());
		}
		
		/* (non-Javadoc)
		 * @see org.hamcrest.Matcher#matches(java.lang.Object)
		 */
		@Override
		public boolean matches(Object object) {
			Calendar c = Calendar.getInstance();
			if ( object instanceof String ) {
				String[] parts = StringUtils.split((String)object, '-');
				c.set(Calendar.YEAR, Integer.parseInt( parts[0] ) );
				c.set(Calendar.MONTH, Integer.parseInt( parts[1] ) );
				c.set(Calendar.DAY_OF_MONTH, Integer.parseInt( parts[2] ) );
			} else if ( object instanceof Integer || object instanceof Long) {
				c.setTime(new Date(Long.valueOf(object.toString())));
			} else if ( object instanceof Date) { 
				c.setTime((Date) object);
			} else {
				throw new IllegalArgumentException("Deve receber um objeto Date ou o UNIX timestamp da data");
			}
			
			return (c.get(Calendar.MONTH) == esperado.get(Calendar.MONTH) &&
					c.get(Calendar.YEAR) == esperado.get(Calendar.YEAR));
		}
	}
	
	/**
	 * Classe do Matcher que verifica se todos os itens da lista analisada
	 * passam no teste do Matcher informado.
	 *
	 * @author vinicius.pires
	 */
	private static class TodosOsItens extends BaseMatcher<Object[]> {
		
		/** The matcher. */
		private Matcher<?> matcher;
		
		/**
		 * Instantiates a new todos os itens.
		 *
		 * @param matcher the matcher
		 */
		public TodosOsItens(Matcher<?> matcher) {
			this.matcher = matcher;
		}

		/* (non-Javadoc)
		 * @see org.hamcrest.Matcher#matches(java.lang.Object)
		 */
		@Override
		public boolean matches(Object o) {
			boolean retorno = true;
			for( Object item : (List<?>) o) {
				if (! matcher.matches(item) ) {
					retorno = false;
				}
			}
			return retorno;
		}

		/* (non-Javadoc)
		 * @see org.hamcrest.SelfDescribing#describeTo(org.hamcrest.Description)
		 */
		@Override
		public void describeTo(Description description) {
			matcher.describeTo(description);
		}
		
	}
	
	/**
	 * Classe do Matcher que verifica se pelo menos um item da lista analisada passa no teste do Matcher informado.
	 *
	 * @author vinicius.pires
	 */
	private static class PeloMenosUmItem extends BaseMatcher<Object[]> {
		
		/** The matcher. */
		private Matcher<?> matcher;
		
		/**
		 * Instantiates a new pelo menos um item.
		 *
		 * @param matcher the matcher
		 */
		public PeloMenosUmItem(Matcher<?> matcher) {
			this.matcher = matcher;
		}

		/* (non-Javadoc)
		 * @see org.hamcrest.Matcher#matches(java.lang.Object)
		 */
		@Override
		public boolean matches(Object o) {
			
			for( Object item : (List<?>) o) {
				if (matcher.matches(item)) {
					return true;
				}
			}
			
			return false;
		}

		/* (non-Javadoc)
		 * @see org.hamcrest.SelfDescribing#describeTo(org.hamcrest.Description)
		 */
		@Override
		public void describeTo(Description description) {
			matcher.describeTo(description);
		}
		
	}
}
