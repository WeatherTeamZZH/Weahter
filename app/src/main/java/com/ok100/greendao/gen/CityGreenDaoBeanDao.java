package com.ok100.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.ok100.weather.bean.CityGreenDaoBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CITY_GREEN_DAO_BEAN".
*/
public class CityGreenDaoBeanDao extends AbstractDao<CityGreenDaoBean, Long> {

    public static final String TABLENAME = "CITY_GREEN_DAO_BEAN";

    /**
     * Properties of entity CityGreenDaoBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "Id", true, "_id");
        public final static Property Area = new Property(1, String.class, "area", false, "AREA");
        public final static Property City = new Property(2, String.class, "city", false, "CITY");
        public final static Property Prov = new Property(3, String.class, "prov", false, "PROV");
        public final static Property Jq = new Property(4, String.class, "jq", false, "JQ");
        public final static Property Ip = new Property(5, String.class, "ip", false, "IP");
        public final static Property From = new Property(6, String.class, "from", false, "FROM");
        public final static Property Lat = new Property(7, String.class, "lat", false, "LAT");
        public final static Property Lng = new Property(8, String.class, "lng", false, "LNG");
    }


    public CityGreenDaoBeanDao(DaoConfig config) {
        super(config);
    }
    
    public CityGreenDaoBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CITY_GREEN_DAO_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: Id
                "\"AREA\" TEXT," + // 1: area
                "\"CITY\" TEXT," + // 2: city
                "\"PROV\" TEXT," + // 3: prov
                "\"JQ\" TEXT," + // 4: jq
                "\"IP\" TEXT," + // 5: ip
                "\"FROM\" TEXT," + // 6: from
                "\"LAT\" TEXT," + // 7: lat
                "\"LNG\" TEXT);"); // 8: lng
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CITY_GREEN_DAO_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, CityGreenDaoBean entity) {
        stmt.clearBindings();
 
        Long Id = entity.getId();
        if (Id != null) {
            stmt.bindLong(1, Id);
        }
 
        String area = entity.getArea();
        if (area != null) {
            stmt.bindString(2, area);
        }
 
        String city = entity.getCity();
        if (city != null) {
            stmt.bindString(3, city);
        }
 
        String prov = entity.getProv();
        if (prov != null) {
            stmt.bindString(4, prov);
        }
 
        String jq = entity.getJq();
        if (jq != null) {
            stmt.bindString(5, jq);
        }
 
        String ip = entity.getIp();
        if (ip != null) {
            stmt.bindString(6, ip);
        }
 
        String from = entity.getFrom();
        if (from != null) {
            stmt.bindString(7, from);
        }
 
        String lat = entity.getLat();
        if (lat != null) {
            stmt.bindString(8, lat);
        }
 
        String lng = entity.getLng();
        if (lng != null) {
            stmt.bindString(9, lng);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, CityGreenDaoBean entity) {
        stmt.clearBindings();
 
        Long Id = entity.getId();
        if (Id != null) {
            stmt.bindLong(1, Id);
        }
 
        String area = entity.getArea();
        if (area != null) {
            stmt.bindString(2, area);
        }
 
        String city = entity.getCity();
        if (city != null) {
            stmt.bindString(3, city);
        }
 
        String prov = entity.getProv();
        if (prov != null) {
            stmt.bindString(4, prov);
        }
 
        String jq = entity.getJq();
        if (jq != null) {
            stmt.bindString(5, jq);
        }
 
        String ip = entity.getIp();
        if (ip != null) {
            stmt.bindString(6, ip);
        }
 
        String from = entity.getFrom();
        if (from != null) {
            stmt.bindString(7, from);
        }
 
        String lat = entity.getLat();
        if (lat != null) {
            stmt.bindString(8, lat);
        }
 
        String lng = entity.getLng();
        if (lng != null) {
            stmt.bindString(9, lng);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public CityGreenDaoBean readEntity(Cursor cursor, int offset) {
        CityGreenDaoBean entity = new CityGreenDaoBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // Id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // area
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // city
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // prov
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // jq
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // ip
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // from
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // lat
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8) // lng
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, CityGreenDaoBean entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setArea(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setCity(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setProv(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setJq(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setIp(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setFrom(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setLat(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setLng(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(CityGreenDaoBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(CityGreenDaoBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(CityGreenDaoBean entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
