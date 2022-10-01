package util;

import java.lang.reflect.*;

public enum ClassUtil {
    INSTANCE;
    ClassUtil(){}
    public void exitWithError(String className , String msg){
        System.out.println(className+"에서 "+msg);
        System.out.println("확인후 다시 실행해 주세요");
        System.exit(0);
    }
    public Class<?> getClass(String className){
        String msg = "";
        Class clazz = null;
        try {
            clazz =  Class.forName(className);
        } catch (ClassNotFoundException e) {
            msg="오류가 발생했습니다(cnfe)";
        } catch (Exception e){
            e.printStackTrace();
            msg="오류가 발생했습니다(e)";
        } finally {
            if(clazz==null||!"".equals(msg)){
                exitWithError(className,msg);
            }
        }
        return clazz;
    }
    public Method getMethod(Class clazz , String methodName){
        String msg = "";
        try {
            for(Method m : clazz.getDeclaredMethods()){
                if(m.getName().equals(methodName)){
                    return m;
                }
            }
        }  catch (Exception e){
            e.printStackTrace();
            msg="오류가 발생했습니다(e)";
        } finally {
            if(!"".equals(msg)){
                exitWithError(clazz.getName(),msg);
            }
        }
        return null;
    }

    public Object getObj(String className){
        String msg = "";
        Class<?> clazz = getClass(className);
        Constructor<?> constructor;
        Object obj=null;
        try {
            constructor = clazz.getConstructor();
            obj=constructor.newInstance();
        } catch (NoSuchMethodException e) {
            msg="오류가 발생했습니다(nse)";
        } catch (Exception e){
            e.printStackTrace();
            msg="오류가 발생했습니다(e)";
        } finally {
            if(obj==null||!"".equals(msg)){
                exitWithError(clazz.getName(),msg);
            }
        }
        return obj;
    }

    public <T> Object invoke(String className, Object obj , String methodName, T ... t) {
        Class<?> clazz = getClass(className);
        Method method = getMethod(clazz,methodName);
        Object object = null;
        String msg = "";
        try {
            object = method.invoke(obj,t);
        } catch (InvocationTargetException e) {
            msg="오류가 발생했습니다(ite)";
        } catch (IllegalAccessException e) {
            msg="오류가 발생했습니다(iae)";
        } catch (Exception e){
            e.printStackTrace();
            msg="오류가 발생했습니다(e)";
        } finally {
            if(!"".equals(msg)){
                exitWithError(clazz.getName(),msg);
            }
        }
        return object;
    }

    public <T> Object invoke(String className, String methodName, T ... t) {
        Class<?> clazz = getClass(className);
        Method method = getMethod(clazz,methodName);
        Object obj = getObj(className);
        Object object = null;
        String msg = "";
        try {
            object = method.invoke(obj,t);
        } catch (InvocationTargetException e) {
            msg="오류가 발생했습니다(ite)";
        } catch (IllegalAccessException e) {
            msg="오류가 발생했습니다(iae)";
        } catch (Exception e){
            e.printStackTrace();
            msg="오류가 발생했습니다(e)";
        } finally {
            if(!"".equals(msg)){
                exitWithError(clazz.getName(),msg);
            }
        }
        return object;
    }
}
