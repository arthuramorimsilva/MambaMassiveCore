package com.massivecraft.massivecore.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.TreeSet;

public class TimeUnit implements Comparable<TimeUnit>
{
	// -------------------------------------------- //
	// CONSTANTS
	// -------------------------------------------- //
	
	public static final long MILLIS_PER_MILLISECOND = 1L;
	public static final long MILLIS_PER_SECOND = 1000L * MILLIS_PER_MILLISECOND;
	public static final long MILLIS_PER_MINUTE = 60L * MILLIS_PER_SECOND;
	public static final long MILLIS_PER_HOUR = 60L * MILLIS_PER_MINUTE;
	public static final long MILLIS_PER_DAY = 24L * MILLIS_PER_HOUR;
	public static final long MILLIS_PER_WEEK = 7L * MILLIS_PER_DAY;
	public static final long MILLIS_PER_MONTH = 31L * MILLIS_PER_DAY;
	public static final long MILLIS_PER_MONTHUP = 32L * MILLIS_PER_DAY;
	public static final long MILLIS_PER_YEAR = 365L * MILLIS_PER_DAY;
	
	public static final TimeUnit YEAR = new TimeUnit(MILLIS_PER_YEAR, "ano", "anos", "ano", "anos", "ano", "ano", "anos");
	public static final TimeUnit MONTH = new TimeUnit(MILLIS_PER_MONTH, "mes", "meses", "mes", "meses", "mes", "meses");
	public static final TimeUnit WEEK = new TimeUnit(MILLIS_PER_WEEK, "semana", "semanas", "semana", "semanas", "w", "semana", "semanas");
	public static final TimeUnit DAY = new TimeUnit(MILLIS_PER_DAY, "dia", "dias", "dia", "dias", "d", "dia", "dias");
	public static final TimeUnit HOUR = new TimeUnit(MILLIS_PER_HOUR, "hora", "horas", "h", "h", "h", "hs", "hora", "horas");
	public static final TimeUnit MINUTE = new TimeUnit(MILLIS_PER_MINUTE, "minuto", "minutos", "min", "min", "m", "min", "mins", "minuto", "minutos");
	public static final TimeUnit SECOND = new TimeUnit(MILLIS_PER_SECOND, "segundo", "segundos", "s", "s", "s", "seg", "segs", "segundo", "segundos");
	public static final TimeUnit MILLISECOND = new TimeUnit(MILLIS_PER_MILLISECOND, "millisecond", "milliseconds", "ms", "ms", "millis", "millisec", "millisecs", "millisecond", "milliseconds", "ms", "msec", "msecs", "msecond", "mseconds");
	
	// -------------------------------------------- //
	// REGISTRY
	// -------------------------------------------- //
		
	private static final TreeSet<TimeUnit> all = new TreeSet<>();
	public static TreeSet<TimeUnit> getAll() { return new TreeSet<>(all); }
	
	public static TreeSet<TimeUnit> getAllBut(TimeUnit... timeUnits)
	{
		TreeSet<TimeUnit> ret = new TreeSet<>(all);
		for (TimeUnit timeUnit : timeUnits)
		{
			ret.remove(timeUnit);
		}
		return ret;
	}
	
	public static TreeSet<TimeUnit> getAllButMillis()
	{
		return getAllBut(MILLISECOND);
	}
	
	public static TreeSet<TimeUnit> getAllButMillisAndSeconds()
	{
		return getAllBut(MILLISECOND, SECOND);
	}
	
	public static TreeSet<TimeUnit> getAllButMillisSecondsAndMinutes()
	{
		return getAllBut(MILLISECOND, SECOND, MINUTE);
	}
	
	public static TreeSet<TimeUnit> getSpecific(TimeUnit... timeUnits)
	{
		TreeSet<TimeUnit> ret = new TreeSet<>(Arrays.asList(timeUnits));
		return ret;
	}
	
	public static TimeUnit get(String timeUnitString)
	{
		if (timeUnitString == null) return null;
		String timeUnitStringLowerCase = timeUnitString.toLowerCase(); 
		for (TimeUnit timeUnit : all)
		{
			for (String alias : timeUnit.aliases)
			{
				if (alias.equals(timeUnitStringLowerCase))
				{
					return timeUnit;
				}
			}
		}
		return null;
	}
	
	public static boolean register(TimeUnit timeUnit)
	{
		return all.add(timeUnit);
	}
	
	static
	{
		register(YEAR);
		register(MONTH);
		register(WEEK);
		register(DAY);
		register(HOUR);
		register(MINUTE);
		register(SECOND);
		register(MILLISECOND);
	}
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	public final long millis;
	public final String singularName;
	public final String pluralName;
	public final String singularUnit;
	public final String pluralUnit;
	public final Collection<String> aliases;
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	private TimeUnit(long millis, String singularName, String pluralName, String singularUnit, String pluralUnit, String... aliases)
	{
		this.millis = millis;
		this.singularName = singularName;
		this.pluralName = pluralName;
		this.singularUnit = singularUnit;
		this.pluralUnit = pluralUnit;
		this.aliases = new ArrayList<>(Arrays.asList(aliases));
	}
	
	// -------------------------------------------- //
	// UTIL
	// -------------------------------------------- //
	
	public String getUnitString(long amount)
	{
		if (amount == 1) return this.singularUnit;
		return this.pluralUnit;
	}
	
	public String getNameString(long amount)
	{
		if (amount == 1) return this.singularName;
		return this.pluralName;
	}
	
	// -------------------------------------------- //
	// BASIC
	// -------------------------------------------- //
	
	@Override
	public String toString()
	{
		return this.singularName;
	}
	
	@Override
	public int compareTo(TimeUnit that)
	{
		return Long.valueOf(this.millis).compareTo(that.millis) * -1;
	}
	
	@Override
	public final boolean equals(Object other)
	{
		if (! (other instanceof TimeUnit)) return false;
		return this.millis == ((TimeUnit)other).millis;
	}
	
	@Override
	public final int hashCode()
	{
		return (int)(this.millis^(this.millis>>>32));
	}
	
}
