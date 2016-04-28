package com.bytezone.dm3270.database;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Member
{
  private static final SimpleDateFormat fmt1 = new SimpleDateFormat ("yyyy/MM/dd");
  private static final SimpleDateFormat fmt2 =
      new SimpleDateFormat ("yyyy/MM/dd HH:mm:ss");

  private final String name;
  Dataset dataset;

  String id;
  int size;
  int init;
  int mod;
  int vv;
  int mm;

  Date created;
  Date changed;
  java.sql.Date createdSQL;
  java.sql.Date changedSQL;

  public Member (Dataset dataset, String name)
  {
    this.dataset = dataset;
    this.name = name;
  }

  public String getName ()
  {
    return name;
  }

  public void setID (String id)
  {
    this.id = id;
  }

  public void setSize (int size)
  {
    this.size = size;
  }

  public void setSize (int size, int init, int mod, int vv, int mm)
  {
    this.size = size;
    this.init = init;
    this.mod = mod;
    this.vv = vv;
    this.mm = mm;
  }

  public void setDates (java.sql.Date createdSQL, java.sql.Date changedSQL)
  {
    this.createdSQL = createdSQL;
    if (createdSQL != null)
      created = new Date (createdSQL.getTime ());

    this.changedSQL = changedSQL;
    if (changedSQL != null)
      changed = new Date (changedSQL.getTime ());
  }

  public void setDates (String created, String changed)
  {
    try
    {
      if (!created.isEmpty ())
      {
        this.created = fmt1.parse (created);
        this.createdSQL = new java.sql.Date (this.created.getTime ());
      }

      if (!changed.isEmpty ())
      {
        this.changed = fmt2.parse (changed);
        this.changedSQL = new java.sql.Date (this.changed.getTime ());
      }
    }
    catch (ParseException e)
    {
      e.printStackTrace ();
    }
  }

  @Override
  public String toString ()
  {
    if (created != null && changed != null)
      return String.format ("%-8s  %3d  %-8s %4d  %2d  %2d  %2d %s %s", name, size, id,
                            init, mod, vv, mm, fmt1.format (created),
                            fmt2.format (changed));
    return String.format ("%-8s  %3d  %-8s %4d  %2d  %2d  %2d", name, size, id, init, mod,
                          vv, mm);
  }
}