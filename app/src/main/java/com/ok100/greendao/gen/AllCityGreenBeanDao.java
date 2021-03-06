package com.ok100.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.ok100.weather.bean.AllCityGreenBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ALL_CITY_GREEN_BEAN".
*/
public class AllCityGreenBeanDao extends AbstractDao<AllCityGreenBean, Long> {

    public static final String TABLENAME = "ALL_CITY_GREEN_BEAN";

    /**
     * Properties of entity AllCityGreenBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "Id", true, "_id");
        public final static Property AREAID = new Property(1, String.class, "AREAID", false, "AREAID");
        public final static Property NAMECN = new Property(2, String.class, "NAMECN", false, "NAMECN");
        public final static Property DISTRICTCN = new Property(3, String.class, "DISTRICTCN", false, "DISTRICTCN");
        public final static Property PROVCN = new Property(4, String.class, "PROVCN", false, "PROVCN");
        public final static Property NATIONCN = new Property(5, String.class, "NATIONCN", false, "NATIONCN");
    }


    public AllCityGreenBeanDao(DaoConfig config) {
        super(config);
    }
    
    public AllCityGreenBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ALL_CITY_GREEN_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: Id
                "\"AREAID\" TEXT," + // 1: AREAID
                "\"NAMECN\" TEXT," + // 2: NAMECN
                "\"DISTRICTCN\" TEXT," + // 3: DISTRICTCN
                "\"PROVCN\" TEXT," + // 4: PROVCN
                "\"NATIONCN\" TEXT);"); // 5: NATIONCN
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ALL_CITY_GREEN_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, AllCityGreenBean entity) {
        stmt.clearBindings();
 
        Long Id = entity.getId();
        if (Id != null) {
            stmt.bindLong(1, Id);
        }
 
        String AREAID = entity.getAREAID();
        if (AREAID != null) {
            stmt.bindString(2, AREAID);
        }
 
        String NAMECN = entity.getNAMECN();
        if (NAMECN != null) {
            stmt.bindString(3, NAMECN);
        }
 
        String DISTRICTCN = entity.getDISTRICTCN();
        if (DISTRICTCN != null) {
            stmt.bindString(4, DISTRICTCN);
        }
 
        String PROVCN = entity.getPROVCN();
        if (PROVCN != null) {
            stmt.bindString(5, PROVCN);
        }
 
        String NATIONCN = entity.getNATIONCN();
        if (NATIONCN != null) {
            stmt.bindString(6, NATIONCN);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, AllCityGreenBean entity) {
        stmt.clearBindings();
 
        Long Id = entity.getId();
        if (Id != null) {
            stmt.bindLong(1, Id);
        }
 
        String AREAID = entity.getAREAID();
        if (AREAID != null) {
            stmt.bindString(2, AREAID);
        }
 
        String NAMECN = entity.getNAMECN();
        if (NAMECN != null) {
            stmt.bindString(3, NAMECN);
        }
 
        String DISTRICTCN = entity.getDISTRICTCN();
        if (DISTRICTCN != null) {
            stmt.bindString(4, DISTRICTCN);
        }
 
        String PROVCN = entity.getPROVCN();
        if (PROVCN != null) {
            stmt.bindString(5, PROVCN);
        }
 
        String NATIONCN = entity.getNATIONCN();
        if (NATIONCN != null) {
            stmt.bindString(6, NATIONCN);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public AllCityGreenBean readEntity(Cursor cursor, int offset) {
        AllCityGreenBean entity = new AllCityGreenBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // Id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // AREAID
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // NAMECN
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // DISTRICTCN
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // PROVCN
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5) // NATIONCN
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, AllCityGreenBean entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setAREAID(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setNAMECN(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setDISTRICTCN(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setPROVCN(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setNATIONCN(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(AllCityGreenBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(AllCityGreenBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(AllCityGreenBean entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
