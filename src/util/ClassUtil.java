package util;

import java.lang.reflect.*;

public enum ClassUtil implements Util{
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
        Method method;
        try {
            for(Method m : clazz.getDeclaredMethods()){
                if(m.getName().equals(methodName)){
                    method = clazz.getMethod(m.getName(),m.getParameterTypes());
                    return method;
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
    public Object invoke(String className, String methodName,Object ... args) {
        Object object = null;
        String msg = "";
        try {
            Class<?> clazz = getClass(className);
            Method method = getMethod(clazz,methodName);
            Object obj = getObj(className);
            object = method.invoke(obj,args);
        } catch (InvocationTargetException e) {
            msg="오류가 발생했습니다(ite)";
        } catch (IllegalAccessException e) {
            msg="오류가 발생했습니다(iae)";
        } catch (Exception e){
            e.printStackTrace();
            msg="오류가 발생했습니다(e)";
        } finally {
            if(!"".equals(msg)){
                exitWithError(className,msg);
            }
        }
        return object;
    }
}
