/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grucas.web.empleados.DAO;

import com.grucas.web.empleados.model.Empleado;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 *
 * @author GrucasDev
 */
public class EmpleadoDAO {

    private Integer id = 0;
    private List<Empleado> objects = null;
    private Boolean ok = false;
    private Integer err_code = 0;
    private final SqlSessionFactory sqlSessionFactory;
    
    public EmpleadoDAO(String env) {
        sqlSessionFactory = FactorySession.getSqlSessionFactory(env);
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Empleado> getObjects() {
        return objects;
    }

    public void setObjects(List<Empleado> objects) {
        this.objects = objects;
    }

    public Boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }

    public Integer getErr_code() {
        return err_code;
    }

    public void setErr_code(Integer err_code) {
        this.err_code = err_code;
    }
     
    public void getEmpleadoID(){
        
        SqlSession session = null;
        
        try {
            
            session = sqlSessionFactory.openSession();
            id = session.selectOne("EmpleadoMaxID");
             
            if(id==null)
            {
                id = 1;
            }
            
            ok = true;
            
        } catch(Exception exception){
            
            ok = false;
            
        } finally {
            
            if(session != null){
                session.close();
            }
            
        }
    }
     
    public void getEmpleado(String strWhere, String strGroup, String strOrder){
        
        SqlSession session = null;
        
        try {
            
            Map map = new HashMap();
            
            map.put("where", strWhere.length()==0?"":"WHERE " + strWhere);
            map.put("group", strGroup.length()==0?"":" GROUP BY " + strGroup);
            map.put("order", strOrder.length()==0?"":" ORDER BY " + strOrder);
            
            session = sqlSessionFactory.openSession();
            objects = session.selectList("EmpleadoWhere",map);
            
            ok = true;
            
        } catch(Exception exception){
            
            ok = false;
            
        } finally {
            
            if(session != null){
                session.close();
            }
            
        }
    }
     
    public void EmpleadoInsert(Empleado object) {
        
        SqlSession session = null;

        try {

            session = sqlSessionFactory.openSession();
            session.insert("EmpleadoInsert", object);
            session.commit();
            ok = true;
            
        } catch(Exception exception){
            
            ok = false;
            
        } finally {
            
            if(session != null){
                session.close();
            }
            
        }
    }
     
    public void EmpleadoUpdate(Empleado object){
        
        SqlSession session = null;
        
        try {
            
            session = sqlSessionFactory.openSession();
            session.update("EmpleadoUpdate",object);
            session.commit();
            
            ok = true;
            
        } catch(Exception exception){
            
            ok = false;
            
        } finally {
            
            if(session != null){
                session.close();
            }
            
        }
    }
     
    public void EmpleadoDelete(Integer id){
        
        SqlSession session = null;
        
        try {
            
            session = sqlSessionFactory.openSession();
            session.delete("EmpleadoDelete",id);
            session.commit();
            
            ok = true;
            
        } catch(Exception exception){
            
            ok = false;
            
        } finally {
            
            if(session != null){
                session.close();
            }
            
        }
    }
    
}